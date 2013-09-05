package com.CardMemorizer.android;

import android.content.Context;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout implements AnimationListener {
	public enum Suit {
		spades(R.string.spades), clubs(R.string.clubs), hearts(R.string.hearts), diamonds(R.string.diamonds);

		private int id;

		private Suit(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	};

	public enum Rank {
		ace(R.string.ace), two(R.string.two), three(R.string.three), four(R.string.four), five(R.string.five), six(R.string.six), seven(R.string.seven), eight(R.string.eight), nine(
				R.string.nine), ten(R.string.ten), jack(R.string.jack), queen(R.string.queen), king(R.string.king);

		private int id;

		private Rank(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

	};

	private int id;
	private CardInfo cardInfo;
	private ImageView cardBack;
	private ImageView cardFront;
	private final Context context;
	private Animation cardOutAnimation;
	private Animation cardInAnimation;
	private Animation cardShakeAnimation;

	public Card(final Context context, CardInfo cardInfo) {
		super(context);
		this.context = context;
		setGravity(Gravity.CENTER);

		cardShakeAnimation = AnimationUtils.loadAnimation(context, R.anim.shake);
		
		
		cardOutAnimation = AnimationUtils.loadAnimation(context, R.anim.card_flip_out);
		cardOutAnimation.setAnimationListener(this);
		cardOutAnimation.setInterpolator(new AccelerateInterpolator());

		cardInAnimation = AnimationUtils.loadAnimation(context, R.anim.card_flip_in);
		cardInAnimation.setInterpolator(new DecelerateInterpolator());

		cardFront = new ImageView(context);
		cardBack = new ImageView(context);

		cardBack.setImageResource(R.drawable.cardback_blue);
		cardBack.setVisibility(cardInfo.isBackShowing() ? View.VISIBLE : View.GONE);

		setCardInfo(cardInfo);
		addView(cardFront);
		addView(cardBack);

		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Card.this.cardInfo.isBackShowing()) {
					new CardSelectionDialog(Card.this.context, Card.this).show();
				}
			}
		});
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
		this.id = getImageId();

		cardFront.setImageBitmap(CardMemorizerSavedState.getInstance().getImageFromCache(id, context));
		shouldShowBack(cardInfo.isBackShowing());
	}

	public void shake() {
		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(250);
		cardBack.startAnimation(cardShakeAnimation);
	}

	public void flip() {
		cardBack.startAnimation(cardOutAnimation);
	}

	public Rank getRank() {
		return cardInfo.getRank();
	}

	public Suit getSuit() {
		return cardInfo.getSuit();
	}

	public void shouldShowBack(boolean shouldShowBack) {
		cardInfo.isBackShowing(shouldShowBack);
		cardBack.setVisibility(cardInfo.isBackShowing() ? View.VISIBLE : View.GONE);
		cardFront.setVisibility(cardInfo.isBackShowing() ? View.GONE : View.VISIBLE);
	}

	private int getImageId() {
		switch (cardInfo.getRank()) {
		case ace:
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
			case clubs:
				return R.drawable.clubs_8;
			case diamonds:
				return R.drawable.diamonds_8;
			case hearts:
				return R.drawable.hearts_8;
			case spades:
				return R.drawable.spades_8;
			}
		case five:
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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
			switch (cardInfo.getSuit()) {
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

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		shouldShowBack(false);

		cardFront.startAnimation(cardInAnimation);

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

}
