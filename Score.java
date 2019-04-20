import java.math.*;
import java.text.*;
import java.io.*;
public class Score
{
	private static DecimalFormat twoPlaces = new DecimalFormat("#.##");
	public double points;
	public double seconds;
	public double accuracy;
	public String prompt;
	public Score(double p, double s, double a, String pr)
	{
		points=Double.parseDouble(twoPlaces.format(p));
		seconds=Double.parseDouble(twoPlaces.format(s));
		accuracy=Double.parseDouble(twoPlaces.format(a));
		prompt=pr;
	}
	public String toString()
	{ return (points +" points " +seconds +" seconds " +accuracy +"% accuracy"); }
	public void Submit()
	{

		BufferedReader reader = new BufferedReader(new FileReader("stats"));
		int linenum=this.getLineNumberOfPrompt();
		if(linenum==-1) //create entry for prompt if there isn't one already.
		{

		}
	}
	public int getLineNumberOfPrompt()
	{
		try
		{
			if(reader.readLine()==prompt)
				return(reader.getLineNumber());
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	public static int countLinesNew(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try 
		{
			byte[] c = new byte[1024];

			int readChars = is.read(c);
			if (readChars == -1) 
			{
				// bail out if nothing to read
				return 0;
			}

			// make it easy for the optimizer to tune this loop
			int count = 0;
			while (readChars == 1024) 
			{
				for (int i=0; i<1024;) 
				{
					if (c[i++] == '\n') 
						++count;
				}
				readChars = is.read(c);
			}

			// count remaining characters
			while (readChars != -1)
		       	{
				System.out.println(readChars);
				for (int i=0; i<readChars; ++i) 
				{
					if (c[i] == '\n')
						++count;
				}
				readChars = is.read(c);
			}

			return count == 0 ? 1 : count;
		} 
		finally 
		{
			is.close();
		}
	}
}
