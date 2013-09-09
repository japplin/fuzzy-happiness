package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

	public static final String DECK_INFO = "DECK_INFO";
	public static final String LEVEL_INFO = "LEVEL_INFO";
	private GridView gridView;
	private CardGridViewAdapter adapter;

	private DrawerLayout drawer;
	private ActionBarDrawerToggle mDrawerToggle;

	private Button startButton;
	private Button restartButton;
	private Level level;
	private ArrayList<CardInfo> deck;

	public static final int UNLIMITED_GUESSES = -2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		this.level = LevelHolder.getInstance().getLevel(intent.getIntExtra(LEVEL_INFO, LevelHolder.CUSTOM_GAME));

		gridView = ((GridView) findViewById(R.id.grid_view));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));
		startButton = ((Button) drawer.findViewById(R.id.start));
		restartButton = ((Button) drawer.findViewById(R.id.restart));

		drawer.openDrawer(Gravity.LEFT);
		mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {

			public void onDrawerClosed(View view) {
				getActionBar().setTitle(R.string.app_name);
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(R.string.app_name);
			}
		};

		drawer.setDrawerListener(mDrawerToggle);

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
						}

					} else {
						selectionDialog.shakeCard();
						if (!(CardMemorizerSavedState.getInstance().getGuessesLeft() == -2)) {
							CardMemorizerSavedState.getInstance().setGuessesLeft(CardMemorizerSavedState.getInstance().getGuessesLeft() - 1);

							if (CardMemorizerSavedState.getInstance().getGuessesLeft() == -1) {

							}
						}
					}
				}
				dialog.dismiss();
			}
		});
		gridView.setColumnWidth(175);
		gridView.setAdapter(adapter);
		setupButtons();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	private void setupButtons() {
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (CardInfo card : deck) {
						card.isBackShowing(true);
					}
					drawer.closeDrawers();
					adapter.notifyDataSetChanged();
				}
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (CardInfo card : deck) {
					card.isBackShowing(false);
				}
				adapter.notifyDataSetChanged();
				Collections.shuffle(deck);
				CardMemorizerSavedState.getInstance().setIsRunning(false);
			}
		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
