package com.itpuj.poker;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	private static List<Player> players, dealOrder;
	private static Table table;
	private static Deck deck;
	private static Dealer dealer;
	
	private static int buttonNum;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numOfTurns = 0;
		
		players = new ArrayList<Player>(Consts.MAX_PLAYERS);
		dealOrder = new ArrayList<Player>(players);
		buttonNum = 0;
		
		init();
		
		while (numOfTurns < 4) {
			dealer.setBlinds();
			dealOrder = dealer.setDealOrder(dealOrder);
			dealer.dealHoleCards(dealOrder);
			System.out.println("Game is played...showdown...and the game is done!");
			buttonNum = dealer.moveButton(buttonNum);
			System.out.println(dealer.getButtonPlacement().getName() + " now has the button.");
			numOfTurns++;
		}
	}
	
	
	
	private static void init() {
		table = new Table();
		deck = new Deck();
		dealer = new Dealer();
		dealer.takeDeck(deck);
		seatPlayers(players, Consts.MAX_PLAYERS);
		dealer.greetPlayers(players);
		
		dealer.giveButtonToPlayer(buttonNum);
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			dealOrder.add(players.get(i));
		}
		dealer.shuffleDeck();
	}
	
	private static void seatPlayers(List<Player> players, int numOfPlayers) {
		for (int i = 0; i < numOfPlayers; i++) {
			players.add(i, new Player(i + 1, 1500));
		}
	}

}