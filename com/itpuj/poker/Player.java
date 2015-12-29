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
	
	public int addChips(int n) {
		this.chips += n;
		return this.chips;
	}
	
	public int deductChips(int n) {
		this.chips -= n;
		return this.chips;
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
	
	public int postBlind() {
		int amt = 0;
		if (this.hasBigBlind()) {
			this.chips -= Consts.BIG_BLIND_AMT;
			amt = Consts.BIG_BLIND_AMT;
		} else if (this.hasSmallBlind()) {
			this.chips -= Consts.SMALL_BLIND_AMT;
			amt = Consts.SMALL_BLIND_AMT;
		}
		return amt;
	}
	
	public void receiveCard(Card card) {
		if (hand.size() < 2) {
			hand.add(card);
		}
	}
	
	public void reportStatus(Player p) {
		System.out.println(this.playerName + " has " + this.getHoleCards() + " and " + this.getChips() + ".");
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
