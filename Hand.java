import java.util.*;
public class Hand//this class only exists for the valueOfHand method otherwise it is literally just an arraylist of cards
{
	ArrayList<Card> theHand = new ArrayList<Card>(0);
	public Hand()//starts as empty hand always
	{
		
	}
	public Card get(int a)//same as arraylist
	{
		return theHand.get(a);
	}
	
	public void remove(int a)//same as arraylist
	{
		theHand.remove(a);
	}
	public int size()//same as arraylist
	{
		return theHand.size();
	}
	public int valueOfHand()//returns value of the hand to be used in 21
	{
		int total=0;
		for(int i=0; i<theHand.size(); i++)
		{
			total+=(theHand.get(i)).getValue();
		}
		return total;
	}
	public void add(Card c)//same as arraylsit
	{
		theHand.add(c);
	}
	public void clearHand()//same as arraylist
	{
		theHand.clear();
	}
	public String toString()//prints the hand of the dealer because this toString method is only used for dealer
	{
		String output = "THE DEALER'S HAND:\n";
		for(int i=0; i<theHand.size();i++)
		{
			output+=theHand.get(i)+"\n";
		}
		return output;
	}
}
