/*
 * Programmer: Eliot J Waddell
 * Class is to establish a card like you find in a card deck 
 */
public class Card
{
int value;//value of the card
String suit;//one of the four standard card suits
String face;

	public Card()//establishes card as ace of spades when given no parameters
	{
		value=1;
		suit="Spades";
		face=setFace();
		standardizeFace();
	}
	public Card(int cardValue, String cardSuit)//establishes cards based on parameters
	{
		if(validValue(cardValue)&&validSuit(cardSuit))//checks to make sure suit and value are valid input
		{
			value=cardValue;//if condition returns true then values are set
			suit=cardSuit;
			face=setFace();
			standardizeFace();
		}
		else//if the condition returns false then throws an error
		{
			value=1;
			suit="Spades";
			face=setFace();
			standardizeFace();
		}
	}
	public static boolean validValue(int cardValue)//simple method to check if card value is between 1 and 13
	{
		if(cardValue>0&&cardValue<14)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean validSuit(String cardSuit)//method to check if suit is one of the four suits
	{
		if(cardSuit.equalsIgnoreCase("spades")||cardSuit.equalsIgnoreCase("hearts")||cardSuit.equalsIgnoreCase("diamonds")||cardSuit.equalsIgnoreCase("clubs"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getValue()//self explanatory
	{
		return value;
	}
	public String getSuit()//also self explanatory
	{
		return suit;
	}
	public String getFace()//still self explanatory
	{
		return face;
	}
	public void setValue(int cardValue)//sets the value but first checks if it is valid
	{
		if(validValue(cardValue))
		{
			value=cardValue;
		}
		else
		{
			value=1;
		}
	}
	public void setSuit(String cardSuit)//sets the suit if input is valid, else sets as spades
	{
		if(validSuit(cardSuit))
		{
			suit=cardSuit;
		}
		else
		{
			suit="Spades";
		}
	}
	public boolean equals(Card comparison)//standard equals method
	{
		if(value==comparison.getValue()&&suit==comparison.getSuit()&&face==comparison.getFace())
		{
			return true;
		}
		else
			return false;
	}
	public String toString()//standard tostring method, prints "blank" of "blanks"
	{
		String cardInfo="";
		if(value==1||value==11&&face.equalsIgnoreCase("Ace"))
		{
			cardInfo+="Ace of ";
		}
		else if(value==10&&face.equalsIgnoreCase("Jack"))
		{
			cardInfo+="Jack of ";
		}
		else if(value==10&&face.equalsIgnoreCase("Queen"))
		{
			cardInfo+="Queen of ";
		}
		else if(value==10&&face.equalsIgnoreCase("King"))
		{
			cardInfo+="King of ";
		}
		else
		{
			cardInfo+=value+" of ";
		}
		cardInfo+=suit;
		return cardInfo;
	}
	public String setFace()//this was a method added retroactively because i originally didnt have faces
	{
		String theFace="";
		if(value==1)
		{
			theFace="Ace";
		}
		else if(value==11)
		{
			theFace="Jack";
		}
		else if(value==12)
		{
			theFace="Queen";
		}
		else if(value==13)
		{
			theFace="King";
		}
		return theFace;
	}
	public void standardizeFace()//i also originally had different values for each face, so this method standardizes them
	{
		if(face.equalsIgnoreCase("ace"))
		{
			value=11;
		}
		else if(face.equalsIgnoreCase("Jack"))
		{
			value=10;
		}
		else if(face.equalsIgnoreCase("queen"))
		{
			value=10;
		}
		else if(face.equalsIgnoreCase("king"))
		{
			value=10;
		}
	}
	
}