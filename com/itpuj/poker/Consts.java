package com.itpuj.poker;

public final class Consts {
	
	public static final int BIG_BLIND_AMT = 10;
	public static final int MAX_PLAYERS = 3;
	public static final int MAX_HOLE_CARDS = 2;
	public static final int SMALL_BLIND_AMT = 5;
	
	public Consts() {}
	
	public enum Rank { DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
}
