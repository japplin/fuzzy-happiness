package com.CardMemorizer.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class CardSelectionDialog extends AlertDialog {

	private Card card;
	private NumberPicker suitList;
	private NumberPicker rankList;
	private String[] rankValues;
	private String[] suitValues;
	private Context context;
	
	public CardSelectionDialog(Context context, Card card) {
		super(context);
		this.card = card;
		this.context = context;
		
		Resources r = context.getResources();
		RelativeLayout dialogContent = (RelativeLayout) getLayoutInflater().inflate(R.layout.card_context_menu, null);

		setView(dialogContent);
		setTitle(R.string.card_selection_dialog_title);

		suitList = (NumberPicker) dialogContent.findViewById(R.id.suit_picker);
		suitValues = new String[] { r.getString(R.string.hearts), r.getString(R.string.clubs), r.getString(R.string.diamonds), r.getString(R.string.spades),
				r.getString(R.string.hearts), r.getString(R.string.clubs), r.getString(R.string.diamonds), r.getString(R.string.spades) };
		setupNumberPicker(suitList, suitValues);

		rankList = (NumberPicker) dialogContent.findViewById(R.id.rank_picker);
		rankValues = new String[] { r.getString(R.string.ace), r.getString(R.string.two), r.getString(R.string.three), r.getString(R.string.four), r.getString(R.string.five),
				r.getString(R.string.six), r.getString(R.string.seven), r.getString(R.string.eight), r.getString(R.string.nine), r.getString(R.string.ten),
				r.getString(R.string.jack), r.getString(R.string.queen), r.getString(R.string.king) };
		setupNumberPicker(rankList, rankValues);
	}

	private void setupNumberPicker(NumberPicker numberPicker, String[] values) {
		numberPicker.setDisplayedValues(values);
		numberPicker.setMaxValue(values.length - 1);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}

	public void flipCard() {
		card.flip();
	}

	public void shakeCard() {
		card.shake();
	}

	public boolean isSelectedCard() {
		return isSelectedSuit() && isSelectedRank();
	}

	private boolean isSelectedSuit() {
		return suitValues[suitList.getValue()].equals(context.getResources().getString(card.getSuit().getId()));
	}

	private boolean isSelectedRank() {
		return rankValues[rankList.getValue()].equals(context.getResources().getString(card.getRank().getId()));
	}
}
