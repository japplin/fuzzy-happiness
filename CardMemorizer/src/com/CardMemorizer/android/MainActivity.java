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

public class MainActivity extends Activity {
	private ArrayList<Card> selectorDeck = new ArrayList<Card>(52);
	private ArrayList<Card> shuffledDeck = new ArrayList<Card>(52);
	private int position = 0;
	private boolean isRunning = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		shuffledDeck = createDeck();
		selectorDeck = createDeck();
		Collections.shuffle(shuffledDeck);
		addDeckToLayout(selectorDeck, R.id.selector_card_container);
		addDeckToLayout(shuffledDeck, R.id.shuffled_card_container);
		findViewById(R.id.selector_card_container).setVisibility(View.GONE);
		((Button) findViewById(R.id.start)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isRunning) {
					findViewById(R.id.selector_card_container).setVisibility(View.VISIBLE);
					for (Card s : shuffledDeck) {
						s.findViewById(R.id.card_back).setVisibility(View.VISIBLE);
					}
				}

			}
		});

		((Button) findViewById(R.id.select)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (Card s : selectorDeck) {
					if (s.findViewById(R.id.card_selected).getVisibility() == View.VISIBLE) {
						if (shuffledDeck.get(position).getId() == s.getId()) {
							shuffledDeck.get(position).findViewById(R.id.card_back).setVisibility(View.GONE);
							position++;
						}
					}
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<Card>(52);
		deck.add(createCard(R.drawable.clubs_ace));
		deck.add(createCard(R.drawable.clubs_2));
		deck.add(createCard(R.drawable.clubs_3));
		deck.add(createCard(R.drawable.clubs_4));
		deck.add(createCard(R.drawable.clubs_5));
		deck.add(createCard(R.drawable.clubs_6));
		deck.add(createCard(R.drawable.clubs_7));
		deck.add(createCard(R.drawable.clubs_8));
		deck.add(createCard(R.drawable.clubs_9));
		deck.add(createCard(R.drawable.clubs_10));
		deck.add(createCard(R.drawable.clubs_jack));
		deck.add(createCard(R.drawable.clubs_queen));
		deck.add(createCard(R.drawable.clubs_king));
		deck.add(createCard(R.drawable.diamonds_ace));
		deck.add(createCard(R.drawable.diamonds_2));
		deck.add(createCard(R.drawable.diamonds_3));
		deck.add(createCard(R.drawable.diamonds_4));
		deck.add(createCard(R.drawable.diamonds_5));
		deck.add(createCard(R.drawable.diamonds_6));
		deck.add(createCard(R.drawable.diamonds_7));
		deck.add(createCard(R.drawable.diamonds_8));
		deck.add(createCard(R.drawable.diamonds_9));
		deck.add(createCard(R.drawable.diamonds_10));
		deck.add(createCard(R.drawable.diamonds_jack));
		deck.add(createCard(R.drawable.diamonds_queen));
		deck.add(createCard(R.drawable.diamonds_king));
		deck.add(createCard(R.drawable.hearts_ace));
		deck.add(createCard(R.drawable.hearts_2));
		deck.add(createCard(R.drawable.hearts_3));
		deck.add(createCard(R.drawable.hearts_4));
		deck.add(createCard(R.drawable.hearts_5));
		deck.add(createCard(R.drawable.hearts_6));
		deck.add(createCard(R.drawable.hearts_7));
		deck.add(createCard(R.drawable.hearts_8));
		deck.add(createCard(R.drawable.hearts_9));
		deck.add(createCard(R.drawable.hearts_10));
		deck.add(createCard(R.drawable.hearts_jack));
		deck.add(createCard(R.drawable.hearts_queen));
		deck.add(createCard(R.drawable.hearts_king));
		deck.add(createCard(R.drawable.spades_ace));
		deck.add(createCard(R.drawable.spades_2));
		deck.add(createCard(R.drawable.spades_3));
		deck.add(createCard(R.drawable.spades_4));
		deck.add(createCard(R.drawable.spades_5));
		deck.add(createCard(R.drawable.spades_6));
		deck.add(createCard(R.drawable.spades_7));
		deck.add(createCard(R.drawable.spades_8));
		deck.add(createCard(R.drawable.spades_9));
		deck.add(createCard(R.drawable.spades_10));
		deck.add(createCard(R.drawable.spades_jack));
		deck.add(createCard(R.drawable.spades_queen));
		deck.add(createCard(R.drawable.spades_king));
		return deck;
	}

	public Card createCard(int id) {
		Card card = (Card) getLayoutInflater().inflate(R.layout.card, null);
		ImageView cardFront = (ImageView) card.findViewById(R.id.card_front);
		card.setId(id);
		cardFront.setImageResource(id);
		card.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (Card s : selectorDeck) {
					if (s.findViewById(R.id.card_selected).getVisibility() == View.VISIBLE) {
						s.findViewById(R.id.card_selected).setVisibility(View.GONE);
					}
				}
				v.findViewById(R.id.card_selected).setVisibility(View.VISIBLE);
			}
		});

		return card;
	}

	public void addDeckToLayout(ArrayList<Card> deck, int id) {
		LinearLayout deckContainer = (LinearLayout) findViewById(id);
		for (Card s : deck) {
			deckContainer.addView(s);
		}
	}
}
