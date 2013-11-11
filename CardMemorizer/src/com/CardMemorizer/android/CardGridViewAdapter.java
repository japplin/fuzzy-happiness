package com.CardMemorizer.android;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CardGridViewAdapter extends BaseAdapter {

	private List<CardInfo> deck;
	private final Activity activity;
	private DialogInterface.OnClickListener listener;

	public CardGridViewAdapter(final Activity activity, List<CardInfo> deck,
			DialogInterface.OnClickListener listener) {
		this.deck = deck;
		this.activity = activity;
		this.listener = listener;
	}

	public void setData(List<CardInfo> deck) {
		this.deck = deck;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return deck.size();
	}

	@Override
	public CardInfo getItem(int position) {
		return deck.get(position);
	}

	@Override
	public long getItemId(int position) {
		return -1l;
	}

	@Override
	public Card getView(final int position, View convertView, ViewGroup parent) {
		final Card card;
		final CardInfo cardInfo = getItem(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			card = (Card) inflater.inflate(R.layout.card, parent, false);
		} else {
			card = (Card) convertView;
		}

		card.setCardInfo(cardInfo);
		card.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cardInfo.isBackShowing()) {
					AlertDialog alertDialog = new CardSelectionDialog(activity,
							card);
					Resources r = activity.getResources();
					alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
							r.getString(R.string.select), listener);
					alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
							r.getString(R.string.cancel), listener);
					alertDialog.show();
				}
			}
		});
		return card;
	}
}
