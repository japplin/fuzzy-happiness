package com.CardMemorizer.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CardGridViewAdapter extends BaseAdapter {
	private ArrayList<CardInfo> deck;
	private final Context context;

	public CardGridViewAdapter(final Context context, ArrayList<CardInfo> deck) {
		this.deck = deck;
		this.context = context;

	}

	public void setData(ArrayList<CardInfo> deck) {
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
	public Card getView(int position, View convertView, ViewGroup parent) {
		Card card;
		CardInfo cardInfo = getItem(position);

		if (convertView == null) {
			card = new Card(context, cardInfo);
		} else {
			card = (Card) convertView;
			card.setCardInfo(cardInfo);
		}

		return card;
	}
}
