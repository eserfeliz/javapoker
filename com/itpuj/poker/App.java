package com.itpuj.poker;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	private static List<Player> players, betOrder, dealOrder;
	private static Table table;
	private static Deck deck;
	private static Dealer dealer;
	
	private static int buttonNum;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numOfTurns = 0;
		
		players = new ArrayList<Player>(Consts.MAX_PLAYERS);
		betOrder = new ArrayList<Player>(Consts.MAX_PLAYERS);
		dealOrder = new ArrayList<Player>(players);
		buttonNum = 0;
		
		init();
		
		while (numOfTurns < 4) {
			dealer.setBlinds();
			for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
				if (players.get(i).hasBigBlind()) {
					System.out.println(players.get(i).getName() + " has the big blind.");
				}
			}
			table.addToPot(dealer.collectPostedBlinds(players));
			dealOrder = dealer.setDealOrder(dealOrder);
			betOrder = dealer.setBetOrder(players);
			dealer.dealHoleCards(dealOrder);
			for (Player p : players) {
				p.reportStatus(p);
			}
			System.out.println("The bet order is as follows:");
			for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
				System.out.println(betOrder.get(i).getName());
			}
			System.out.println("Game is played...showdown...and the game is done!");
			dealer.pushPot(table.awardPot());
			for (Player p : players) {
				p.reportStatus(p);
			}
			table.clearPot();
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
		players = table.seatPlayers(players, Consts.MAX_PLAYERS);
		dealer.greetPlayers(players);
		
		dealer.giveButtonToPlayer(buttonNum);
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			dealOrder.add(players.get(i));
		}
		dealer.shuffleDeck();
	}	

}