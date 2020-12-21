import java.util.*;
public class Deck 
{
int lengthOfDeck=52, cardsDealt=0;//length is standard, cards dealt is useless in this case
static List<Card> theDeck = new ArrayList<Card>();
	public Deck()//constructer doesn't have parameters because every deck is standard
	{
		for(int i=0; i<4; i++)//sets spades
		{
			if(i==0)
			{
				for(int j=0; j<13; j++)
				{
					theDeck.add(new Card(j+1, "Spades"));
				}
			}
			if(i==1)//sets diamonds
			{
				for(int j=0; j<13; j++)
				{
					theDeck.add(new Card(j+1, "Diamonds"));
				}
			}
			if(i==2)//sets hearts
			{
				for(int j=0; j<13; j++)
				{
					theDeck.add(new Card(j+1, "Hearts"));
				}
			}
			if(i==3)//sets spades
			{
				for(int j=0; j<13; j++)
				{
					theDeck.add(new Card(j+1, "Spades"));
				}
			}
		}
	}
	public void shuffle()//shuffles method, not the built in one, because i didn't know it existed previously
	{
		List<Card> newDeck = new ArrayList<Card>();
		while(theDeck.size() > 0)
		{
			int randomIndex = (int)(Math.random() * theDeck.size());
			newDeck.add(theDeck.remove(randomIndex));
		}
		theDeck = newDeck;
	}
	public Hand deal(int numDealt, Hand hand)//deals a set amount of cards to a hand
	{
		for(int i=0; i<numDealt; i++)
		{
			hand.add(theDeck.remove(0));
		}
		return hand;
	}
	public String toString()//standard toString
	{
		String output = "THE DECK:\n";
		for(int i=0; i<theDeck.size();i++)
		{
			output+=theDeck.get(i)+"\n";
		}
		return output;
	}
}
