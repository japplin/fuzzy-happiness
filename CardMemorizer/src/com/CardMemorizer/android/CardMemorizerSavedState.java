package com.CardMemorizer.android;

import java.util.ArrayList;

public class CardMemorizerSavedState {

	private static CardMemorizerSavedState instance;
	private ArrayList<Card> shuffledDeck;
	private ArrayList<Card> selectorDeck;
	private boolean isRunning = false;
	private Card selectedCard;

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

	public void setShuffledDeck(ArrayList<Card> shuffledDeck) {
		this.shuffledDeck = shuffledDeck;
	}

	public void setSelectorDeck(ArrayList<Card> selectorDeck) {
		this.selectorDeck = selectorDeck;
	}

	public ArrayList<Card> getShuffledDeck() {
		return shuffledDeck != null ? shuffledDeck : null;
	}

	public ArrayList<Card> getSelectorDeck() {
		return selectorDeck != null ? selectorDeck : null;
	}
	
	public Card getSelectedCard() {
		return selectedCard;
	}
	
	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}

	public void unSelectSelectedCard() {
		if (selectedCard != null) {
			this.selectedCard.setSelectedState(false);
		}
	}

}
