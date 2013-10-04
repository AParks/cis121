import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class MinTurns {

	private HashMap<String, Set<IntersectionI>> graph;
	private PriorityQueue<IntersectionI> pq;
	private DistanceComparator<IntersectionI> distComp;
	private HashMap<IntersectionI, StreetI> edgeTo;
	private HashMap<Point, IntersectionI> pointsToIMap;

	public MinTurns(String filename) {
		distComp = new DistanceComparator<IntersectionI>();
		pq = new PriorityQueue<IntersectionI>(10, distComp);
		pointsToIMap = new HashMap<Point, IntersectionI>();
		//edgeTo = new HashMap<IntersectionI, StreetI>();


		graph = new HashMap<String, Set<IntersectionI>>();
		GraphMaker m1 = new GraphMaker(filename);
		for (IntersectionI i : m1.getRoadMap()) {
			distComp.setDistTo(i, Double.POSITIVE_INFINITY);
			pointsToIMap.put(i.getLocation(), i);
		}

		
		for (StreetI street : m1.getStreets()) {
			Set<IntersectionI> sameStreet = new HashSet<IntersectionI>();
			for (IntersectionI i : m1.getRoadMap()) {
				List<StreetI> x = i.getStreetList();
				for(StreetI s : x)
					if (s.getName().equals(street.getName()))
						sameStreet.add(i);
			}
			graph.put(street.getName(), sameStreet);
		}
		
		for (String name : graph.keySet()) {
			System.out.println(name + "__");
			for (IntersectionI i : graph.get(name))
				System.out.print(i);

			System.out.println();
		}
	}

	

	public int minTurnsBetween(IntersectionI source, IntersectionI destination) {
		if (source.isConnected(destination) != null)
			return 0;
		else {
			computeSP(source);
		}
//		System.out.println(distComp.getDistTo(i));
		

		return (int)distComp.getDistTo(destination);
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

			boolean isTurn = true;
			Set<IntersectionI> sameStreetSet = graph.get(street.getName());
			if(sameStreetSet.contains(w))
				isTurn = false;
				
			double numTurns = distComp.getDistTo(v);
			int cost = 0;
			if (isTurn)
				cost = 1;
			double newNumTurns = numTurns + cost;
			if (distComp.getDistTo(w) > newNumTurns) {
				//edgeTo.put(w, street);
				distComp.setDistTo(w, newNumTurns);
				if (pq.contains(w))
					pq.remove(w);
				pq.offer(w);
			}
		}
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

}
