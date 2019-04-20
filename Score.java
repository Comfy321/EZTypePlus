import java.math.*;
import java.text.*;
public class Score
{
	private static DecimalFormat twoPlaces = new DecimalFormat("#.##");
	public double points;
	public double seconds;
	public double accuracy;
	public Score(double p, double s, double a)
	{
		points=Double.parseDouble(twoPlaces.format(p));
		seconds=Double.parseDouble(twoPlaces.format(s));
		accuracy=Double.parseDouble(twoPlaces.format(a));
	}
	public String toString()
	{ return (points +" points " +seconds +" seconds " +accuracy +"% accuracy"); }
}
