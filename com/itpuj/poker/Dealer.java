package com.itpuj.poker;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	private List<Player> betOrder, dealOrder, players, tempDealOrder;
	
	private Deck deck, tempDeck;
	private Player playerWithButton;
	private int tempIndex, shuffleCount;

	public Dealer() {
		this.deck = null;
		this.players = null;
		this.dealOrder = null;
		this.betOrder = null;
	}
	
	public int collectPostedBlinds(List<Player> players) {
		int amt = 0;
		for (Player p : players) {
			amt += p.postBlind();
		}
		return amt;
	}
	
	public void dealHoleCards(List<Player> players) {
		Card holeCard;
		for (int i = 0; i < Consts.MAX_HOLE_CARDS; i++) {
			for (Player player : players) {
				holeCard = this.takeCardFromTopOfDeck();
				holeCard.setHoleCard();
				player.receiveCard(holeCard);
				if (i == 1) {
					System.out.println(player.getName() + " has cards " + player.getHoleCards());
				}
			}
		}
	}
	
	public Player getButtonPlacement() {
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			if (players.get(i).hasButton()) {
				playerWithButton = players.get(i);
			}
		}
		return playerWithButton;
	}
	
	public void shuffleDeck() {
		
		deck.unsetShuffled();
		shuffleCount = 0;
		System.out.println("Shuffling deck...");
		tempIndex = (int) (Math.random() * 52);
		tempDeck = new Deck();
		
		for (Card card : deck.getDeck()) {
			tempDeck.getDeck().add(card);
		}
		tempDeck.getDeck().clear();
		
		while (!deck.isShuffled() && tempDeck.getDeck().size() < 52) {
			Card randomCard = deck.getDeck().get((int) (Math.random() * deck.getDeck().size()));
			if (!tempDeck.getDeck().contains(randomCard)) {
				tempDeck.getDeck().add(randomCard);
			}
			if (tempDeck.getDeck().size() == 52) {
				deck.getDeck().clear();
				for (Card card : tempDeck.getDeck()) {
					deck.getDeck().add(card);
				}
				shuffleCount++;
			}
			if (shuffleCount == 3 && tempDeck.getDeck().size() == 52) {
				deck.setShuffled();
			} else {
				tempDeck.clearDeck();
			}
		}
	}
	
	public void giveButtonToPlayer(int n) {
		players.get(n).setButton();
		System.out.println(players.get(n).getName() + " has the button at index " + n + ".");
	}
	
	
	public void greetPlayers(List<Player> players) {
		this.players = players;
		this.dealOrder = new ArrayList<Player>();
		this.betOrder = new ArrayList<Player>();
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			dealOrder.add(players.get(i));
		}
		this.tempDealOrder = new ArrayList<Player>();
	}
	
	public int moveButton(int n) {
		for (Player player : players) {
			if (player.hasButton()) {
				player.unsetButton();
			}
		}
		n++;
		if (n == 3) {
			n = 0;
		}
		giveButtonToPlayer(n);
		return n;
	}
	
	public void pushPot(int n) {
		players.get((int) (Math.random() * Consts.MAX_PLAYERS)).addChips(n);
	}
	
	public void setBlinds() {
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			players.get(i).unsetSmallBlind();
			players.get(i).unsetBigBlind();
		}
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			if (players.get(i).hasButton()) {
				if (i == Consts.MAX_PLAYERS - 1) {
					players.get(0).setSmallBlind();
					players.get(1).setBigBlind();
				} else {
					players.get(i + 1).setSmallBlind();
					if (i == Consts.MAX_PLAYERS - 2) {
						players.get(0).setBigBlind();
					} else {
						players.get(2).setBigBlind();
					}
				}
			}
		}
	}
	
	public List<Player> setBetOrder(List<Player> players) {
		betOrder.clear();
		int betOrderIndexNum = 0;
		int betOrderSizeAfterFirstPass = 0;
		int bigBlindIndexNum = 0;
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			if (players.get(i).hasBigBlind()) {
				bigBlindIndexNum = i;
			}
		}
		if (bigBlindIndexNum == Consts.MAX_PLAYERS - 1) {
			betOrderIndexNum = 0;
		} else {
			betOrderIndexNum = bigBlindIndexNum + 1;
		}
		for (int i = betOrderIndexNum; i < Consts.MAX_PLAYERS; i++) {
				betOrder.add(players.get(i));
		}
		betOrderSizeAfterFirstPass = betOrder.size();
		if (betOrderIndexNum != 0) {
			for (int i = 0; i < Consts.MAX_PLAYERS - betOrderSizeAfterFirstPass; i++) {
				betOrder.add(players.get(i));
			}
		}
		return betOrder;
	}
	
	public List<Player> setDealOrder(List<Player> players) {
		
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			tempDealOrder.add(players.get(i));
		}
		dealOrder.clear();
		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			Player p = tempDealOrder.get(i);
			dealOrder.add(p);
		}

		for (int i = 0; i < Consts.MAX_PLAYERS; i++) {
			if (dealOrder.get(i).hasButton()) {
				Player p = dealOrder.remove(i);
				dealOrder.add(Consts.MAX_PLAYERS - 1, p);
			}
		}
		for (Player player : dealOrder) {
			if (player.hasButton()) {
				player.unsetButton();
			}
		}
		dealOrder.get(0).setButton();
		tempDealOrder.clear();
		return dealOrder;
	}
	
	public void spreadDeck() {
		for (Card card : deck.getDeck()) {
			System.out.println(card);
		}
	}
	
	public Card takeCardFromTopOfDeck() {
		return deck.takeTopCard();
	}
	
	public void takeDeck(Deck deck) {
		this.deck = deck;
	}
}