package com.CardMemorizer.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout {
	private int id;

	public Card(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

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
