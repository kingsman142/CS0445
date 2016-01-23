/*
James Hahn
*/

public class Blackjack{
	private static int deckMultiples;
	private static int numRounds;
	private static Shoe deck; //Shoe for the cards
	private static Hand playerHand; //Player's hand
	private static Hand dealerHand; //Computer's hand
	private static RandIndexQueue<Card> discardPile;
	private static int dealerWins;
	private static int playerWins;
	private static int ties;

	public static void main(String[] args){
		deck = new Shoe(Integer.parseInt(args[0]));
		numRounds = Integer.parseInt(args[1]);
		playerHand = new Hand("Player");
		dealerHand = new Hand("Dealer");
		discardPile = new RandIndexQueue<Card>(deck.getDeck().size());

		deck.getDeck().shuffle();

		for(int i = 0; i < numRounds; i++){
			System.out.printf("\nRound %d!\n", i+1);
			System.out.println("Size of shoe: " + deck.getDeck().size());

			playerHand.dealCard(deck.getDeck().removeItem());
			dealerHand.dealCard(deck.getDeck().removeItem());
			playerHand.dealCard(deck.getDeck().removeItem());
			dealerHand.dealCard(deck.getDeck().removeItem());
			System.out.printf("Player | %s: %d\n", playerHand.toString(), playerHand.sum());
			System.out.printf("Dealer | %s: %d\n", dealerHand.toString(), dealerHand.sum());

			while(true){
				if(playerHand.sum() < 17) playerHand.dealCard(deck.getDeck().removeItem());

				if(playerHand.sum() <= 21 && dealerHand.sum() < 17) dealerHand.dealCard(deck.getDeck().removeItem());

				if(playerHand.sum() > 21 || dealerHand.sum() > 21) break;
				else if(playerHand.sum() >= 17 && dealerHand.sum() >= 17) break;
			}

			//STAND and BUST statements
			if(playerHand.sum() <= 21) System.out.printf("Player | STANDS | %s: %d\n", playerHand.toString(), playerHand.sum());
			else System.out.printf("Player | BUSTS | %s: %d\n", playerHand.toString(), playerHand.sum());

			if(dealerHand.sum() <= 21) System.out.printf("Dealer | STANDS | %s: %d\n", dealerHand.toString(), dealerHand.sum());
			else System.out.printf("Dealer | BUSTS | %s: %d\n", dealerHand.toString(), dealerHand.sum());

			//Win conditions
			if(playerHand.sum() == 21 && dealerHand.sum() == 21 && (playerHand.getCards().size() == dealerHand.getCards().size())){ //Both get blackjack in 2 cards
				System.out.println("It's a tie!");
				ties++;
			} else if(dealerHand.sum() != 21 && playerHand.sum() == 21 && playerHand.getCards().size() == 2){ //Player got a blackjack and the dealer didn't
				System.out.println("The player got a blackjack and wins!");
				playerWins++;
			} else if(playerHand.sum() != 21 && dealerHand.sum() == 21 && dealerHand.getCards().size() == 2){ //Dealer got a blackjack and the player didn't
				System.out.println("The dealer got a blackjack and wins!");
				dealerWins++;
			} else if(playerHand.sum() == 21 && dealerHand.sum() == 21 && playerHand.getCards().size() < dealerHand.getCards().size()){ //Player gets 21 in less cards
				System.out.println("The player wins because they got 21 first!");
				playerWins++;
			} else if(playerHand.sum() == 21 && dealerHand.sum() == 21 && playerHand.getCards().size() > dealerHand.getCards().size()){ //Dealer gets 21 in less cards
				System.out.println("The dealer wins because they got a 21 first!");
				dealerWins++;
			} else if(playerHand.sum() <= 21 && dealerHand.sum() > 21){  //Dealer busts
				System.out.println("The player wins!");
				playerWins++;
			} else if(playerHand.sum() > 21 && dealerHand.sum() <= 21){ //Player busts
				System.out.println("The dealer wins!");
				dealerWins++;
			} else if(playerHand.sum() > 21 && dealerHand.sum() > 21){ //Player and Dealer both bust
				System.out.println("Both people busted and tied!");
				ties++;
			} else if(playerHand.sum() <= 21 && dealerHand.sum() <= 21){ //Player and Dealer both stand
				if(playerHand.sum() < dealerHand.sum()){ //Dealer had a higher sum
					System.out.println("The dealer wins!");
					dealerWins++;
				} else if(playerHand.sum() > dealerHand.sum()){ //Player had a higher sum
					System.out.println("The player wins!");
					playerWins++;
				} else if(playerHand.sum() == dealerHand.sum()){ //Player and dealer had the same sum
					System.out.println("It's a tie!");
					ties++;
				}
			}

			//Clear both players' hands
			discardCards();

			//Prepare for next round
			//Add discard pile to shoe if shoe size is less than 1/4 the original size of shoe
			if(deck.getDeck().size() < ((Integer.parseInt(args[0])*52)*.25)){
				while(discardPile.size() > 0){
					deck.getDeck().addItem(discardPile.removeItem());
				}

				System.out.printf("\nReshuffling the deck for round %d!\n", i+2);
				deck.getDeck().shuffle();
			}
		}

		//Print out match statistics
		System.out.println("\n-----MATCH STATISTICS-----");
		System.out.printf("Rounds played: %s\n", args[1]);
		System.out.printf("Dealer wins: %d\n", dealerWins);
		System.out.printf("Player wins: %d\n", playerWins);
		System.out.printf("Ties: %d\n", ties);
	}

	//Get rid of the cards in the player's hands and put them in the discard pile
	public static void discardCards(){
		while(playerHand.getCards().size() > 0){
			discardPile.addItem(playerHand.getCards().removeItem());
		}

		while(dealerHand.getCards().size() > 0){
			discardPile.addItem(dealerHand.getCards().removeItem());
		}

		playerHand.getCards().clear();
		dealerHand.getCards().clear();
	}
}
