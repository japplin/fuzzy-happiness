package com.CardMemorizer.android;

import android.content.Context;

public class CardMemorizer extends android.app.Application {
	
	private static CardMemorizer instance;

	public CardMemorizer() {
		instance = this;
	}

	public static Context getContext() {
		return instance;
	}

}
