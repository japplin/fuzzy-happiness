package com.CardMemorizer.android;

import android.os.Parcel;
import android.os.Parcelable;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class CardInfo implements Parcelable {
	private Suit suit;
	private Rank rank;
	private boolean isBackShowing;

	public CardInfo(Suit suit, Rank rank, boolean isBackShowing) {
		this.suit = suit;
		this.rank = rank;
		this.isBackShowing = isBackShowing;
	}

	public CardInfo(Parcel in) {
		suit = Suit.values()[in.readInt()];
		rank = Rank.values()[in.readInt()];
		isBackShowing = in.readByte() == 1;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Rank getRank() {
		return rank;
	}

	public void isBackShowing(boolean isBackShowing) {
		this.isBackShowing = isBackShowing;
	}

	public boolean isBackShowing() {
		return isBackShowing;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(suit.ordinal());
		dest.writeInt(rank.ordinal());
		dest.writeByte((byte) (isBackShowing ? 1 : 0));
	}

	public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator<CardInfo>() {

		@Override
		public CardInfo createFromParcel(Parcel source) {
			return new CardInfo(source);
		}

		@Override
		public CardInfo[] newArray(int size) {
			return new CardInfo[size];
		}
	};

}
