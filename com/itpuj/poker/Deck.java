package com.itpuj.poker;

import java.util.List;
import java.util.ArrayList;
import com.itpuj.poker.Consts.*;

public class Deck {

	private List<Card> deck = new ArrayList<Card>();
	private Card cardFromDeck;
	private boolean shuffled;
	
	public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        shuffled = false;
    }
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public List<Card> clearDeck() {
		return deck;
	}
	
	public boolean isShuffled() {
		return shuffled;
	}
	
	public void setShuffled() {
		shuffled = true;
	}
	
	public void unsetShuffled() {
		shuffled = false;
	}
	
	public Card takeTopCard() {
		cardFromDeck = deck.remove(0);
		return cardFromDeck;
	}
}
