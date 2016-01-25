/*
James Hahn

This is the Shoe class for the Blackjack game stored in Blackjack.java.  The size of the shoe is determined by
52 (number of cards in a standard deck) multiplied by a constructor argument, multiples, meaning how many decks are
in the shoe.  The variable multiples is passed into Blackjack as the first command-line argument.
*/

import java.util.*;

public class Shoe{
	private int numCards;
	private RandIndexQueue<Card> cards;

	//Construct the shoe of n decks
	public Shoe(int multiples){
		numCards = 52*multiples;
		cards = new RandIndexQueue<Card>(numCards);

		for(int i = 0; i < multiples; i++){
			for(int k = 0; k < Card.Suits.values().length; k++){
				for(int l = 0; l < Card.Ranks.values().length; l++){
					cards.addItem(new Card(Card.Suits.values()[k], Card.Ranks.values()[l]));
				}
			}
		}
	}

	//Return the deck/shoe
	public RandIndexQueue<Card> getDeck(){
		return cards;
	}
}
