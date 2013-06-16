package com.CardMemorizer.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout {
	public enum suit {
		spade, club, heart, diamond
	};

	public enum rank {
		ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king
	};

	private int id;

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
}
