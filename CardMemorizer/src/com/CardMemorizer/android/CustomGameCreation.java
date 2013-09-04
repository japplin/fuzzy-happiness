package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
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

public class CustomGameCreation extends Activity {
	public static final String DECK_INFO = "DECK_INFO";
	private NumberPicker numberPicker;

	private ArrayList<CardInfo> deck;
	private Set<Suit> suitsInDeck = new HashSet<Suit>(Suit.values().length);
	private Set<Rank> ranksInDeck = new HashSet<Rank>(Rank.values().length);

	private int deckSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_game_creation_screen);

		((CheckBox) findViewById(R.id.chkhearts)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.hearts));
		((CheckBox) findViewById(R.id.chkdiamonds)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.diamonds));
		((CheckBox) findViewById(R.id.chkclubs)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.clubs));
		((CheckBox) findViewById(R.id.chkspades)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.spades));

		addRankCheckBoxes();
		numberPicker = (NumberPicker) findViewById(R.id.np);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		numberPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				deckSize = newVal;
			}
		});

		numberPicker.setMaxValue(208);
		numberPicker.setMinValue(1);

		((Button) findViewById(R.id.new_game)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level customLevel = new Level(suitsInDeck, ranksInDeck, deckSize);
				CardMemorizerSavedState.getInstance().loadLevel(customLevel, CustomGameCreation.this);
			}
		});
	}

	private void addRankCheckBoxes() {
		((CheckBox) findViewById(R.id.ace)).setOnCheckedChangeListener(createRankCheckChanged(Rank.ace));
		((CheckBox) findViewById(R.id.two)).setOnCheckedChangeListener(createRankCheckChanged(Rank.two));
		((CheckBox) findViewById(R.id.three)).setOnCheckedChangeListener(createRankCheckChanged(Rank.three));
		((CheckBox) findViewById(R.id.four)).setOnCheckedChangeListener(createRankCheckChanged(Rank.four));
		((CheckBox) findViewById(R.id.five)).setOnCheckedChangeListener(createRankCheckChanged(Rank.five));
		((CheckBox) findViewById(R.id.six)).setOnCheckedChangeListener(createRankCheckChanged(Rank.six));
		((CheckBox) findViewById(R.id.seven)).setOnCheckedChangeListener(createRankCheckChanged(Rank.seven));
		((CheckBox) findViewById(R.id.eight)).setOnCheckedChangeListener(createRankCheckChanged(Rank.eight));
		((CheckBox) findViewById(R.id.nine)).setOnCheckedChangeListener(createRankCheckChanged(Rank.nine));
		((CheckBox) findViewById(R.id.ten)).setOnCheckedChangeListener(createRankCheckChanged(Rank.ten));
		((CheckBox) findViewById(R.id.jack)).setOnCheckedChangeListener(createRankCheckChanged(Rank.jack));
		((CheckBox) findViewById(R.id.queen)).setOnCheckedChangeListener(createRankCheckChanged(Rank.queen));
		((CheckBox) findViewById(R.id.king)).setOnCheckedChangeListener(createRankCheckChanged(Rank.king));
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
			}
		};
	}
}
