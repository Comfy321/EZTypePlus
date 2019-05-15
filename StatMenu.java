import java.util.*;
import java.io.*;
public class StatMenu implements Menu
{
	public static void Display()
	{
		if(PlayMenu.prompts.size()<=1)
			PlayMenu.genPrompts();
		while(true)
		{
			System.out.println("---Stats---\n0) Back");
			for(int i=1; i<=PlayMenu.prompts.size();i++)
				System.out.println(i +") " +PlayMenu.prompts.get(i-1));
			int choice=-1;
			while(true)
			{
				Scanner s= new Scanner(System.in);
				try {choice=s.nextInt(); }
				catch(Exception e) { choice=-1; }
				if(choice>=0 && choice < PlayMenu.prompts.size()+1)
					break;
			}
			if(choice!=0)
				choice--;
			else
				MainMenu.Display();
			int lineno = new Score(0,0,0,PlayMenu.prompts.get(choice)).getLineNumberOfPrompt();
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader("stats"));
                //First, move Scanner to Correct Line
                for (int i=1; i<lineno;i++)
                   reader.readLine();
				ArrayList<String> scores = new ArrayList<String>();
                System.out.println(reader.readLine());
                String line="";
				while ((line=reader.readLine())!=null && line.charAt(0)=='\t')
				{
					System.out.println(line);
					scores.add(line);
				}
				reader.close();
			} 
			catch(IOException e) { System.out.println(e);}
		}
	}
}
