import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class ShortestPath {

	private PriorityQueue<IntersectionI> pq;
	private HashMap<Point, IntersectionI> pointsToIMap;
	private HashMap<IntersectionI, StreetI> edgeTo;
	private DistanceComparator<IntersectionI> distComp;
	private List<IntersectionI> graph;

	public ShortestPath(GraphMaker graph) {
		this.graph = graph.getRoadMap();
		pointsToIMap = new HashMap<Point, IntersectionI>();
		edgeTo = new HashMap<IntersectionI, StreetI>();
		distComp = new DistanceComparator<IntersectionI>();
		pq = new PriorityQueue<IntersectionI>(10, distComp);
		for (IntersectionI i : this.graph) {
			distComp.setDistTo(i, Double.POSITIVE_INFINITY);
			pointsToIMap.put(i.getLocation(), i);
		}

	}

	public List<IntersectionI> dijkstraPath(IntersectionI start,
			IntersectionI end) {
		
		computeSP(start);
		if (!hasPathTo(end))
			return null;
		Stack<IntersectionI> path = new Stack<IntersectionI>();
		IntersectionI cur = end;
		StreetI e = edgeTo.get(cur);
		while (e != null) {
			path.push(cur);
			cur = getOtherIntersection(cur, e);
			e = edgeTo.get(cur);
		}
		path.push(start);
		Collections.reverse(path);
		return path;
	}

	// begin book code
	public void computeSP(IntersectionI start) {

		distComp.setDistTo(start, 0.0);
		pq.add(start);
		while (!pq.isEmpty())
			relax(pq.remove());
		
	}

	private void relax(IntersectionI v) {
		for (StreetI street : v.getStreetList()) {

			IntersectionI w = getOtherIntersection(v, street);

			double distToV = distComp.getDistTo(v);
			double newDistance = distToV + street.getDistance();
			if (distComp.getDistTo(w) > newDistance) {
				edgeTo.put(w, street);
				distComp.setDistTo(w, newDistance);
				if (pq.contains(w))
					pq.remove(w);
				pq.offer(w);
			}
		}
	}

	public boolean hasPathTo(IntersectionI v) {
		return distComp.getDistTo(v) < Double.POSITIVE_INFINITY;
	}

	// end book code

	private IntersectionI getOtherIntersection(IntersectionI i, StreetI street) {
		Point p = i.getLocation();
		Point first = street.getFirstPoint();
		Point second = street.getSecondPoint();

		if (p.equals(first))
			return pointsToIMap.get(second);
		else if (p.equals(second))
			return pointsToIMap.get(first);
		else
			return null;
	}

	public HashMap<Point, IntersectionI> getRoadMap() {
		return pointsToIMap;
	}

	public DistanceComparator<IntersectionI> getDistComp() {
		return distComp;
	}

}
