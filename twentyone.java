/*
 * programer Eliot Waddell
 * play 21!
 */
import javax.swing.*;
public class twentyone 
{
	public static int betting()
	{
		boolean correctBet=false;
		int bet=0;
		while(!correctBet)
		{
			try
			{
				bet = Integer.parseInt(JOptionPane.showInputDialog("How much would you like to bet?"));
				correctBet=true;
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Incorrect input, try again");
			}
		}
		return bet;
	}
	public static int hitOrStay(Hand thePlayer)//method designed to return an int either 0 or 1
   //which is used to determine whether the player hits or stays, determined by showOptionDialog
	{
		int choice=0;
		String[] options = {"Hit","Stay"};
		String currentHand = "CURRENT HAND:\n";
		for(int i=0; i<thePlayer.size();i++)
		{
			currentHand+=thePlayer.get(i)+"\n";
		}
		choice=JOptionPane.showOptionDialog(null, currentHand+"The value of your hand is "+thePlayer.valueOfHand()+ ".\nHit or Stay?", "Please choose", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return choice;
	}
	public static void theGame(Player player)//the actual game
	{
		int bet = player.withdraw(betting());
		Deck theDeck = new Deck();//standard deck
		theDeck.shuffle();//shuffles deck
		int choice=0;//establishes int to be used as a choice
		Hand thePlayer = new Hand();//player hand
		Hand theDealer = new Hand();//dealer hand
		theDeck.deal(2, thePlayer);//deals 2 cards to player
		theDeck.deal(2, theDealer);//deals 2 cards to dealer
		while(choice==0)//while the player continues to hit
		{
			if(thePlayer.get(thePlayer.size()-1).getFace().equalsIgnoreCase("ace"))//if the player is dealt ace
			{
				Card oldAce = thePlayer.get(thePlayer.size()-1);//establishes the current ace
				oldAce.setValue(setAceValue());//sets that aces value to whichever value player picks, using setAceValue method
			}
			if(thePlayer.get(thePlayer.size()-2).getFace().equalsIgnoreCase("ace"))//same as above except checks 2 cards back
         //in case player was dealt ace on first card
			{
				Card oldAce = thePlayer.get(thePlayer.size()-1);
				oldAce.setValue(setAceValue());
			}
			if(thePlayer.valueOfHand()>21)//if player busts
			{
				break;
			}
			choice=hitOrStay(thePlayer);//sets choice value
			if(choice==0)
			{
				theDeck.deal(1, thePlayer);//deals 1 card in case that choice was to hit
				if(thePlayer.get(thePlayer.size()-1).getFace().equalsIgnoreCase("ace"))//checks for ace on new card dealt
				{
					Card oldAce = thePlayer.get(thePlayer.size()-1);
					oldAce.setValue(setAceValue());
				}
					
			}
			
		}
		while(theDealer.valueOfHand()<=16)//dealer is forced to hit if their hand is below 16, like real blackjack
		{
			theDeck.deal(1, theDealer);//deals to dealer
			if(theDealer.valueOfHand()>21)//dealer busts
			{
				break;
			}
		}
		if(thePlayer.valueOfHand()>21)//the program checks for the player busting first
		JOptionPane.showMessageDialog(null, "You busted. Too bad my bro.");
		else if(theDealer.valueOfHand()>21)//if player didnt bust, checks for dealer busting
		{
			JOptionPane.showMessageDialog(null, "You win, the dealer busted, my bro.\n"+theDealer);
			player.deposit(bet*2);
		}
		else
		{
			if(thePlayer.valueOfHand()==theDealer.valueOfHand())//if neither busted, checks for who has larger value
			{
				JOptionPane.showMessageDialog(null, "You tied with the dealer, my bro.\n"+theDealer);
				player.deposit(bet);
			}
			else
			{
			if(thePlayer.valueOfHand()>theDealer.valueOfHand())
			{
			JOptionPane.showMessageDialog(null, "You won, my bro, with a score of "+thePlayer.valueOfHand()+" beating the dealer's score of "+theDealer.valueOfHand()+".\n"+theDealer); 
			player.deposit(bet*2);
			}
			else
			JOptionPane.showMessageDialog(null, "My bro, you lost to the dealer, with a score of "+thePlayer.valueOfHand()+" being less than "+theDealer.valueOfHand()+".\n"+theDealer);
			}
		}
	}
	public static int setAceValue()//method using showOptionDialog to determine whether ace should equal 1 or 11
	{
		int aceValue=0;
		int choice=0;
		String[] options = {"1","11"};
		choice=JOptionPane.showOptionDialog(null, "Congrats, my bro! You got an ace! Would you like it to be worth 1 or 11?", "Please choose", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(choice==0)
			aceValue=1;
		if(choice==1)
			aceValue=11;
		return aceValue;
	}
	public static void play21(Player player)//main function, plays the game
	{
		theGame(player);
	}
}
