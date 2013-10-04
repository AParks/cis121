import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class MinSpanningTree {

	private QuickUnionUF uf;
	private PriorityQueue<StreetI> pq;
	private HashMap<Point, IntersectionI> pointsToIMap;
	private List<IntersectionI> roadMap;


	public MinSpanningTree(GraphMaker graph) {
		roadMap = graph.getRoadMap();
		pointsToIMap = new HashMap<Point, IntersectionI>();			
		uf = new QuickUnionUF(roadMap);
	}

	public Set<StreetI> getMST() {
		

		List<StreetI> streets = new ArrayList<StreetI>();
		Set<StreetI> mst = new HashSet<StreetI>();
		

		for (IntersectionI i : roadMap){
			streets.addAll(i.getStreetList());
			pointsToIMap.put(i.getLocation(), i);
		}


		pq = new PriorityQueue<StreetI>(roadMap.size(),
				new Comparator<StreetI>() {
					public int compare(StreetI i, StreetI j) {
						return i.getDistance().compareTo(j.getDistance());
					}
				});
		pq.addAll(streets);

		while (!pq.isEmpty()) {
			StreetI e = pq.remove();
			IntersectionI first = pointsToIMap.get(e.getFirstPoint());
			IntersectionI second = pointsToIMap.get(e.getSecondPoint());

			if (!uf.connected(first, second)) {// two points are connected
				uf.union(first, second);
				mst.add(e);
			}

		}

		return mst;

	}
	
	public IntersectionI getIntersection(Point p) {
		return pointsToIMap.get(p);
	}


}
