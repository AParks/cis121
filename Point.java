/**
 * A self explanatory class.
 * For this assignment you can assume that every point is essentially
 * an intersection. More precisely, the location of an intersection
 * is a point, and all points will be represented by an intersection, as
 * we have chosen a closed set of points (a closed graph).
 *
 * @author StudentName
 *
 */
public class Point
{
	private double x;
	private double y;
	
	public Point(double x, double y){
		setx(x);
		sety(y);
	}


	public double getx()
	{
		return x;
	}

	public void setx(double x)
	{
		this.x = x;
	}

	public double gety()
	{
		return y;
	}

	public void sety(double y)
	{
		this.y = y;
	}
	
	@Override
	public String toString(){
		//latitude, longitude		
		return x + "," + y;

	}

	@Override
	public int hashCode(){
		long bits = java.lang.Double.doubleToLongBits(x);
		bits ^= java.lang.Double.doubleToLongBits(y) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}
	
	@Override
	public boolean equals(Object p){
		return ((Point) p).getx() == x && ((Point) p).gety()==y;
	}
}

