package com.CardMemorizer.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class GuessesLeftActionItem extends TextView implements GuessesLeft {
	public GuessesLeftActionItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void guessesLeftChanged(int guessesLeft) {
		if (guessesLeft > 0) {
			setVisibility(View.VISIBLE);
			setText(getResources().getString(R.string.guesses) + ": " + guessesLeft);
		} else {
			setVisibility(View.GONE);
		}
	}
}
