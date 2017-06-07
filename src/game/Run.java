package game;
import java.util.*;
public class Run {
	public static int deckPos=0;
	public static int discardPos=0;
	public static void main(String[] args) {
		//VARIABLES
		Card[] deck=new Card[40];
		Card[] discard=new Card[40];
		Fluster[] flusterDeck=new Fluster[13];
		Card[] player1=new Card[5];
		Card[] player2=new Card[5];
		Card[] player3=new Card[5];
		Card[] player4=new Card[5];
		Card tempCard = deck[1];
		boolean win=false;
		Scanner in=new Scanner(System.in);
		//MAIN
		Setup(deck,flusterDeck);
		Deal(deck, player1, player2, player3, player4, deckPos);
		deckPos++;
		discard[discardPos]=deck[deckPos];
		System.out.println("Your cards are: ");
		for (int x=0;x<player1.length;x++){
			System.out.println("Card "+(x+1)+": "+player1[x].getValue()+" of "+player1[x].getSuit());
		}
		int choice = 0;
		do{
			System.out.println("the card on the top of the discard pile is: "+discard[discardPos].getValue()+" of "+discard[discardPos].getSuit());
			System.out.println("Would you like to draw from the discard pile or from the deck");
			System.out.println("1: Deck\n2: Discard pile");
			int drawChoice=in.nextInt();
			if (drawChoice==1)
			{
				System.out.println("The card drawn is "+deck[deckPos].getValue()+" of "+deck[deckPos].getSuit());
				tempCard=deck[deckPos];
				System.out.println("Do you want to\nDiscard this card: 1\nkeep this card and discard one from your hand: 2");
				int cardChoice = in.nextInt();
				if (cardChoice==2)
				{
					System.out.println("Card #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
					choice = in.nextInt();
					System.out.println("You picked "+player1[choice-1].getValue()+" of "+player1[choice-1].getSuit());
					player1[choice-1]=tempCard;

					for (int q=0;q<player1.length;q++){
						System.out.println("Card "+(q+1)+": "+player1[q].getValue()+" of "+player1[q].getSuit());
					}
				}
				else
				{
					discard[discardPos+1]=tempCard;
				}

				else if(drawChoice==2)
				{
					System.out.println("Please select a card to replace");
					System.out.println("Card #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
					choice = in.nextInt();
					tempCard=discard[discardPos];
					System.out.println("You picked "+player1[choice-1].getValue()+" of "+player1[choice-1].getSuit());
					player1[choice-1]=tempCard;
					for (int q=0;q<player1.length;q++){
						System.out.println("Card "+(q+1)+": "+player1[q].getValue()+" of "+player1[q].getSuit());
					}
				}
			}
			while(win==false);
			}

			//HELPER METHODS

			//SETUP
			public static void Setup(Card[] deck, Fluster[] flusterDeck){
				String values[]={"10","J","Q","K","A"};
				String suits[] ={"Spades","Hearts","Clubs","Diamonds","Spades","Hearts","Clubs","Diamonds"};
				for (int x=0;x<values.length;x++){
					for (int y=0;y<suits.length;y++){
						deck[x*8+y]=new Card(values[x],suits[y]);
					}
				}
				Shuffle(deck);
				String types[]={"Go Again","Go Again", "Go Again", "Go Again Twice", "Go Again Twice", "Look and Take", "Look and Take", "Take", "Take", "Ask", "Ask", "Dig", "Royal Fluster"};
				for (int x=0;x<types.length;x++){
					flusterDeck[x]=new Fluster(types[x]);
				}
				Shuffle(flusterDeck);
			}

			//SHUFFLE
			public static void Shuffle(Object[] deck){
				Random rnd=new Random();
				for (int x=0;x<deck.length;x++){
					int temp=rnd.nextInt(deck.length);
					Object t=deck[x];
					deck[x]=deck[temp];
					deck[temp]=t;
				}
			}

			//DEAL
			public static void Deal(Card[] deck, Card[] p1, Card[] p2,Card[] p3, Card[] p4, int startCard){
				for (int x=0;x<5;x++){
					p1[x]=deck[x*4];
					p2[x]=deck[x*4+1];
					p3[x]=deck[x*4+2];
					p4[x]=deck[x*4+3];
					deckPos+=4;
				}
			}
		}