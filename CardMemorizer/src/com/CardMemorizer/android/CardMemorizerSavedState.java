package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

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

	public void loadLevel(Level curLevel, Activity activity) {

		ArrayList<CardInfo> deck = createDeck(curLevel);
		Collections.shuffle(deck);
		Intent intent = new Intent(activity, MainActivity.class);
		intent.putParcelableArrayListExtra(CustomGameCreation.DECK_INFO, deck);

		activity.startActivity(intent);
	}

	private ArrayList<CardInfo> createDeck(Level curLevel) {
		ArrayList<CardInfo> deck = new ArrayList<CardInfo>(curLevel.getDeckSize());

		while (deck.size() < curLevel.getDeckSize()) {
			OUTER_LOOP: for (Rank rank : curLevel.getRanks()) {
				for (Suit suit : curLevel.getSuits()) {
					deck.add(new CardInfo(suit, rank, false));
					if (deck.size() == curLevel.getDeckSize()) {
						break OUTER_LOOP;
					}
				}
			}
		}
		return deck;
	}

}
