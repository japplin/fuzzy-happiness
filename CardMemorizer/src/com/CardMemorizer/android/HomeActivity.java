package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class HomeActivity extends Activity {

	private NumberPicker numberPicker;

	private ArrayList<Card> deck;
	private Set<Suit> suitsInDeck = new HashSet<Suit>(Suit.values().length);
	private Set<Rank> ranksInDeck = new HashSet<Rank>(Rank.values().length);

	private int deckSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);

		deckSize = calculateDeckSize();
		deck = new ArrayList<Card>(deckSize);

		((CheckBox) findViewById(R.id.chkhearts)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.hearts));
		((CheckBox) findViewById(R.id.chkdiamonds)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.diamonds));
		((CheckBox) findViewById(R.id.chkclubs)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.clubs));
		((CheckBox) findViewById(R.id.chkspades)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.spades));

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

		((Button) findViewById(R.id.new_game)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deck = createDeck();
				Collections.shuffle(deck);

				CardMemorizerSavedState.getInstance().setShuffledDeck(deck);

				HomeActivity.this.startActivity(new Intent(HomeActivity.this, MainActivity.class));
			}
		});
	}

	private ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<Card>(deckSize);

		for (Rank rank : ranksInDeck) {
			for (Suit suit : suitsInDeck) {
				deck.add(new Card(this, rank, suit));
				if (deck.size() == deckSize) {
					return deck;
				}
			}
		}
		return deck;
	}

	private OnCheckedChangeListener createRankCheckChanged(final Rank rank) {
		return new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					ranksInDeck.add(rank);
				} else {
					ranksInDeck.remove(rank);
				}
			}
		};

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

	private int calculateDeckSize() {
		return ranksInDeck.size() * suitsInDeck.size();
	}
}
