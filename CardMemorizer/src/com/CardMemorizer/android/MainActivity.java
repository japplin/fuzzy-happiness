package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class MainActivity extends Activity {

	private LinearLayout deckContainer;
	private DrawerLayout drawer;

	private Button startButton;
	private Button restartButton;
	private Button selectButton;
	// /private Button backToMain;

	private ArrayList<Card> deck;
	private Set<Suit> suitsInDeck = new HashSet<Suit>(Suit.values().length);
	private Set<Rank> ranksInDeck = new HashSet<Rank>(Rank.values().length);

	private int deckSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = ((Button) findViewById(R.id.start));
		restartButton = ((Button) findViewById(R.id.restart));
		selectButton = ((Button) findViewById(R.id.select));
		// backToMain = ((Button) findViewById(R.id.backToMain));

		deckContainer = ((LinearLayout) findViewById(R.id.shuffled_card_container));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));

		// drawer.openDrawer(Gravity.LEFT);

		deck = new ArrayList<Card>(CardMemorizerSavedState.getInstance()
				.getShuffledDeck());

		deckSize = calculateDeckSize();

		addDeckToLayout(deck);
		setupButtons();
	}

	private int calculateDeckSize() {
		return ranksInDeck.size() * suitsInDeck.size();
	}

	public void setupButtons() {
		updateButtonVisibility();
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (Card card : deck) {
						card.shouldShowBack(true);
					}
					updateButtonVisibility();
				}

			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Collections.shuffle(deck);
				deckContainer.removeAllViews();
				for (Card card : deck) {
					card.setSelectedState(false);
					card.shouldShowBack(false);
					deckContainer.addView(card);
				}

				CardMemorizerSavedState.getInstance().setIsRunning(false);

				updateButtonVisibility();
			}
		});

		selectButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Card selectedCard = CardMemorizerSavedState.getInstance()
						.getSelectedCard();
				if (selectedCard != null && isSelectedSuit(selectedCard)
						&& isSelectedRank(selectedCard)) {
					selectedCard.shouldShowBack(false);
					selectedCard.setSelectedState(false);
				}
			}
		});
	}

	private void updateButtonVisibility() {
		startButton.setVisibility(CardMemorizerSavedState.getInstance()
				.isRunning() ? View.GONE : View.VISIBLE);
		restartButton.setVisibility(!CardMemorizerSavedState.getInstance()
				.isRunning() ? View.GONE : View.VISIBLE);
		selectButton.setVisibility(!CardMemorizerSavedState.getInstance()
				.isRunning() ? View.GONE : View.VISIBLE);
	}

	private boolean isSelectedSuit(Card selectedCard) {
		RadioGroup suit = (RadioGroup) findViewById(R.id.card_suit);
		return suit.getCheckedRadioButtonId() == selectedCard.getSuit().getId();
	}

	private boolean isSelectedRank(Card selectedCard) {
		RadioGroup rank = (RadioGroup) findViewById(R.id.card_rank);
		return rank.getCheckedRadioButtonId() == selectedCard.getRank().getId();
	}

	@Override
	protected void onPause() {
		CardMemorizerSavedState.getInstance().setShuffledDeck(deck);
		deckContainer.removeAllViews();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addDeckToLayout(ArrayList<Card> deck) {
		deckContainer.removeAllViews();

		for (Card card : deck) {
			deckContainer.addView(card);
		}

		/*
		 * for (int i = 0; i < deckSize; i++) {
		 * deckContainer.addView(deck.get(i)); }
		 */
	}

	public void onRadioButtonClicked(View view) {
		((RadioButton) view).isChecked();
	}

}
