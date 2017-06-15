package game;
import java.util.*;
public class Run {
	public static int deckPos=0;
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static int discardPos=0;
	public static int numP=0;
	public static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		//VARIABLES
		Card[] deck=new Card[40];
		Card[] discard=new Card[40];
		Fluster[] flusterDeck=new Fluster[13];
		Card[] player1=new Card[5];
		Card[] player2=new Card[5];
		Card[] player3=new Card[5];
		Card[] player4=new Card[5];
		Card tempCard = deck[0];
		boolean winp1=false;
		boolean winp2=false;
		boolean winp3=false;
		boolean winp4=false;
		//MAIN
		Setup(deck,flusterDeck);
		populateMap();
		System.out.println("How many players do you want to play with");
		System.out.println("Two Players: 1\nThree Players: 2\nFour Players: 3");
		numP = in.nextInt();
		in.nextLine();
		if (numP==1)
		{
			Deal2(deck,player1, player2, deckPos);
		}
		if (numP==2)
		{
			Deal3(deck, player1, player2, player3, deckPos);
		}
		if (numP==3)
		{
			Deal4(deck, player1, player2, player3, player4, deckPos);
		}
		discard[discardPos]=deck[deckPos];
		deckPos++;
		sortHand(player1);
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
					Card discardTemp = player1[choice-1];
					Card flusterCheck = player1[choice-1];
					player1[choice-1]=tempCard;
					discard[discardPos+1]=discardTemp;
				}
				else
				{
					discard[discardPos+1]=tempCard;
					flusterCheck = discard[discardPos+1];
				}
			}
			else if(drawChoice==2)
			{
				System.out.println("Please select a card to replace");
				System.out.println("Card #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
				choice = in.nextInt();
				tempCard=discard[discardPos];
				System.out.println("You picked "+player1[choice-1].getValue()+" of "+player1[choice-1].getSuit());
				discard[discardPos] = player1[choice-1];
				flusterCheck=discard[discardPos];
				player1[choice-1]=tempCard;
				for (int q=0;q<player1.length;q++){
					System.out.println("Card "+(q+1)+": "+player1[q].getValue()+" of "+player1[q].getSuit());
				
				}
			}
			if (deckPos==40)
			{
				Shuffle(deck);
				deckPos=0;
			}
			winChecker(player1, winp1, player2, winp2, player3, winp3, player4, winp4);
			if (discard[discardPos]=='A')
			{
				int x=0;
				if(x==14)
				{
					x=0;
				}
				System.out.println("The card drawn is "+flusterDeck[x].getType());
				if (flusterDeck[x].getType()=="Go Again")
				{
					play( player1, discard, choice, tempCard, Card[] deck, int deckPos, int discardPos, boolean winp1, Card[] player2, boolean winp2, Card[] player3, boolean winp3, Card[] player4, boolean winp4);
				}
				else if (flusterDeck[x].getType()=="Go Again Twice")
				{
					play(Card[] player1, Card[] discard, int choice, Card tempCard, Card[] deck, int deckPos, int discardPos, boolean winp1, Card[] player2, boolean winp2, Card[] player3, boolean winp3, Card[] player4, boolean winp4);	
					play(Card[] player1, Card[] discard, int choice, Card tempCard, Card[] deck, int deckPos, int discardPos, boolean winp1, Card[] player2, boolean winp2, Card[] player3, boolean winp3, Card[] player4, boolean winp4);	
				}
				else if (flusterDeck[x].getType()=="look and Take")
				{
					System.out.println("You got Look and take.");
					System.out.println("who do you want to take from?\nPlayer 2: 1\nPlayer 3: 2\nPlayer 4: 3");
					choice = in.nextInt();
					in.nextLine();
					if (choice==1)
					{
						for (int x=0;x<player1.length;x++){
							System.out.println("Card "+(x+1)+": "+player2[x].getValue()+" of "+player2[x].getSuit());
							System.out.println("Pick a card you want\nCard #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
							choice = in.nextInt();
							tempCard = player2[choice-1];
							System.out.println("What card will you replace\nCard #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
							choice = in.nextInt();
							player2[choice-1]=player1[choice-1];
							player1[choice-1] = tempCard;
							
						}
					}
						
				}
				x++
				for (int q=0;q<player1.length;q++){
					System.out.println("Card "+(q+1)+": "+player1[q].getValue()+" of "+player1[q].getSuit());
				}
			}
			discardPos++;
			deckPos++;
		}
		while(winp1==false && winp2==false && winp3==false && winp4==false);

		in.close();
	}

	//HELPER METHODS

	//SETUP
	public static void Setup(Card[] deck, Fluster[] flusterDeck){
		String values[]={"10","J","Q","K","A"};
		String suits[] ={"Spade","Heart","Club","Diamond","Spade","Heart","Club","Diamond"};
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
	public static void Deal2(Card[] deck, Card[] p1, Card[] p2, int startCard)
	{
		for (int x=0;x<5;x++){
			p1[x]=deck[x*4];
			p2[x]=deck[x*4+1];
			deckPos+=2;
		}
	}
	public static void Deal3(Card[] deck, Card[] p1, Card[] p2,Card[] p3,  int startCard)
	{
		for (int x=0;x<5;x++){
			p1[x]=deck[x*4];
			p2[x]=deck[x*4+1];
			p3[x]=deck[x*4+2];
			deckPos+=3;
		}
	}
	public static void Deal4(Card[] deck, Card[] p1, Card[] p2,Card[] p3, Card[] p4, int startCard)
	{
		for (int x=0;x<5;x++){
			p1[x]=deck[x*4];
			p2[x]=deck[x*4+1];
			p3[x]=deck[x*4+2];
			p4[x]=deck[x*4+3];
			deckPos+=4;
		}
	}
	//Sort Hand
	public static void populateMap ()
	{
		map.put("A", 5);
		map.put("K", 4);
		map.put("Q", 3);
		map.put("J", 2);
		map.put("10", 1);
	}
	public static void sortHand (Card[] player1)
	{
		for (int y=0;y<player1.length;y++){
			for (int x=0;x<player1.length-1;x++)
			{
				int temp1 = map.get( player1[x].getValue());
				int temp2 = map.get( player1[x+1].getValue());
				if(temp1>temp2)
				{
					Card cardTemp = player1[x];
					player1[x] = player1[x+1];
					player1[x+1] = cardTemp;	
				}
			}
		}
	}
	//Play
	public static void play (Card[] player1, Card[] discard, int choice, Card tempCard, Card[] deck, int deckPos, int discardPos, boolean winp1, Card[] player2, boolean winp2, Card[] player3, boolean winp3, Card[] player4, boolean winp4)
	{
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
				Card discardTemp = player1[choice-1];
				Card flusterCheck = player1[choice-1];
				player1[choice-1]=tempCard;
				discard[discardPos+1]=discardTemp;
			}
			else if(drawChoice==2)
			{
				System.out.println("Please select a card to replace");
				System.out.println("Card #1: 1\nCard #2: 2\nCard #3: 3\nCard #4: 4\nCard #5: 5");
				choice = in.nextInt();
				tempCard=discard[discardPos];
				System.out.println("You picked "+player1[choice-1].getValue()+" of "+player1[choice-1].getSuit());
				discard[discardPos] = player1[choice-1];
				player1[choice-1]=tempCard;
				for (int q=0;q<player1.length;q++){
					System.out.println("Card "+(q+1)+": "+player1[q].getValue()+" of "+player1[q].getSuit());
				}
			}
			if (deckPos==40)
			{
				Shuffle(deck);
				deckPos=0;
			}
			discardPos++;
			deckPos++;
			winChecker(player1, winp1, player2, winp2, player3, winp3, player4, winp4);
		}
	}
	//Win
	public static void winChecker (Card[] p1, boolean winp1,Card[] p2, boolean winp2,Card[] p3, boolean winp3,Card[] p4, boolean winp4)
	{
		String suit=p1[0].getSuit();
		if (p1[1].getSuit()==suit&&p1[2].getSuit()==suit&&p1[3].getSuit()==suit&&p1[4].getSuit()==suit&&p1[0].getValue()=="10"&&p1[0].getValue()=="J"&&p1[0].getValue()=="Q"&&p1[0].getValue()=="K"&&p1[0].getValue()=="A")
		{
			winp1=true;
		}
		String suit2=p2[0].getSuit();
		if (p2[1].getSuit()==suit2&&p2[2].getSuit()==suit2&&p2[3].getSuit()==suit2&&p2[4].getSuit()==suit2&&p2[0].getValue()=="10"&&p2[0].getValue()=="J"&&p2[0].getValue()=="Q"&&p2[0].getValue()=="K"&&p2[0].getValue()=="A")
		{
			winp2=true;
		}
		if(numP==2)
		{
			String suit3=p3[0].getSuit();
			if (p3[1].getSuit()==suit3&&p3[2].getSuit()==suit3&&p3[3].getSuit()==suit3&&p3[4].getSuit()==suit3&&p3[0].getValue()=="10"&&p3[0].getValue()=="J"&&p3[0].getValue()=="Q"&&p3[0].getValue()=="K"&&p3[0].getValue()=="A")
			{
				winp3=true;
			}
			if (numP==3)
			{
				String suit4=p4[0].getSuit();
				if (p4[1].getSuit()==suit4&&p4[2].getSuit()==suit4&&p4[3].getSuit()==suit4&&p4[4].getSuit()==suit4&&p4[0].getValue()=="10"&&p4[0].getValue()=="J"&&p4[0].getValue()=="Q"&&p4[0].getValue()=="K"&&p4[0].getValue()=="A")
				{
					winp4=true;
				}
			}
		}
	}
}