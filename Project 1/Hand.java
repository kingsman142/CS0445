/*
James Hahn

This program manages the a hand of cards.  Two instances of this class will be used; one for the player, and one for the computer.
*/

public class Hand{
	private RandIndexQueue<Card> cards = new RandIndexQueue<Card>(22); //Maximum amount of cards that could ever possibly be in a hand when losing (22 aces with 6 decks in the shoe)
	private String name;

	//Name is used to keep track of who's hand it is
	public Hand(String user){
		name = user;
	}

	//Deal a card to this hand
	public void dealCard(Card card){
		cards.addItem(card);
		System.out.println(name + " dealt: " + card.toString());
	}

	//Calculate the sum of the hand
	public int sum(){
		int sum1 = 0;
		int sum2 = 0;

		for(int i = 0; i < cards.size(); i++){
			sum1 += cards.get(i).value();
			sum2 += cards.get(i).value2();
		}

		if(sum1 > 21) return sum2;
		else return sum1;
	}

	//Return the cards in the hand
	public RandIndexQueue<Card> getCards(){
		return cards;
	}

	//Return the contents of the hand
	public String toString(){
		return cards.toString();
	}
}
