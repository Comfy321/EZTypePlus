import java.util.*;
import java.io.*;
class PlayMenu implements Menu
{
	public static ArrayList<String> prompts = new ArrayList<String>();
	public static void genPrompts()
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
		genPrompts();
		System.out.println("---Play---\n0) Back");
		for(int i=1; i<=prompts.size(); i++)
			System.out.println(i +") " +prompts.get(i-1));
		Scanner s = new Scanner(System.in);
		int choice =-1;
		while(true)
		{
			try{choice=s.nextInt();}
			catch(Exception e) { choice=-1; }
			if(choice>=0 && choice < prompts.size()+1)
					break;
		}
		choice--;
		System.out.println(prompts.get(choice));
	}
	public static double Play(String s) //returns time it take to type prompt.
	{
		MainMenu.Clear();
		Scanner s = new Scanner(System.in);
		System.out.println(s);
		long starttime=System.nanoTime();
		String input=s.nextLine();
		long endtime=System.nanoTime();
		long time=(starttime-endtime)/1000; //time is in seconds

	}
	public static double Score(string i; long t) //calculate score based on time and similarity
	{
       	}
}
