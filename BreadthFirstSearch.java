import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {
	private List<IntersectionI> roadMap;
	private HashMap<Point, IntersectionI> pointsToIMap;

	public BreadthFirstSearch(GraphMaker graph) {
		roadMap = graph.getRoadMap();
		pointsToIMap = new HashMap<Point, IntersectionI>();

		for (IntersectionI i : roadMap)
			pointsToIMap.put(i.getLocation(), i);

	}

	public HashMap<IntersectionI, List<IntersectionI>> bfsTraverse(
			IntersectionI source) {
		HashMap<IntersectionI, List<IntersectionI>> result = new HashMap<IntersectionI, List<IntersectionI>>();

		Set<IntersectionI> visitedNodes = new HashSet<IntersectionI>();
		visitedNodes.add(source);

		Queue<IntersectionI> q = new LinkedList<IntersectionI>();
		q.add(source);
		while (!q.isEmpty()) {
			IntersectionI currIntersection = q.remove();

			if (currIntersection == null)
				return result;

			List<IntersectionI> adjInter = new ArrayList<IntersectionI>();
			List<StreetI> streetList = currIntersection.getStreetList();
			Collections.sort(streetList, new Comparator<StreetI>() {
					public int compare(StreetI i, StreetI j) {
						return i.getDistance().compareTo(j.getDistance());
					}
				});
			
			for (StreetI edge : streetList) {
				IntersectionI o = getOtherIntersection(currIntersection, edge);
				if (!visitedNodes.contains(o)) {
					q.add(o);
					adjInter.add(o);					
				}

			}
			
			result.put(currIntersection, adjInter);
			visitedNodes.addAll(adjInter);

		}
		return result;
	}

	public IntersectionI getOtherIntersection(IntersectionI i, StreetI street) {
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

	public IntersectionI getIntersection(Point p) {
		return pointsToIMap.get(p);
	}
	
	
}
