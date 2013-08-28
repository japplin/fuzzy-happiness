package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class MainActivity extends Activity {

	private LinearLayout deckContainer;
	private DrawerLayout drawer;
	private NumberPicker numberPicker;

	private Button startButton;
	private Button restartButton;
	private Button selectButton;
	private Button newGameButton;

	private ArrayList<Card> deck;
	private Set<Suit> suitsInDeck = new HashSet<Suit>(Suit.values().length);
	private Set<Rank> ranksInDeck = new HashSet<Rank>(Rank.values().length);

	private int deckSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deckSize = calculateDeckSize();
		deck = new ArrayList<Card>(deckSize);

		startButton = ((Button) findViewById(R.id.start));
		restartButton = ((Button) findViewById(R.id.restart));
		selectButton = ((Button) findViewById(R.id.select));
		newGameButton = ((Button) findViewById(R.id.new_game));

		deckContainer = ((LinearLayout) findViewById(R.id.shuffled_card_container));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));

		numberPicker = (NumberPicker) findViewById(R.id.np);
		numberPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				ranksInDeck = new HashSet<Rank>(Arrays.asList(Rank.values()).subList(0, newVal));
				deckSize = calculateDeckSize();
			}
		});
		numberPicker.setMaxValue(Rank.values().length);
		numberPicker.setMinValue(1);

		((CheckBox) findViewById(R.id.chkhearts)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.hearts));
		((CheckBox) findViewById(R.id.chkdiamonds)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.diamonds));
		((CheckBox) findViewById(R.id.chkclubs)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.clubs));
		((CheckBox) findViewById(R.id.chkspades)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.spades));

		newGameButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deck = createDeck();
				Collections.shuffle(deck);
				deckContainer.removeAllViews();

				addDeckToLayout(deck);

				CardMemorizerSavedState.getInstance().setIsRunning(false);
				updateButtonVisibility();
			}
		});

		drawer.openDrawer(Gravity.LEFT);
		if (CardMemorizerSavedState.getInstance().isRunning()) {
			deck = new ArrayList<Card>(CardMemorizerSavedState.getInstance().getShuffledDeck());
		} else {
			deck = createDeck();
			Collections.shuffle(deck);
		}

		addDeckToLayout(deck);
		setupButtons();
	}

	private int calculateDeckSize() {
		return ranksInDeck.size() * suitsInDeck.size();
	}

	private OnCheckedChangeListener createSuitCheckChanged(final Suit suit) {
		return new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					suitsInDeck.add(suit);
				} else {
					suitsInDeck.remove(suit);
				}

				deckSize = calculateDeckSize();
			}
		};

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
				Card selectedCard = CardMemorizerSavedState.getInstance().getSelectedCard();
				if (selectedCard != null && isSelectedSuit(selectedCard) && isSelectedRank(selectedCard)) {
					selectedCard.shouldShowBack(false);
					selectedCard.setSelectedState(false);
				}
			}
		});
	}

	private void updateButtonVisibility() {
		startButton.setVisibility(CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		restartButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		selectButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
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

	private ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<Card>(deckSize);

		for (Rank rank : ranksInDeck) {
			for (Suit suit : suitsInDeck) {
				deck.add(new Card(this, rank, suit));
			}
		}
		return deck;
	}

	public void addDeckToLayout(ArrayList<Card> deck) {
		deckContainer.removeAllViews();

		for (int i = 0; i < deckSize; i++) {
			deckContainer.addView(deck.get(i));
		}
	}

	public void onRadioButtonClicked(View view) {
		((RadioButton) view).isChecked();
	}

}
