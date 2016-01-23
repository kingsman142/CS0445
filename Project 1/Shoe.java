/*
James Hahn
*/

import java.util.*;

public class Shoe{
	private int numCards;
	private RandIndexQueue<Card> cards;

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

	public RandIndexQueue<Card> getDeck(){
		return cards;
	}
}
