package game;
import java.util.*;
public class Run {

	public static void main(String[] args) {
		//VARIABLES
		Card[] deck=new Card[40];
		Card[] discard=new Card[40];
		Fluster[] flusterDeck=new Fluster[13];
		Card[] player1=new Card[5];
		Card[] player2=new Card[5];
		Card[] player3=new Card[5];
		Card[] player4=new Card[5];
		Scanner in=new Scanner(System.in);
		
		//MAIN
		Setup(deck,flusterDeck);
		Deal(deck, player1, player2, player3, player4, discard);
		System.out.println("Your cards are: ");
		for (int x=0;x<player1.length;x++){
			System.out.println("Card "+(x+1)+": "+player1[x].getValue()+" of "+player1[x].getSuit());
		}
		int choice;
		do{
			System.out.print("Which card do you want to play?");
			choice=in.nextInt();
		}while(choice<1||choice>5);
		System.out.println("You picked "+player1[choice-1].getValue()+" of "+player1[choice-1].getSuit());
		
		System.out.println("Okay would like to draw from the discard pile or from the deck");
		System.out.println("the card on the top of the discard pile is: "+discard);
		System.out.println("1: Deck\n2: Discard pile");
		int drawChoice=in.nextInt();
		if (drawChoice==1)
		{
			System.out.println("The card drawn is");
			System.out.println("Do you want to\nDiscard this card: 1\nkeep this card and discard another: 2");
		}
		Card tempCard = player1[choice];
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
	public static void Deal(Card[] deck, Card[] p1, Card[] p2,Card[] p3, Card[] p4, Card[] discard){
		for (int x=0;x<5;x++){
			p1[x]=deck[x*4];
			p2[x]=deck[x*4+1];
			p3[x]=deck[x*4+2];
			p4[x]=deck[x*4+3];
		}
		discard[0]=deck[24];
	}
}
