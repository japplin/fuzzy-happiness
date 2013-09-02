package com.CardMemorizer.android;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CardMemorizerSavedState {

	private static CardMemorizerSavedState instance;
	private boolean isRunning = false;
	HashMap<Integer, Bitmap> imageCache = new HashMap<Integer, Bitmap>();

	public synchronized static CardMemorizerSavedState getInstance() {
		if (instance == null) {
			instance = new CardMemorizerSavedState();
		}
		return instance;
	}

	private CardMemorizerSavedState() {

	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Bitmap getImageFromCache(int id, Context context) {
		Bitmap bitmap = imageCache.get(id);
		if (bitmap == null) {
			bitmap = BitmapFactory.decodeResource(context.getResources(), id);
			imageCache.put(id, bitmap);
		}
		return bitmap;
	}

}
