import java.util.*;
public class Prompt
{
	 String prompt;
	 int id;
	 static int nPrompt=0;
	 public Prompt(String s)
	 {
		 nPrompt++;
		 prompt=s;
		 id=nPrompt;
	 }
}
