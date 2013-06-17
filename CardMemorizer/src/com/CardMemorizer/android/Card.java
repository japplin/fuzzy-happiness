package com.CardMemorizer.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout {
	public enum Suit {
		spades, clubs, hearts, diamonds
	};

	public enum Rank {
		ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king
	};

	private int id;
	private Rank rank;
	private Suit suit;

	public Card(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Card(Context context) {
		super(context);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Rank getRank() {
		return rank;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}
}
