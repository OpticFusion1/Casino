import java.util.*;

import javax.swing.JOptionPane;
public class Main 
{
	protected static int text=0;
	public static int setTextSpeed()
	{
		int textSpeed=0, selection=0;
		String[] options = {"Slow","Normal","Fast"};
		selection=JOptionPane.showOptionDialog(null, "Please select text speed.", "Text Speed", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(selection==0)
			textSpeed=100;
		else if(selection==1)
			textSpeed=25;
		else if(selection==2)
			textSpeed=10;
		return textSpeed;
	}
	public static void gameOver()
	{
		nicePrint("Unfortunately for you, we cannot trust you in this casino any longer. You will now become an indentured servant for as long as it takes!");
	}
	public static void nicePrint(String message)
	   {
	     int printSpeed=text; 
	     char[] msg = message.toCharArray();
	     for(int i=0; i<msg.length; i++)
	     {
	       System.out.print(msg[i]);
	       try
	       {
	          Thread.sleep(printSpeed);
	       }
	       catch(InterruptedException ie)
	       {
	         
	       }
	       
	     }
	   }
	public static int payDebt(int debt, Player player)
	{
		int paid=0;
		try
		{
			paid = Integer.parseInt(JOptionPane.showInputDialog("Your current debt is $"+debt+", and you possess $"+player.money+", how much would you like to pay off?"));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Incorrect Input, you have paid 0 dollars.");
		}
		debt-=paid;
		if(paid>player.money)
		{
			JOptionPane.showMessageDialog(null, "You don't have that much");
			paid=0;
		}
		else
		{
		if(paid!=0)
		JOptionPane.showMessageDialog(null, "Your new debt is $"+debt+".");
		player.withdraw(paid);
		}
		return debt;
	}
	public static void playECard(Player player)
	{
		String[] firstOptions = {"Play","Rules", "Return to Menu"};
		int firstAction = JOptionPane.showOptionDialog(null, "Welcome to E Card.", "E Card", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, firstOptions, firstOptions[0]);
		if(firstAction==1)
		{
			JOptionPane.showMessageDialog(null, "E Card pits two players against eachother. Both players decks consist of four citizens and one special card, either an Emperor or a Slave.\nEach turn, players put down a card. Citizens, when played against eachother, will cancel out.\nHowever, the Emperor card will defeat the citizen and win the game. But the slave, since it has nothing to lose, will defeat the emperor when played against it.\nThe goal on the Emperor side is to defeat a citizen, while the goal of the Slave side is to defeat the Emperor.\nBefore each round, you can bet on the game. The emperor side recieves a 20% return on wins, whereas the slave recieves a 300% return.");
			firstAction = JOptionPane.showOptionDialog(null, "Welcome to E Card.", "E Card", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, firstOptions, firstOptions[0]);
			
		}
		else if(firstAction==0)
		{
			boolean slaveOrEmperor;
			Random rng = new Random();
			int start = rng.nextInt(2);
			ArrayList<ECard> playerHand = new ArrayList<ECard>(0);
			ArrayList<ECard> enemyHand = new ArrayList<ECard>(0);
			if(start==0)
			{
			JOptionPane.showMessageDialog(null, "You will play on the emperor side");
			playerHand = ECard.emperorHand();
			enemyHand = ECard.slaveHand();
			slaveOrEmperor=false;
			}
			else
			{
			JOptionPane.showMessageDialog(null, "You will play on the slave side");
			playerHand = ECard.slaveHand();
			enemyHand = ECard.emperorHand();
			slaveOrEmperor=true;
			}
			int bet=0;
			boolean correctBet=false;
			while(!correctBet)
			{
				try{
				bet=Integer.parseInt(JOptionPane.showInputDialog("How much would you like to bet?"));
				if(bet<=player.money)
					correctBet=true;
				else
					throw new NumberFormatException("dafsdafsd");
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Incorrect entry, try again.");
				}
			}
			int onTable = player.withdraw(bet);
			int cardPicked = 0;
			boolean battleContinue=true;
			while(battleContinue)
			{	
				battleContinue=false;
				ECard currentCard, enemyCard;
				Object[] hand = playerHand.toArray();
				cardPicked = JOptionPane.showOptionDialog(null, "Which card will you play?", "Card selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, hand, hand[0]);
				if(cardPicked==hand.length-1)
				{
					currentCard=playerHand.remove(hand.length-1);
				}
				else
				{
					currentCard = playerHand.remove(0);
				}
				enemyCard = enemyHand.remove(rng.nextInt(hand.length));
				int battleResult;
				if(slaveOrEmperor)
				{
					battleResult = ECard.battle(currentCard, enemyCard);
				}
				else
				{
					battleResult = ECard.battle(enemyCard, currentCard);
				}
				if(battleResult==0)
				{
					JOptionPane.showMessageDialog(null, "The battle resulted in a draw. It will continue to the next round.");
					battleContinue=true;
				}
				else if(battleResult==1)
				{
					if(slaveOrEmperor)
					{
						JOptionPane.showMessageDialog(null, "The emperor has defeated a citizen, you have lost this round");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You have defeated the enemy's citizen, you win this round.");
						player.deposit((int)(onTable*1.2));
					}
				}
				else if(battleResult==-1)
				{
					if(slaveOrEmperor)
					{
						JOptionPane.showMessageDialog(null, "Through overwhelming odds, you have defeated the emperor.");
						player.deposit((int)(onTable*3));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Though the odds were in your favor, your emperor has been defeated.");
					}
				}
				else
				{
					if(slaveOrEmperor)
					{
						JOptionPane.showMessageDialog(null, "Your slave has been defeated by a citizen");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your citizen has defeated the slave");
						player.deposit((int)(onTable*1.2));
					}
				}
			}
		}
	}
	public static void intro()
	{
		String intro = "Welcome to our newly refurbished casino! Here you will be able to play games to make lots of money.\nEach of you has been lended 500,000 dollars in advance. The interest on this debt compounds by 10% every ten minutes.\nIf this debt ever reaches twice the amount that you have on hand, we will seek immediate recollection.";
		nicePrint(intro);
	}
	public static int selectGame()
	{
		String[] options = {"Blackjack","13 Card Poker","E Card","Roulette"};
		int selection = JOptionPane.showOptionDialog(null, "Select a game to play:", "Game selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return selection;
	}
	public static int menu()
	{
		String[] options = {"Play Games","Pay Debt","General Rules","Exit"};
		int selection = JOptionPane.showOptionDialog(null, "What would you like to do?", "Main Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return selection;
	}
	public static void playPoker()
	{
		
	}
	public static double debtCompound(double debt, int compounds)
	{
		if(compounds==0)
		{
			return debt;
		}
		else
		{
			compounds--;
			return 1.2*debtCompound(debt, compounds);
		}
	}
	public static void main(String[] args)
	{
		int time=0;
		int debt=600000;
		boolean alive=true;
		text=setTextSpeed();
		intro();
		Player player = new Player();
		while(alive)
		{
			if(time>9)
			{
				debt=(int) debtCompound(debt,(time/10));
				time-=(10*(time/10));
				nicePrint("Debt has compounded");
			}
			if(player.money>=debt*2||player.money==0)
			{
				gameOver();
				System.exit(0);
			}
			int menuSelection=menu();
			if(menuSelection==0)
			{
				int selection=selectGame();
				if(selection==0)
				{
					twentyone.play21(player);
					time++;
				}
				else if(selection==1)
				{
					playPoker();
					time++;
				}
				else if(selection==2)
				{
					playECard(player);
					time+=5;
				}
				else if(selection==3)
				{
					Roulette.playRoulette(player);
					time++;
				}
			}
			else if(menuSelection==1)
			{
				payDebt(debt, player);
			}
			else if(menuSelection==2)
			{			
				String message = "The interest on your 500 thousand dollar loan compounds from minute 0, and every 10 minutes thereafter."
						+ "\nEach game takes a certain amount of time to complete. Each game takes one minute to complete, with the exception of ECard"
						+ "\nwhich takes five minutes (per side). This is because ECard is a rather time consuming game with high reward. Once you pay off your debt"
						+ "\nyou are free to go, or if you would like you can stay and play longer, for potentially larger rewards.";
				JOptionPane.showMessageDialog(null, message);
			}
			else if(menuSelection==3)
			{
				System.exit(0);
			}
		}
	}
}
