import java.util.ArrayList;
import java.util.List;


public class Intersection implements IntersectionI {

	private Point p;
	private List<StreetI> streets;
	
	public Intersection (Point p ,List<StreetI> streets){
		setStreetList(streets);
		setPointOfIntersection(p);
	}
	
	@Override
	public Point getLocation() {
		return p;
	}

	@Override
	public List<StreetI> getStreetList() {
		return streets;
	}

	@Override
	public void setPointOfIntersection(Point p) {
		this.p = p;

	}

	@Override
	public void setStreetList(List<StreetI> list) {
		streets = new ArrayList<StreetI>();
		streets.addAll(list);

	}

	@Override
	public StreetI isConnected(IntersectionI intersection) {
		if (this.equals(intersection))
			return null;
		for (StreetI street: streets){
			if(intersection.getStreetList().contains(street))
					return street;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Intersection [p=" + p + ", streets=" + streets + "]\n";
	}
	
	@Override
	public int hashCode(){
		return p.hashCode();
	}
	
	@Override
	public boolean equals(Object i){
		IntersectionI intersection = (IntersectionI) i;
		return p.equals(intersection.getLocation());
	}





}
