package com.CardMemorizer.android;

import java.util.ArrayList;

public class CardMemorizerSavedState {

	private static CardMemorizerSavedState instance;
	private ArrayList<Card> shuffledDeck;
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

	public ArrayList<Card> getShuffledDeck() {
		return shuffledDeck != null ? shuffledDeck : null;
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
