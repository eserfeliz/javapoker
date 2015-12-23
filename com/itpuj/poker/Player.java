package com.itpuj.poker;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Card> hand = new ArrayList<Card>(2);
	private String playerName, handString;
	private boolean button, bigBlind, smallBlind;
	private int chips;
	
	public Player(int num, int chips) {
		this.playerName = "Player " + num;
		this.chips = chips;
		this.button = false;
	}
	
	public int getChips() {
		return chips;
	}

	public String getHoleCards() {
		handString = "";
		for (Card card : hand) {
			if (handString.equals("")) {
				handString += card.toString();
			} else {
				handString += " and " + card.toString();
			}
		}
		return handString;
	}
	
	public String getName() {
		return playerName;
	}
	
	public boolean hasBigBlind() {
		return bigBlind;
	}
		
	public boolean hasButton() {
		return button;
	}
	
	public boolean hasSmallBlind() {
		return smallBlind;
	}
	
	public void receiveCard(Card card) {
		if (hand.size() < 2) {
			hand.add(card);
		}
	}
	
	public void setBigBlind() {
		this.bigBlind = true;
	}
	
	public void setButton() {
		this.button = true;
	}
	
	public void setSmallBlind() {
		this.smallBlind = true;
	}
	
	public void unsetButton() {
		this.button = false;
	}
	
	public void unsetBigBlind() {
		this.bigBlind = false;
	}	
	
	public void unsetSmallBlind() {
		this.smallBlind = false;
	}
}
