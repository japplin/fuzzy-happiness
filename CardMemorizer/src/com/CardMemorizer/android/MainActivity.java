package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends Activity implements GuessesLeft {

	public static final String DECK_INFO = "DECK_INFO";
	public static final String LEVEL_INFO = "LEVEL_INFO";
	private GridView gridView;
	private CardGridViewAdapter adapter;
	private MenuItem playButton;
	private Menu menu;

	private Level level;
	private ArrayList<CardInfo> deck;

	public static final int UNLIMITED_GUESSES = -2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		this.level = LevelHolder.getInstance().getLevel(intent.getIntExtra(LEVEL_INFO, LevelHolder.CUSTOM_GAME));
		if (level == null) {
			getActionBar().setTitle(getResources().getString(R.string.custom_game));
		} else {
			getActionBar().setTitle(getResources().getString(R.string.level) + " " + level.getLevelId());
		}
		CardMemorizerSavedState.getInstance().setIsRunning(false);
		CardMemorizerSavedState.getInstance().registerListener(this);
		gridView = ((GridView) findViewById(R.id.grid_view));

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		if (deck == null) {
			deck = intent.getParcelableArrayListExtra(DECK_INFO);
		}

		adapter = new CardGridViewAdapter(this, deck, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				CardSelectionDialog selectionDialog = (CardSelectionDialog) dialog;
				if (which == AlertDialog.BUTTON_POSITIVE) {
					if (selectionDialog.isSelectedCard()) {
						selectionDialog.flipCard();
						CardMemorizerSavedState.getInstance().setCorrectGuesses(CardMemorizerSavedState.getInstance().getCorrectGuesses() + 1);

						if (CardMemorizerSavedState.getInstance().getCorrectGuesses() == CardMemorizerSavedState.getInstance().getCurLevelDeckSize()) {
							level.setLevelCompleted();
							createGameOverDialog(true).show();
						}

					} else {
						selectionDialog.shakeCard();
						if (!(CardMemorizerSavedState.getInstance().getGuessesLeft() == -2)) {
							CardMemorizerSavedState.getInstance().setGuessesLeft(CardMemorizerSavedState.getInstance().getGuessesLeft() - 1);

							if (CardMemorizerSavedState.getInstance().getGuessesLeft() == 0) {
								createGameOverDialog(false).show();
							}
						}
					}
				}
				dialog.dismiss();
			}
		});
		gridView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_options_menu, menu);
		playButton = menu.findItem(R.id.start_game);
		CardMemorizerSavedState.getInstance().registerListener((GuessesLeft) menu.findItem(R.id.guesses).getActionView());
		this.menu = menu;
		menu.getItem(1).setVisible(false);
		menu.getItem(2).setVisible(false);
		menu.getItem(3).setVisible(false);
		return super.onCreateOptionsMenu(menu);
	}

	private AlertDialog createGameOverDialog(final boolean winner) {
		AlertDialog gameOverDialog = new AlertDialog.Builder(this).create();
		gameOverDialog.setTitle(level.getLevelId() + "");
		gameOverDialog.setButton(AlertDialog.BUTTON_POSITIVE, winner ? getResources().getString(R.string.next_level) : getResources().getString(R.string.restart),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (winner) {
							Level nextLevel = LevelHolder.getInstance().getLevel(level.getLevelId() + 1);
							CardMemorizerSavedState.getInstance().loadLevel(nextLevel, MainActivity.this);
							MainActivity.this.finish();
						} else {
							MainActivity.this.restartGame();
						}
					}

				});
		gameOverDialog.setButton(AlertDialog.BUTTON_NEGATIVE, winner ? getResources().getString(R.string.main_menu) : getResources().getString(R.string.restart),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
					}

				});

		return gameOverDialog;
	}

	public void restartGame() {
		for (CardInfo card : deck) {
			card.isBackShowing(false);
		}
		adapter.notifyDataSetChanged();
		Collections.shuffle(deck);
		CardMemorizerSavedState.getInstance().setIsRunning(false);
		CardMemorizerSavedState.getInstance().setGuessesLeft(0);

		playButton.setIcon(R.drawable.ic_action_play);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		deck = savedInstanceState.getParcelableArrayList(DECK_INFO);
		adapter.setData(deck);
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList(DECK_INFO, deck);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		CardMemorizerSavedState.getInstance().setIsRunning(false);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.start_game:
			if (!CardMemorizerSavedState.getInstance().isRunning()) {
				CardMemorizerSavedState.getInstance().setIsRunning(true);
				for (CardInfo card : deck) {
					card.isBackShowing(true);
				}
				item.setIcon(R.drawable.ic_action_refresh);
				adapter.notifyDataSetChanged();
				if (level != null) {
					CardMemorizerSavedState.getInstance().setGuessesLeft(level.getGuesses());
				}
			} else {
				restartGame();
			}
			return true;
		case android.R.id.home:
			if (level == null) {
				CardMemorizerSavedState.getInstance().setIsRunning(false);
				NavigationHelper.getInstance().goToCustomGameCreation(MainActivity.this);
			} else {
				CardMemorizerSavedState.getInstance().setIsRunning(false);
				NavigationHelper.getInstance().goToLevelBrowserActivity(MainActivity.this);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (level == null) {
			CardMemorizerSavedState.getInstance().setIsRunning(false);
			NavigationHelper.getInstance().goToCustomGameCreation(MainActivity.this);
		} else {
			CardMemorizerSavedState.getInstance().setIsRunning(false);
			NavigationHelper.getInstance().goToLevelBrowserActivity(MainActivity.this);
		}
	}

	@Override
	public void guessesLeftChanged(int guessesLeft) {
		menu.findItem(R.id.guesses).setVisible(guessesLeft != 0);
	}

}
