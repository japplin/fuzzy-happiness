package com.CardMemorizer.android;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class CardSelectionDialog extends Dialog {
	Button select;
	Card card;

	public CardSelectionDialog(Context context, Card card) {
		super(context);
		this.card = card;
		setContentView(R.layout.card_context_menu);
		setTitle("Title...");
		select = (Button) this.findViewById(R.id.select);
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

	private void flipCard() {
		card.shouldShowBack(false);
		card.setSelectedState(false);
	}

	private boolean isSelectedSuit() {
		RadioGroup suit = (RadioGroup) findViewById(R.id.card_suit);
		return suit.getCheckedRadioButtonId() == card.getSuit().getId();
	}

	private boolean isSelectedRank() {
		RadioGroup rank = (RadioGroup) findViewById(R.id.card_rank);
		return rank.getCheckedRadioButtonId() == card.getRank().getId();
	}

}
