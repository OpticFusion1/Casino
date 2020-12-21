import java.util.*;
public class ECard
{
	protected String cardType;
	public ECard()
	{
		cardType = "Citizen";
	}
	public ECard(char c)
	{
		if(c=='e')
			cardType="Emperor";
		else if(c=='c')
			cardType="Citizen";
		else if(c=='s')
			cardType="Slave";
	}
	public String toString()
	{
		return cardType;
	}
	public static int battle(ECard a, ECard b)//a is slave side, b is emperor side
	{
		int result=0;
		if(a.toString().equals("Citizen")&&b.toString().equals("Citizen"))
			result=0;
		else if(a.toString().equals("Citizen")&&b.toString().equals("Emperor"))
			result=1;
		else if(a.toString().equals("Slave")&&b.toString().equals("Emperor"))
			result=-1;
		else
			result=-2;
		return result;
	}
	public static ArrayList<ECard> emperorHand()
	{
		ArrayList<ECard> emperorHand = new ArrayList<ECard>(0);
		for(int i=0; i<4;i++)
		{
			emperorHand.add(new ECard());

		}
		emperorHand.add(new ECard('e'));
		return emperorHand;
	}
	public static ArrayList<ECard> slaveHand()
	{
		ArrayList<ECard> slaveHand = new ArrayList<ECard>(0);
		for(int i=0; i<4;i++)
		{
			slaveHand.add(new ECard());

		}
		slaveHand.add(new ECard('s'));
		return slaveHand;
	}
}
