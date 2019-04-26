public class StatMenu implements Menu
{
	public static void Display()
	{
		if(PlayMenu.prompts.size()<=1)
			PlayMenu.genPrompts();
		while(true)
		{
			System.out.println("---Stats---\n0)back");
			for(int i=1; i<=MainMenu.prompts.size();i++)
				System.out.println(i +") " +prompts.get(i-1));
			int choice=-1;
			while(true)
			{
				Scanner s= new Scanner(System.in);
				try {choice=s.nextInt(); }
				catch(Exception e) { choice=-1; }
				if(choice>=0 && choice < MainMenu.prompts.size()+1)
					break;
			}
			if(choice!=0)
				choice--;
			else
				MainMenu.Display();

		}
	}
}
