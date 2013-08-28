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

	public Card(Context context, Rank rank, Suit suit) {
		super(context);
		this.rank = rank;
		this.suit = suit;
		this.id = getImageId(suit, rank);

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

	private int getImageId(Suit suit, Rank rank) {
		switch (rank) {
		case ace:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_ace;
			case diamonds:
				return R.drawable.diamonds_ace;
			case hearts:
				return R.drawable.hearts_ace;
			case spades:
				return R.drawable.spades_ace;
			}
		case eight:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_8;
			case diamonds:
				return R.drawable.diamonds_8;
			case hearts:
				return R.drawable.hearts_8;
			case spades:
				return R.drawable.spades_9;
			}
		case five:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_5;
			case diamonds:
				return R.drawable.diamonds_5;
			case hearts:
				return R.drawable.hearts_5;
			case spades:
				return R.drawable.spades_5;
			}
		case four:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_4;
			case diamonds:
				return R.drawable.diamonds_4;
			case hearts:
				return R.drawable.hearts_4;
			case spades:
				return R.drawable.spades_4;
			}
		case jack:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_jack;
			case diamonds:
				return R.drawable.diamonds_jack;
			case hearts:
				return R.drawable.hearts_jack;
			case spades:
				return R.drawable.spades_jack;
			}
		case king:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_king;
			case diamonds:
				return R.drawable.diamonds_king;
			case hearts:
				return R.drawable.hearts_king;
			case spades:
				return R.drawable.spades_king;
			}
		case nine:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_9;
			case diamonds:
				return R.drawable.diamonds_9;
			case hearts:
				return R.drawable.hearts_9;
			case spades:
				return R.drawable.spades_9;
			}
		case queen:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_queen;
			case diamonds:
				return R.drawable.diamonds_queen;
			case hearts:
				return R.drawable.hearts_queen;
			case spades:
				return R.drawable.spades_queen;
			}
		case seven:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_7;
			case diamonds:
				return R.drawable.diamonds_7;
			case hearts:
				return R.drawable.hearts_7;
			case spades:
				return R.drawable.spades_7;
			}
		case six:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_6;
			case diamonds:
				return R.drawable.diamonds_6;
			case hearts:
				return R.drawable.hearts_6;
			case spades:
				return R.drawable.spades_6;
			}
		case ten:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_10;
			case diamonds:
				return R.drawable.diamonds_10;
			case hearts:
				return R.drawable.hearts_10;
			case spades:
				return R.drawable.spades_10;
			}
		case three:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_3;
			case diamonds:
				return R.drawable.diamonds_3;
			case hearts:
				return R.drawable.hearts_3;
			case spades:
				return R.drawable.spades_3;
			}
		case two:
			switch (suit) {
			case clubs:
				return R.drawable.clubs_2;
			case diamonds:
				return R.drawable.diamonds_2;
			case hearts:
				return R.drawable.hearts_2;
			case spades:
				return R.drawable.spades_2;
			}
		}
		return 0;
	}

}
