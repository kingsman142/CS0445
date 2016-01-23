/*
James Hahn

This program manages the a hand of cards.  Two instances of this class will be used; one for the player, and one for the computer.
*/

public class Hand{
	private RandIndexQueue<Card> cards = new RandIndexQueue<Card>(12); //Maximum amount of cards that could ever possibly be in a hand when losing (4 aces, 4 2s, 3 3s)
	private String name;

	public Hand(String user){
		name = user;
	}

	public void dealCard(Card card){
		cards.addItem(card);
		System.out.println(name + " dealt: " + card.toString());
	}

	public int sum(){
		int sum = 0;

		for(int i = 0; i < cards.size()+1; i++){
			if((sum + cards.get(i).value()) > 21) sum += cards.get(i).value2();
			else if((sum + cards.get(i).value()) <= 21) sum += cards.get(i).value();
		}

		return sum;
	}

	public RandIndexQueue<Card> getCards(){
		return cards;
	}

	public String toString(){
		return cards.toString();
	}
}
