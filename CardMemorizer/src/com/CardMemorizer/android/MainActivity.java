package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class MainActivity extends Activity implements GuessesLeft {

	public static final String DECK_INFO = "DECK_INFO";
	public static final String LEVEL_INFO = "LEVEL_INFO";
	public static final CardInfo[] SPADES = new CardInfo[] { new CardInfo(Suit.spades, Rank.ace, false),
			new CardInfo(Suit.spades, Rank.two, false), new CardInfo(Suit.spades, Rank.three, false),
			new CardInfo(Suit.spades, Rank.four, false), new CardInfo(Suit.spades, Rank.five, false),
			new CardInfo(Suit.spades, Rank.six, false), new CardInfo(Suit.spades, Rank.seven, false),
			new CardInfo(Suit.spades, Rank.eight, false), new CardInfo(Suit.spades, Rank.nine, false),
			new CardInfo(Suit.spades, Rank.ten, false), new CardInfo(Suit.spades, Rank.jack, false),
			new CardInfo(Suit.spades, Rank.queen, false), new CardInfo(Suit.spades, Rank.king, false) };

	public static final CardInfo[] HEARTS = new CardInfo[] { new CardInfo(Suit.hearts, Rank.ace, false),
			new CardInfo(Suit.hearts, Rank.two, false), new CardInfo(Suit.hearts, Rank.three, false),
			new CardInfo(Suit.hearts, Rank.four, false), new CardInfo(Suit.hearts, Rank.five, false),
			new CardInfo(Suit.hearts, Rank.six, false), new CardInfo(Suit.hearts, Rank.seven, false),
			new CardInfo(Suit.hearts, Rank.eight, false), new CardInfo(Suit.hearts, Rank.nine, false),
			new CardInfo(Suit.hearts, Rank.ten, false), new CardInfo(Suit.hearts, Rank.jack, false),
			new CardInfo(Suit.hearts, Rank.queen, false), new CardInfo(Suit.hearts, Rank.king, false) };

	public static final CardInfo[] CLUBS = new CardInfo[] { new CardInfo(Suit.clubs, Rank.ace, false),
			new CardInfo(Suit.clubs, Rank.two, false), new CardInfo(Suit.clubs, Rank.three, false),
			new CardInfo(Suit.clubs, Rank.four, false), new CardInfo(Suit.clubs, Rank.five, false),
			new CardInfo(Suit.clubs, Rank.six, false), new CardInfo(Suit.clubs, Rank.seven, false),
			new CardInfo(Suit.clubs, Rank.eight, false), new CardInfo(Suit.clubs, Rank.nine, false),
			new CardInfo(Suit.clubs, Rank.ten, false), new CardInfo(Suit.clubs, Rank.jack, false),
			new CardInfo(Suit.clubs, Rank.queen, false), new CardInfo(Suit.clubs, Rank.king, false) };

	public static final CardInfo[] DIAMONDS = new CardInfo[] { new CardInfo(Suit.diamonds, Rank.ace, false),
			new CardInfo(Suit.diamonds, Rank.two, false), new CardInfo(Suit.diamonds, Rank.three, false),
			new CardInfo(Suit.diamonds, Rank.four, false), new CardInfo(Suit.diamonds, Rank.five, false),
			new CardInfo(Suit.diamonds, Rank.six, false), new CardInfo(Suit.diamonds, Rank.seven, false),
			new CardInfo(Suit.diamonds, Rank.eight, false), new CardInfo(Suit.diamonds, Rank.nine, false),
			new CardInfo(Suit.diamonds, Rank.ten, false), new CardInfo(Suit.diamonds, Rank.jack, false),
			new CardInfo(Suit.diamonds, Rank.queen, false), new CardInfo(Suit.diamonds, Rank.king, false) };

	private GridView gridView;
	private CardGridViewAdapter adapter;
	private HorizontalListView cardSelector;
	private CardGridViewAdapter cardSelctorAdapter;
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
		cardSelector = (HorizontalListView) findViewById(R.id.card_selector);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		if (deck == null) {
			deck = intent.getParcelableArrayListExtra(DECK_INFO);
		}
		cardSelctorAdapter = new CardGridViewAdapter(MainActivity.this, Arrays.asList(SPADES));
		OnClickListener suitSelectorOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.spades:
					setSelectedSuit(Suit.spades);
					break;
				case R.id.hearts:
					setSelectedSuit(Suit.hearts);
					break;
				case R.id.clubs:
					setSelectedSuit(Suit.clubs);
					break;
				case R.id.diamonds:
					setSelectedSuit(Suit.diamonds);
					break;
				}
			}
		};
		findViewById(R.id.spades).setOnClickListener(suitSelectorOnClickListener);
		findViewById(R.id.hearts).setOnClickListener(suitSelectorOnClickListener);
		findViewById(R.id.clubs).setOnClickListener(suitSelectorOnClickListener);
		findViewById(R.id.diamonds).setOnClickListener(suitSelectorOnClickListener);
		findViewById(R.id.select).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (adapter.getSelectedCard().equals(cardSelctorAdapter.getSelectedCard())) {
					adapter.getSelectedCard().flip();
					CardMemorizerSavedState.getInstance().setCorrectGuesses(
							CardMemorizerSavedState.getInstance().getCorrectGuesses() + 1);

					if (CardMemorizerSavedState.getInstance().getCorrectGuesses() == CardMemorizerSavedState.getInstance()
							.getCurLevelDeckSize()) {
						level.setLevelCompleted();
						createGameOverDialog(true).show();
					}
				} else {
					adapter.getSelectedCard().shake();
					if (!(CardMemorizerSavedState.getInstance().getGuessesLeft() == -2)) {
						CardMemorizerSavedState.getInstance()
								.setGuessesLeft(CardMemorizerSavedState.getInstance().getGuessesLeft() - 1);

						if (CardMemorizerSavedState.getInstance().getGuessesLeft() == 0) {
							createGameOverDialog(false).show();
						}
					}
				}

			}
		});

		adapter = new CardGridViewAdapter(this, deck);
		gridView.setAdapter(adapter);
		cardSelector.setAdapter(cardSelctorAdapter);
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
		gameOverDialog.setButton(AlertDialog.BUTTON_POSITIVE, winner ? getResources().getString(R.string.next_level) : getResources()
				.getString(R.string.restart), new DialogInterface.OnClickListener() {

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
		gameOverDialog.setButton(AlertDialog.BUTTON_NEGATIVE, winner ? getResources().getString(R.string.main_menu) : getResources()
				.getString(R.string.restart), new DialogInterface.OnClickListener() {

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

	public void setSelectedSuit(Suit suit) {
		switch (suit) {
		case clubs:
			cardSelctorAdapter.setData(Arrays.asList(CLUBS));
			break;
		case diamonds:
			cardSelctorAdapter.setData(Arrays.asList(DIAMONDS));
			break;
		case hearts:
			cardSelctorAdapter.setData(Arrays.asList(HEARTS));
			break;
		case spades:
			cardSelctorAdapter.setData(Arrays.asList(SPADES));
			break;
		default:
			break;
		}

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
