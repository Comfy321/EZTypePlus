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
		Scanner scan = new Scanner(system.in);
		while(true)
		{
			
		}
	}
}
