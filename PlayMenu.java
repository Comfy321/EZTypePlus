import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
class PlayMenu implements Menu
{
	public static ArrayList<String> prompts = new ArrayList<String>(); 
	public static void genPrompts()
		//populates static ArrayList prompts with the contents of promptList
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("promptList"));
			String line = reader.readLine();
			while(line != null)
			{
				prompts.add(line);
				line = reader.readLine();
			}
		}
		catch(Exception e)
		{ System.out.println(e); }
	}
	public static void Display()
	{
		if(prompts.size()<=1)
			genPrompts();
		while(true)
		{
			MainMenu.Clear();
			System.out.println("---Play---\n0) Back");
			for(int i=1; i<=prompts.size(); i++)
				System.out.println(i +") " +prompts.get(i-1));
			int choice =-1;
			while(true)
			{

				Scanner s = new Scanner(System.in);
				try{choice=s.nextInt();}
				catch(Exception e) { choice=-1; }
				if(choice>=0 && choice < prompts.size()+1)
					break;
			}
			if(choice!=0)
				choice--;
			else
				MainMenu.Display();
			Score thisScore = Play(prompts.get(choice));
			if(thisScore!=null)
				thisScore.Submit();
		}
	}
	public static Score Play(String prompt)
		//Gives user a prompt and returns Score object (see Score.java) 
	{
		MainMenu.Clear();
		Scanner s = new Scanner(System.in);
		System.out.println(prompt);
		long starttime=System.currentTimeMillis();
		String input=s.nextLine();
		long endtime=System.currentTimeMillis();
		double time=(endtime-starttime)/1000.0; //time is in seconds
		System.out.println(time +" sec.");
		double sim = Similarity(prompt, input) * 100;
		sim-=70;
		if(sim<=0)
		{
			System.out.println("Too inaccurate to score. Not Counting.");
			return null;
		}
		return new Score(getPoints(sim, time), time, sim+70, prompt);
	}
	public static double getPoints(double s, double t) //calculate score based on time and similarity
	{ return (double) ((s/t) * 1000.0) ;}
	public static double Similarity(String s1, String s2)
		//calculates string similarity between two strings.
	{
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length())
		{ // longer should always have greater length
			longer = s2; shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) { return 1.0;} //both strings are 0 length
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
	}
	public static int editDistance(String s1, String s2)
	{
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) 
		{
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++)
			{
				if (i == 0)
					costs[j] = j;
				else 
				{
					if (j > 0) 
					{
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue),
									costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}
}
