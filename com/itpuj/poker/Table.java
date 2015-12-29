package com.itpuj.poker;

import java.util.List;

public class Table {
	
	private int pot = 0;
	private int award = 0;

	public Table() {
		this.pot = pot;
	}
	
	public void addToPot(int n) {
		this.pot += n;
	}
	
	public int awardPot() {
		award = this.pot;
		return award;
	}
	
	public void clearPot() {
		this.pot = 0;
	}
	
	public int getPot() {
		return pot;
	}
	
	public List<Player> seatPlayers(List<Player> players, int numOfPlayers) {
		for (int i = 0; i < numOfPlayers; i++) {
			players.add(i, new Player(i + 1, 1500));
		}
		return players;
	}
}
