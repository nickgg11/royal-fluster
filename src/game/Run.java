package game;
import java.util.*;
public class Run {

	public static void main(String[] args) {
		//VARIABLES
		Card[] deck=new Card[40];
		Fluster[] flusterDeck=new Fluster[13];
		Card[] player1=new Card[5];
		Card[] player2=new Card[5];
		Card[] player3=new Card[5];
		Card[] player4=new Card[5];
		
		//MAIN
		Setup(deck,flusterDeck);
		System.out.println();
		
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
	
	public static void Shuffle(Object[] deck){
		Random rnd=new Random();
		for (int x=0;x<deck.length;x++){
			int temp=rnd.nextInt(deck.length);
			Object t=deck[x];
			deck[x]=deck[temp];
			deck[temp]=t;
		}
	}
}
