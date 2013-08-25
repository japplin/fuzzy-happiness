package com.CardMemorizer.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout {
	public enum Suit {
		spades(R.id.spades), clubs(R.id.clubs), hearts(R.id.hearts), diamonds(R.id.diamonds);

		private int id;

		private Suit(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	};

	public enum Rank {
		ace(R.id.ace), two(R.id.two), three(R.id.three), four(R.id.four), five(R.id.five), six(R.id.six), seven(R.id.seven), eight(R.id.eight), nine(R.id.nine), ten(R.id.ten), jack(
				R.id.jack), queen(R.id.queen), king(R.id.king);
		private int id;

		private Rank(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

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
