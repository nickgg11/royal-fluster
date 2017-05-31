package game;

public class Card {
	String value;
	String suit;
	
	public Card(String value, String suit){
		this.value=value;
		this.suit=suit;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getSuit(){
		return suit;
	}
}
