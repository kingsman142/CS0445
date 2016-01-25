/*
James Hahn

This program manages the a hand of cards.  Two instances of this class will be used; one for the player, and one for the computer.
*/

public class Hand{
	private RandIndexQueue<Card> cards = new RandIndexQueue<Card>(22); //Maximum amount of cards that could ever possibly be in a hand when losing (22 aces with 6 decks in the shoe)
	private String name;

	public Hand(String user){
		name = user;
	}

	public void dealCard(Card card){
		cards.addItem(card);
		System.out.println(name + " dealt: " + card.toString());
	}

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

	public RandIndexQueue<Card> getCards(){
		return cards;
	}

	public String toString(){
		return cards.toString();
	}
}
