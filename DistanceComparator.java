import java.util.Comparator;
import java.util.HashMap;


public class DistanceComparator<T> implements Comparator<IntersectionI> {
	
	private HashMap<IntersectionI, Double>  distTo;



	public DistanceComparator() {
		distTo = new HashMap<IntersectionI, Double>();
	}
	
	
	@Override
	public int compare(IntersectionI i1, IntersectionI i2) {
		double e1 = distTo.get(i1);
		double e2 = distTo.get(i2);

		return (int) (e1 - e2);
	}



	public double getDistTo(IntersectionI i) {		
		return distTo.get(i);
	}
	
	public void setDistTo(IntersectionI i, double distance) {		
		distTo.put(i, distance);
	}



}
