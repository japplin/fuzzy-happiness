package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	private int deckSize;
	private ArrayList<Card> shuffledDeck;
	private int selectedPosition;
	private Button startButton;
	private Button restartButton;
	private Button selectButton;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deckSize = 52;
		shuffledDeck = new ArrayList<Card>(deckSize);
		startButton = ((Button) findViewById(R.id.start));
		restartButton = ((Button) findViewById(R.id.restart));
		selectButton = ((Button) findViewById(R.id.select));
		container = ((LinearLayout) findViewById(R.id.shuffled_card_container));

		if (CardMemorizerSavedState.getInstance().isRunning()) {
			shuffledDeck = new ArrayList<Card>(CardMemorizerSavedState.getInstance().getShuffledDeck());
			selectedPosition = CardMemorizerSavedState.getInstance().getSelectedPosition();
		} else {
			shuffledDeck = createDeck();
			selectedPosition = 0;
			Collections.shuffle(shuffledDeck);
		}

		addDeckToLayout(shuffledDeck, R.id.shuffled_card_container);
		setupButtons();
	}

	public void setupButtons() {
		updateButtonVisibility();
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (Card s : shuffledDeck) {
						s.findViewById(R.id.card_back).setVisibility(View.VISIBLE);
					}
					updateButtonVisibility();
				}

			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Collections.shuffle(shuffledDeck);
				container.removeAllViews();
				for (int i = 0; i <= deckSize; i++) {
					shuffledDeck.get(i).findViewById(R.id.card_selected).setVisibility(View.GONE);
					shuffledDeck.get(i).findViewById(R.id.card_back).setVisibility(View.GONE);
					container.addView(shuffledDeck.get(i));
				}
				
				selectedPosition = 0;
				CardMemorizerSavedState.getInstance().setIsRunning(false);
				updateButtonVisibility();
			}
		});

		selectButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isSelectedSuit() && isSelectedRank()) {
					shuffledDeck.get(selectedPosition).findViewById(R.id.card_back).setVisibility(View.GONE);
					shuffledDeck.get(selectedPosition).findViewById(R.id.card_selected).setVisibility(View.GONE);
					if ((selectedPosition) < deckSize) {
						selectedPosition++;
						shuffledDeck.get(selectedPosition).findViewById(R.id.card_selected).setVisibility(View.VISIBLE);
					}
				}
			}
		});
	}

	private void updateButtonVisibility() {
		startButton.setVisibility(CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		restartButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		selectButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
	}

	private boolean isSelectedSuit() {
		Card selectedCard = shuffledDeck.get(selectedPosition);
		RadioGroup suit = (RadioGroup) findViewById(R.id.card_suit);
		return suit.getCheckedRadioButtonId() == selectedCard.getSuit().getId();
	}

	private boolean isSelectedRank() {
		Card selectedCard = shuffledDeck.get(selectedPosition);
		RadioGroup rank = (RadioGroup) findViewById(R.id.card_rank);
		return rank.getCheckedRadioButtonId() == selectedCard.getRank().getId();
	}

	@Override
	protected void onPause() {
		CardMemorizerSavedState.getInstance().setSelectedPosition(selectedPosition);
		CardMemorizerSavedState.getInstance().setShuffledDeck(shuffledDeck);
		container.removeAllViews();
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
		deck.add(createCard(R.drawable.clubs_ace, Card.Rank.ace, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_2, Card.Rank.two, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_3, Card.Rank.three, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_4, Card.Rank.four, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_5, Card.Rank.five, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_6, Card.Rank.six, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_7, Card.Rank.seven, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_8, Card.Rank.eight, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_9, Card.Rank.nine, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_10, Card.Rank.ten, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_jack, Card.Rank.jack, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_queen, Card.Rank.queen, Card.Suit.clubs));
		deck.add(createCard(R.drawable.clubs_king, Card.Rank.king, Card.Suit.clubs));
		deck.add(createCard(R.drawable.diamonds_ace, Card.Rank.ace, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_2, Card.Rank.two, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_3, Card.Rank.three, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_4, Card.Rank.four, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_5, Card.Rank.five, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_6, Card.Rank.six, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_7, Card.Rank.seven, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_8, Card.Rank.eight, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_9, Card.Rank.nine, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_10, Card.Rank.ten, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_jack, Card.Rank.jack, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.diamonds_queen, Card.Rank.queen, Card.Suit.diamonds));
		deck.add(createCard(R.drawable.hearts_king, Card.Rank.king, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_ace, Card.Rank.ace, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_2, Card.Rank.two, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_3, Card.Rank.three, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_4, Card.Rank.four, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_5, Card.Rank.five, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_6, Card.Rank.six, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_7, Card.Rank.seven, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_8, Card.Rank.eight, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_9, Card.Rank.nine, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_10, Card.Rank.ten, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_jack, Card.Rank.jack, Card.Suit.hearts));
		deck.add(createCard(R.drawable.hearts_queen, Card.Rank.queen, Card.Suit.hearts));
		deck.add(createCard(R.drawable.spades_king, Card.Rank.king, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_king, Card.Rank.king, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_ace, Card.Rank.ace, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_2, Card.Rank.two, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_3, Card.Rank.three, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_4, Card.Rank.four, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_5, Card.Rank.five, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_6, Card.Rank.six, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_7, Card.Rank.seven, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_8, Card.Rank.eight, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_9, Card.Rank.nine, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_10, Card.Rank.ten, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_jack, Card.Rank.jack, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_queen, Card.Rank.queen, Card.Suit.spades));
		deck.add(createCard(R.drawable.spades_king, Card.Rank.king, Card.Suit.spades));
		return deck;
	}

	public Card createCard(int id, Card.Rank rank, Card.Suit suit) {
		Card card = (Card) getLayoutInflater().inflate(R.layout.card, null);
		ImageView cardFront = (ImageView) card.findViewById(R.id.card_front);
		card.setId(id);
		card.setRank(rank);
		card.setSuit(suit);
		cardFront.setImageResource(id);
		card.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.findViewById(R.id.card_back).getVisibility() == View.VISIBLE) {
					for (int i = 0; i < deckSize; i++) {
						shuffledDeck.get(i).findViewById(R.id.card_selected).setVisibility(View.GONE);
					}

					v.findViewById(R.id.card_selected).setVisibility(View.VISIBLE);
					for (int i = 0; i < deckSize; i++) {
						if (shuffledDeck.get(i).findViewById(R.id.card_selected).getVisibility() == View.VISIBLE) {
							selectedPosition = i;
						}
					}
				}
			}
		});

		return card;
	}

	public void addDeckToLayout(ArrayList<Card> deck, int id) {
		for (Card s : deck) {
			container.addView(s);
		}
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

	}
}
