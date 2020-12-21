public class Player 
{
	protected int money;
	public Player()
	{
		money=500000;
	}
	public int withdraw(int amount)
	{
		money-=amount;
		return amount;
	}
	public void deposit(int amount)
	{
		money+=amount;
	}
	public int getWallet()
	{
		return money;
	}
}
