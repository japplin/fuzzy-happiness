package com.CardMemorizer.android;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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
	private ImageView cardBack;
	private ImageView cardFront;
	private ImageView cardSelected;

	public Card(Context context, int cardId, Rank rank, Suit suit) {
		super(context);
		this.id = cardId;
		this.rank = rank;
		this.suit = suit;
		
		cardFront = new ImageView(context);
		cardFront.setImageResource(id);
		addView(cardFront);

		cardBack = new ImageView(context);
		cardBack.setImageResource(R.drawable.cardback_blue);
		cardBack.setVisibility(View.GONE);
		cardBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CardMemorizerSavedState.getInstance().unSelectSelectedCard();
				CardMemorizerSavedState.getInstance().setSelectedCard(Card.this);
				cardSelected.setVisibility(View.VISIBLE);
			}
		});
		addView(cardBack);

		cardSelected = new ImageView(context);
		cardSelected.setImageResource(R.drawable.cardback_blue_selected);
		cardSelected.setVisibility(View.GONE);
		addView(cardSelected);

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

	public void shouldShowBack(boolean shouldShowBack) {
		cardBack.setVisibility(shouldShowBack ? View.VISIBLE : View.GONE);
	}

	public void setSelectedState(boolean isSelected) {
		cardSelected.setVisibility(isSelected ? View.VISIBLE : View.GONE);
	}

}
