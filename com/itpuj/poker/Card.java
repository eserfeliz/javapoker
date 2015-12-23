package com.itpuj.poker;

import com.itpuj.poker.Consts.*;

public class Card {
	
	private Rank rank;
	private Suit suit;
	private boolean isHole, isBurned, isComm, isMucked;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
        this.suit = suit;
        this.isHole = false;
        this.isBurned = false;
        this.isComm = false;
        this.isMucked = false;
	}

	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Card getFace(Card card) {
		return card;
	}
	
	public void setHoleCard() {
		this.isHole = true;
	}
	
	public String toString() {
		return rank + " " + suit;
	}
	
	public void unsetHoleCard() {
		this.isHole = false;
	}
}
