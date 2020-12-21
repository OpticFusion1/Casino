import javax.swing.JOptionPane;
import java.util.*;
public class Roulette 
{	
	//getValue parses String number values into integer number values
	public static int getValue(String question)
	{
	      int value = 0;
	      String input;
	      boolean anumber = false;
	      while(anumber == false)
	      {
	         input = JOptionPane.showInputDialog(question);
	         try
	         {
	            value = Integer.parseInt(input);
	            anumber = true;            
	         }
	         catch(NumberFormatException e)
	         {
	            JOptionPane.showMessageDialog(null,"Sorry that's not a number.");
	         }
	      }
	      return  value;
	}
	//Betting method takes a number for how much the player would like to bet
	public static int betting()
	{
		int bet = getValue("How much would you like to bet?");
		return bet;
	}
	//wheel method sets up a random number generator that returns a 
	public static int wheel()
	{
		Random generate = new Random();
		int value = generate.nextInt(38);
		return value;
	}
	
	public static void playRoulette(Player player) 
	{	
		int input = 0;
		int chips = 0;
		String[] buttons = {"Number","Odd","Even","High","Low"};//Main menu input- directs user to the function they need
		input = JOptionPane.showOptionDialog(null,"How would you like to bet?","Menu",1,1,null,buttons,buttons[0]);

		//Number betting decision
		if(input == 0)
		{
			int number = getValue("What number would you like to bet on? (Between 1 and 38)");
			chips = player.withdraw(betting()); 
			int value = wheel();			
			if(value == number)
			{
				JOptionPane.showMessageDialog(null,"The number was "+value+"! You won!");
				player.deposit(chips*38);
			}	
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry, the number was "+value+", you lost");
			}	
		}
		//Odd betting choice
		if(input == 1)
		{
			int value = wheel();
			chips = player.withdraw(betting()); 
			
			if(value%2 != 0)
			{				
				JOptionPane.showMessageDialog(null,"The number was "+value+"! You won!");	
				player.deposit(chips*2);
			}	
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry, the number was "+value+", you lost");
			}	
			
		}	
		//Even betting option
		if(input == 2)
		{
			int value = wheel();
			chips = player.withdraw(betting()); 
			
			if(value%2 == 0)
			{				
				JOptionPane.showMessageDialog(null,"The number was "+value+"! You won!");
				player.deposit(chips*2);
			}	
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry, the number was "+value+", you lost");
			}	
			
		}	
		//High betting option
		if(input == 3)
		{
			int value = wheel();
			chips = player.withdraw(betting()); 
			
			if(value >= 19)
			{				
				JOptionPane.showMessageDialog(null,"The number was "+value+"! You won!");
				player.deposit(chips*2);
			}	
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry, the number was "+value+", you lost");
			}	
			
		}	
		//Low betting option
		if(input == 4)
		{
			int value = wheel();
			chips = betting(); 
			
			if(value <= 18)
			{				
				JOptionPane.showMessageDialog(null,"The number was "+value+"! You won!");
				player.deposit(chips*2);
			}	
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry, the number was "+value+", you lost");
			}	
			
		}	
	
	}

}


