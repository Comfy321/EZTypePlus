import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.nio.channels.*;
//Score Objects - Consist of points, seconds, accuracy, and accosiated prompt.
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
	{ return "\t" +points +" points " +seconds +"seconds " +accuracy +"%accuracy "; }
	public void Submit()
	{
		//"Submits" score to stats file; adds above toString method below where whatever prompt is in stats
		PrintWriter statsFile = null;
		try
		{
			FileWriter fw = new FileWriter("stats", true);
			BufferedWriter bw = new BufferedWriter(fw);
			statsFile = new PrintWriter(bw);
		}
		catch(Exception e) { System.out.println(e); }
		int linenum=this.getLineNumberOfPrompt();
		if(linenum==-1) //create entry for prompt if there isn't one already.
		{
			statsFile.println(prompt);
			linenum=this.getLineNumberOfPrompt();
			statsFile.println(this.toString());
		}
		else
		{
			insert(linenum+2, this.toString().getBytes());
		}
		statsFile.close();
	}
	public int getLineNumberOfPrompt()
		//finds this.prompt in stats file, returns line no. If stats doesn't include this prompt, returns -1.
	{
		File stats= new File("stats");
		Scanner fileReader=null;
		try {fileReader = new Scanner(stats); }
		catch(Exception e) { System.out.println(e); }
		int i=0;
		while(fileReader.hasNextLine())
		{
			i++;
			if(fileReader.nextLine().equals(prompt))
				return i;
		}
		return -1;
	}
	public static int countLinesNew() throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream("stats"));
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

	public void insert(long offset, byte[] content)
	{
		try
		{
			String filename="stats";
			RandomAccessFile r = new RandomAccessFile(new File(filename), "rw");
			RandomAccessFile rtemp = new RandomAccessFile(new File(filename + "~"), "rw");
			long fileSize = r.length();
			FileChannel sourceChannel = r.getChannel();
			FileChannel targetChannel = rtemp.getChannel();
			sourceChannel.transferTo(offset, (fileSize - offset), targetChannel);
			sourceChannel.truncate(offset);
			r.seek(offset);
			r.write(content);
			long newOffset = r.getFilePointer();
			targetChannel.position(0L);
			sourceChannel.transferFrom(targetChannel, newOffset, (fileSize - offset));
			sourceChannel.close();
			targetChannel.close();
		}
		catch(Exception e) { System.out.println(e); }
	}
}
