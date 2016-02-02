/*
James Hahn

This is the main java program file that manages a game of Blackjack.
The number of cards is determined by a command line argument describing how many decks are in the shoe (second argument).
The number of rounds played is also determined by a command line argument (first argument).
*/

public class Blackjack{
	private static int deckMultiples;
	private static int numRounds;
	private static Shoe deck; //Shoe for the cards
	private static Hand playerHand; //Player's hand
	private static Hand dealerHand; //Computer's hand
	private static RandIndexQueue<Card> discardPile; //Discard pile

	//Keep track of who wins each round
	private static int dealerWins;
	private static int playerWins;
	private static int ties;

	//Every statement with the condition if(Integer.parseInt(args[0]) <= 10) prevents text from being output
	//if the # of input rounds is more than 10.  This makes it so if it the program runs 10000 rounds, for example,
	//it would only print out the rounds where it reshuffled the shoe and the match statistics.
	public static void main(String[] args){
		deck = new Shoe(Integer.parseInt(args[1]));
		numRounds = Integer.parseInt(args[0]);
		playerHand = new Hand("Player");
		dealerHand = new Hand("Dealer");
		discardPile = new RandIndexQueue<Card>(deck.getDeck().size());

		System.out.println("Starting Blackjack with " + args[0] + " rounds and " + args[1] + " decks in the shoe.");

		deck.getDeck().shuffle();

		for(int i = 0; i < numRounds; i++){
			if(Integer.parseInt(args[0]) <= 10) System.out.printf("\nRound %d!\n", i+1);

			//Deal out the initial 2 cards per player
			if(Integer.parseInt(args[0]) <= 10){
				playerHand.dealCard(deck.getDeck().removeItem(), true);
				dealerHand.dealCard(deck.getDeck().removeItem(), true);
				playerHand.dealCard(deck.getDeck().removeItem(), true);
				dealerHand.dealCard(deck.getDeck().removeItem(), true);
			} else{
				playerHand.dealCard(deck.getDeck().removeItem(), false);
				dealerHand.dealCard(deck.getDeck().removeItem(), false);
				playerHand.dealCard(deck.getDeck().removeItem(), false);
				dealerHand.dealCard(deck.getDeck().removeItem(), false);
			}
			if(Integer.parseInt(args[0]) <= 10) System.out.printf("Player | %s: %d\n", playerHand.toString(), playerHand.sum());
			if(Integer.parseInt(args[0]) <= 10) System.out.printf("Dealer | %s: %d\n", dealerHand.toString(), dealerHand.sum());

			//Game loop for player
			if(Integer.parseInt(args[0]) <= 10) while(playerHand.sum() < 17) playerHand.dealCard(deck.getDeck().removeItem(), true);
			else while(playerHand.sum() < 17) playerHand.dealCard(deck.getDeck().removeItem(), false);

			if(Integer.parseInt(args[0]) <= 10){
				if(playerHand.sum() <= 21) System.out.printf("Player | STANDS | %s: %d\n", playerHand.toString(), playerHand.sum());
				else System.out.printf("Player | BUSTS | %s: %d\n", playerHand.toString(), playerHand.sum());
			}

			//Game loop for dealer
			if(Integer.parseInt(args[0]) <= 10) while(dealerHand.sum() < 17 && playerHand.sum() <= 21) dealerHand.dealCard(deck.getDeck().removeItem(), true);
			else while(dealerHand.sum() < 17 && playerHand.sum() <= 21) dealerHand.dealCard(deck.getDeck().removeItem(), false);

			if(Integer.parseInt(args[0]) <= 10){
				if(dealerHand.sum() <= 21) System.out.printf("Dealer | STANDS | %s: %d\n", dealerHand.toString(), dealerHand.sum());
				else System.out.printf("Dealer | BUSTS | %s: %d\n", dealerHand.toString(), dealerHand.sum());
			}

			//Win conditions
			if(playerHand.sum() == 21 && dealerHand.sum() == 21 && (playerHand.getCards().size() == dealerHand.getCards().size())){ //Both get blackjack in 2 cards
				if(Integer.parseInt(args[0]) <= 10) System.out.println("It's a tie!");
				ties++;
			} else if(dealerHand.sum() != 21 && playerHand.sum() == 21 && playerHand.getCards().size() == 2){ //Player got a blackjack and the dealer didn't
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The player got a blackjack and wins!");
				playerWins++;
			} else if(playerHand.sum() != 21 && dealerHand.sum() == 21 && dealerHand.getCards().size() == 2){ //Dealer got a blackjack and the player didn't
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The dealer got a blackjack and wins!");
				dealerWins++;
			} else if(playerHand.sum() == 21 && dealerHand.sum() == 21 && playerHand.getCards().size() < dealerHand.getCards().size()){ //Player gets 21 in less cards
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The player wins because they got 21 first!");
				playerWins++;
			} else if(playerHand.sum() == 21 && dealerHand.sum() == 21 && playerHand.getCards().size() > dealerHand.getCards().size()){ //Dealer gets 21 in less cards
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The dealer wins because they got a 21 first!");
				dealerWins++;
			} else if(playerHand.sum() <= 21 && dealerHand.sum() > 21){  //Dealer busts
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The player wins!");
				playerWins++;
			} else if(playerHand.sum() > 21 && dealerHand.sum() <= 21){ //Player busts
				if(Integer.parseInt(args[0]) <= 10) System.out.println("The dealer wins!");
				dealerWins++;
			} else if(playerHand.sum() > 21 && dealerHand.sum() > 21){ //Player and Dealer both bust
				if(Integer.parseInt(args[0]) <= 10) System.out.println("Both people busted and tied!");
				ties++;
			} else if(playerHand.sum() <= 21 && dealerHand.sum() <= 21){ //Player and Dealer both stand
				if(playerHand.sum() < dealerHand.sum()){ //Dealer had a higher sum
					if(Integer.parseInt(args[0]) <= 10) System.out.println("The dealer wins!");
					dealerWins++;
				} else if(playerHand.sum() > dealerHand.sum()){ //Player had a higher sum
					if(Integer.parseInt(args[0]) <= 10) System.out.println("The player wins!");
					playerWins++;
				} else if(playerHand.sum() == dealerHand.sum()){ //Player and dealer had the same sum
					if(Integer.parseInt(args[0]) <= 10) System.out.println("It's a tie!");
					ties++;
				}
			}

			//Clear both players' hands
			discardCards();

			//Prepare for next round
			//Add discard pile to shoe if shoe size is less than 1/4 the original size of shoe
			if(deck.getDeck().size() < ((Integer.parseInt(args[1])*52)*.25)){
				while(discardPile.size() > 0){
					deck.getDeck().addItem(discardPile.removeItem());
				}

				System.out.printf("\nReshuffling the shoe for round %d!\n", i+2);
				deck.getDeck().shuffle();
			}
		}

		//Print out match statistics
		System.out.println("\n-----MATCH STATISTICS-----");
		System.out.printf("Rounds played: %s\n", args[0]);
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
