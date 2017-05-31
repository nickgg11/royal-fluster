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
		Deal(deck, player1, player2, player3, player4);
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
	public static void Deal(Card[] deck, Card[] p1, Card[] p2,Card[] p3, Card[] p4){
		for (int x=0;x<5;x++){
			p1[x]=deck[x*4];
			p2[x]=deck[x*4+1];
			p3[x]=deck[x*4+2];
			p4[x]=deck[x*4+3];
		}
	}
}
