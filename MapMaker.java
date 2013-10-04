import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MapMaker {
	private static ShortestPath[] sp;
	private static BreadthFirstSearch[] bfs;
	private static MinSpanningTree[] mst;
	private static GraphMaker[] m;
	private static String[] tf;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		m = new GraphMaker[3];
		sp = new ShortestPath[3];
		bfs = new BreadthFirstSearch[3];
		mst = new MinSpanningTree[3];
		tf = new String[3];
		tf[0] = "roadSet1Formatted";
		tf[1] = "penn";
		tf[2] = "ny";
		for (int i = 0; i < 3; i++) { // one for each file
			m[i] = new GraphMaker(tf[i] + ".txt");
			sp[i] = new ShortestPath(m[i]);
			bfs[i] = new BreadthFirstSearch(m[i]);
			mst[i] = new MinSpanningTree(m[i]);
		}

		// MinTurns m = new MinTurns("roadSet1Formatted.txt");

		setUp(0, 46.39672434900001, -63.79515405799998, 46.39539196099997,
				-63.791323466999984);
		setUp(1, 39.953902, -75.194623, 39.954379, -75.198469);
		setUp(2, 40.741668, -74.001129, 40.744035, -74.003116);

	}

	private static void setUp(int j, double x1, double y1, double x2, double y2) {
		Point[] points = new Point[2];
		points[0] = new Point(x1, y1);
		points[1] = new Point(x2, y2);

		HashMap<Point, IntersectionI> x = sp[j].getRoadMap();
		IntersectionI start = x.get(points[0]);
		IntersectionI end = x.get(points[1]);
		generateSPPoints(j, start, end);
		generateMSTPoints(j);
		generateBFSPoints(j, start);
	}

	private static void generateSPPoints(int j, IntersectionI start,
			IntersectionI end) {
		List<IntersectionI> splist = sp[j].dijkstraPath(start, end);
		JavaScriptPointsWriter sp = new JavaScriptPointsWriter("points1", "_"
				+ tf[j], splist);

	}

	private static void generateBFSPoints(int j, IntersectionI start) {
		HashMap<IntersectionI, List<IntersectionI>> map = bfs[j]
				.bfsTraverse(start);
		List<IntersectionI> bfslist = generateTree(map, start);
		JavaScriptPointsWriter bfs = new JavaScriptPointsWriter("points3", "_"
				+ tf[j], bfslist);

	}

	private static void generateMSTPoints(int j) {
		Set<StreetI> streetset = mst[j].getMST();
		List<IntersectionI> mstlist = new ArrayList<>();
		for (StreetI street : streetset) {
			IntersectionI i1 = mst[j].getIntersection(street.getFirstPoint());
			IntersectionI i2 = mst[j].getIntersection(street.getSecondPoint());
			mstlist.add(i1);
			mstlist.add(i2);
		}
		JavaScriptPointsWriter mst = new JavaScriptPointsWriter("points2", "_"
				+ tf[j], mstlist);
	}

	private static ArrayList<IntersectionI> generateTree(
			HashMap<IntersectionI, List<IntersectionI>> map, IntersectionI start) {

		ArrayList<IntersectionI> arrayList = new ArrayList<IntersectionI>();
		Queue<IntersectionI> q = new LinkedList<IntersectionI>();
		q.add(start);
		while (!q.isEmpty()) {
			IntersectionI currIntersection = q.remove();
			List<IntersectionI> nextLevel = map.get(currIntersection);

			for (IntersectionI i : nextLevel) {
				arrayList.add(currIntersection);
				arrayList.add(i);
				q.add(i);
			}
		}

		return arrayList;
	}

}
