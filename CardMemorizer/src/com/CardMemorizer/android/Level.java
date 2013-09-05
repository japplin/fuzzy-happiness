package com.CardMemorizer.android;

import java.util.Set;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class Level {

	public static final int CUSTOM_LEVEL = -1;
	
	private Set<Suit> suits;

	private Set<Rank> ranks;
	
	private int levelId;

	private int deckSize;

	private int guesses;

	public Level(Set<Suit> suits, Set<Rank> ranks, int deckSize, int guesses, int levelId) {
		this.suits = suits;
		this.ranks = ranks;
		this.deckSize = deckSize;
		this.guesses = guesses;
		this.levelId = levelId;
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

}
