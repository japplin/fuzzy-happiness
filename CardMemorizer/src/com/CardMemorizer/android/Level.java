package com.CardMemorizer.android;

import java.util.Set;

import android.content.SharedPreferences;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class Level {

	public static final int CUSTOM_LEVEL = -1;
	public static final String LEVEL_PREFS = "LEVEL_PREFS";
	public static final String LEVEL_COMPLETION = "LEVEL_COMPLETION";

	private Set<Suit> suits;
	private Set<Rank> ranks;
	private int levelId;
	private int deckSize;
	private int guesses;
	private boolean hasBeenCompleted;

	public Level(Set<Suit> suits, Set<Rank> ranks, int deckSize, int guesses, int levelId) {
		this.suits = suits;
		this.ranks = ranks;
		this.deckSize = deckSize;
		this.guesses = guesses;
		this.levelId = levelId;
		this.hasBeenCompleted = CardMemorizer.getContext().getSharedPreferences(LEVEL_PREFS, 0).getBoolean(LEVEL_COMPLETION + levelId, false);

	}
	
	public Set<Suit> getSuits() {
		return suits;
	}

	public Set<Rank> getRanks() {
		return ranks;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public int getGuesses() {
		return guesses;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	public boolean hasBeenCompleted() {
		return hasBeenCompleted;
	}
	
	public void setLevelCompleted() {
		SharedPreferences.Editor editor = CardMemorizer.getContext().getSharedPreferences(LEVEL_PREFS, 0).edit();
		editor.putBoolean(LEVEL_COMPLETION + levelId, true);
		editor.commit();
	}

}
