import java.util.*;
class MainMenu implements Menu
{
	public static void Clear(){System.out.println("");} //Likely will remove this later
	public static void Display() 
	{
		int input=0;
		while(true)
		{
			Scanner s=new Scanner(System.in);
			Clear();
			System.out.println("1) Play on Some Prompts\n2) View Progress\n3) Quit");
			try {input=s.nextInt();}
			catch(Exception e) {System.out.println("\n"); input=0;}
			if(1<=input && input<=3) break;
		}
		switch(input)
		{
			case 1:PlayMenu.Display();break;
			case 2:break;
			case 3:System.exit(0);break;
			default:System.out.println("An Error Occured");break;
		}
	}

}
