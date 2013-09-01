package com.CardMemorizer.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CardGridViewAdapter extends BaseAdapter {
	private ArrayList<Card> deck;
	private final Context context;

	public CardGridViewAdapter(final Context context, ArrayList<Card> deck) {
		this.deck = deck;
		this.context = context;

		for (final Card card : deck) {
			card.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (card.isBackShowing()) {
						new CardSelectionDialog(context, card).show();
					}
				}
			});
		}
	}

	@Override
	public int getCount() {
		return deck.size();
	}

	@Override
	public Card getItem(int position) {
		return deck.get(position);
	}

	@Override
	public long getItemId(int position) {
		return deck.get(position).getId();
	}

	@Override
	public Card getView(int position, View convertView, ViewGroup parent) {
		return deck.get(position);
	}

}
