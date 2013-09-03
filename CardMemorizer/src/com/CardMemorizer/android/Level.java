package com.CardMemorizer.android;

import java.util.Set;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class Level {

	private Set<Suit> suits;

	private Set<Rank> ranks;

	private int deckSize;

	public Level(Set<Suit> suits, Set<Rank> ranks, int deckSize) {
		this.suits = suits;
		this.ranks = ranks;
		this.deckSize = deckSize;
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

}
