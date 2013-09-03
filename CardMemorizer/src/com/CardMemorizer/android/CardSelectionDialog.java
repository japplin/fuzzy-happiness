package com.CardMemorizer.android;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class CardSelectionDialog extends Dialog {
	Button select;
	Card card;
	NumberPicker suitList;
	NumberPicker rankList;
	String[] rankValues;
	String[] suitValues;
	Context context;

	public CardSelectionDialog(Context context, Card card) {
		super(context);
		this.card = card;
		this.context = context;
		setContentView(R.layout.card_context_menu);
		setTitle("Title...");

		Resources r = context.getResources();

		suitList = (NumberPicker) findViewById(R.id.suit_picker);
		suitValues = new String[] { r.getString(R.string.hearts), r.getString(R.string.clubs), r.getString(R.string.diamonds), r.getString(R.string.spades),
				r.getString(R.string.hearts), r.getString(R.string.clubs), r.getString(R.string.diamonds), r.getString(R.string.spades) };
		setupNumberPicker(suitList, suitValues);

		rankList = (NumberPicker) findViewById(R.id.rank_picker);
		rankValues = new String[] { r.getString(R.string.ace), r.getString(R.string.two), r.getString(R.string.three), r.getString(R.string.four), r.getString(R.string.five),
				r.getString(R.string.six), r.getString(R.string.seven), r.getString(R.string.eight), r.getString(R.string.nine), r.getString(R.string.ten),
				r.getString(R.string.jack), r.getString(R.string.queen), r.getString(R.string.king) };
		setupNumberPicker(rankList, rankValues);
		
		select = (Button) findViewById(R.id.select);
		select.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isSelectedSuit() && isSelectedRank()) {
					flipCard();
				}
				dismiss();
			}
		});
	}

	private void setupNumberPicker(NumberPicker numberPicker, String[] values) {
		numberPicker.setDisplayedValues(values);
		numberPicker.setMaxValue(values.length - 1);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}

	private void flipCard() {
		card.flip();
	}

	private boolean isSelectedSuit() {
		return suitValues[suitList.getValue()].equals(context.getResources().getString(card.getSuit().getId()));
	}

	private boolean isSelectedRank() {
		return rankValues[rankList.getValue()].equals(context.getResources().getString(card.getRank().getId()));
	}

}
