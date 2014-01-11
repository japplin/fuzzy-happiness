package com.CardMemorizer.android.MainNavigation;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;
import com.CardMemorizer.android.CardMemorizerSavedState;
import com.CardMemorizer.android.Level;
import com.CardMemorizer.android.MainActivity;
import com.CardMemorizer.android.NavigationHelper;
import com.CardMemorizer.android.R;

public class CustomGameCreation extends Fragment {

	private NumberPicker mNumberPicker;
	private View mLayout;

	private Set<Suit> mSuitsInDeck = new HashSet<Suit>(Suit.values().length);
	private Set<Rank> mRanksInDeck = new HashSet<Rank>(Rank.values().length);

	private int mDeckSize = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.custom_game_creation_screen, container, false);
		return mLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((CheckBox) mLayout.findViewById(R.id.chkhearts)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.hearts));
		((CheckBox) mLayout.findViewById(R.id.chkdiamonds)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.diamonds));
		((CheckBox) mLayout.findViewById(R.id.chkclubs)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.clubs));
		((CheckBox) mLayout.findViewById(R.id.chkspades)).setOnCheckedChangeListener(createSuitCheckChanged(Suit.spades));

		addRankCheckBoxes();
		mNumberPicker = (NumberPicker) mLayout.findViewById(R.id.np);
		mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		mNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				mDeckSize = newVal;
			}
		});

		mNumberPicker.setMaxValue(208);
		mNumberPicker.setMinValue(1);

		((Button) mLayout.findViewById(R.id.new_game)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mSuitsInDeck.isEmpty() || mRanksInDeck.isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setTitle(R.string.custom_game_start_title);
					builder.setMessage(R.string.custom_game_start_message);
					builder.setNegativeButton(R.string.ok, new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}

					});
					builder.create().show();
				} else {
					Level customLevel = new Level(mSuitsInDeck, mRanksInDeck, mDeckSize, MainActivity.UNLIMITED_GUESSES, Level.CUSTOM_LEVEL);
					CardMemorizerSavedState.getInstance().loadLevel(customLevel, getActivity());
				}
			}
		});
	}

	private void addRankCheckBoxes() {
		((CheckBox) mLayout.findViewById(R.id.ace)).setOnCheckedChangeListener(createRankCheckChanged(Rank.ace));
		((CheckBox) mLayout.findViewById(R.id.two)).setOnCheckedChangeListener(createRankCheckChanged(Rank.two));
		((CheckBox) mLayout.findViewById(R.id.three)).setOnCheckedChangeListener(createRankCheckChanged(Rank.three));
		((CheckBox) mLayout.findViewById(R.id.four)).setOnCheckedChangeListener(createRankCheckChanged(Rank.four));
		((CheckBox) mLayout.findViewById(R.id.five)).setOnCheckedChangeListener(createRankCheckChanged(Rank.five));
		((CheckBox) mLayout.findViewById(R.id.six)).setOnCheckedChangeListener(createRankCheckChanged(Rank.six));
		((CheckBox) mLayout.findViewById(R.id.seven)).setOnCheckedChangeListener(createRankCheckChanged(Rank.seven));
		((CheckBox) mLayout.findViewById(R.id.eight)).setOnCheckedChangeListener(createRankCheckChanged(Rank.eight));
		((CheckBox) mLayout.findViewById(R.id.nine)).setOnCheckedChangeListener(createRankCheckChanged(Rank.nine));
		((CheckBox) mLayout.findViewById(R.id.ten)).setOnCheckedChangeListener(createRankCheckChanged(Rank.ten));
		((CheckBox) mLayout.findViewById(R.id.jack)).setOnCheckedChangeListener(createRankCheckChanged(Rank.jack));
		((CheckBox) mLayout.findViewById(R.id.queen)).setOnCheckedChangeListener(createRankCheckChanged(Rank.queen));
		((CheckBox) mLayout.findViewById(R.id.king)).setOnCheckedChangeListener(createRankCheckChanged(Rank.king));
	}

	private OnCheckedChangeListener createRankCheckChanged(final Rank rank) {
		return new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mRanksInDeck.add(rank);
				} else {
					mRanksInDeck.remove(rank);
				}
			}
		};

	}

	private OnCheckedChangeListener createSuitCheckChanged(final Suit suit) {
		return new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mSuitsInDeck.add(suit);
				} else {
					mSuitsInDeck.remove(suit);
				}
			}
		};
	}
}
