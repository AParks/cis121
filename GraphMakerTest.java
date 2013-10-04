import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphMakerTest {
	GraphMaker m1;
	GraphMaker m2;
	ShortestPath sp;
	IntersectionI start;
	IntersectionI end2;
	IntersectionI end3;
	IntersectionI end4;
	IntersectionI end5;
	IntersectionI end6;
	IntersectionI end7;

	@Before
	public void setUp() throws Exception {
		m1 = new GraphMaker("roadSet1Formatted.txt");
		sp = new ShortestPath(m1);
		HashMap<Point, IntersectionI> x = sp.getRoadMap();

		Point startp = new Point(46.39672434900001,-63.79515405799998); //H
		Point end1p = new Point(46.395763397999986, -63.79356619599997);
		Point end2p = new Point(46.39502922700001, -63.795610694000004); // J
		Point end3p = new Point(46.396430125999984, -63.79345879800002); // I
		Point end4p = new Point(46.39604122600002, -63.79113776899999); // R
		Point end5p = new Point(46.395763397999986, -63.79356619599997); // O
		Point end6p = new Point(46.39539196099997, -63.791323466999984); // Q
		Point end7p = new Point(46.395369499000026, -63.793694415000004); // N

		start = x.get(startp);
		end2 = x.get(end2p);
		end3 = x.get(end3p);
		end4 = x.get(end4p);
		end5 = x.get(end5p);
		end6 = x.get(end6p);
		end7 = x.get(end7p);
	}

	@After
	public void tearDown() throws Exception {
		m1 = null;
	}

	@Test
	public void test_roadSet1Formatted() {
		m1.printPoints();
		List<IntersectionI> path = sp.dijkstraPath(start, end7); // H-I-O-N
		assertEquals(path.get(0), start); // H
		assertEquals(path.get(1), end3); // I
		assertEquals(path.get(2), end5); // O
		assertEquals(path.get(3), end7); // N

		Set<StreetI> streetset = new HashSet<StreetI>();
		for (IntersectionI i : m1.getRoadMap()) {
			List<StreetI> street = i.getStreetList();
			streetset.addAll(street);
		}

		
		List<StreetI> streetlist = new ArrayList<StreetI>();
		streetlist.addAll(streetset);
		Collections.sort(streetlist, new Comparator<StreetI>() {
			public int compare(StreetI i, StreetI j) {
				return i.getDistance().compareTo(j.getDistance());
			}
		});
		for (StreetI s : streetlist) {
			System.out.println(s.getIdNumber() + "______" + s.getDistance());
		}

		double id_4767 = 6.753225572840233E-4; // I-O
		double id_3892 = 0.002273279308040811; // O-Q
		double ids_8286_2785 = 0.0023533845476137486 + 6.752990356070259E-4;
		double id_8365 = 0.0017206026970854194; // H-I
		double id_3899 = 4.142421201888518E-4; // O-N

		double H_O = sp.getDistComp().getDistTo(end5);
		double H_Q = sp.getDistComp().getDistTo(end6);
		double H_N = sp.getDistComp().getDistTo(end7);

		assertEquals(H_O, id_8365 + id_4767, 0.00000000000000001); // H-I-O
		assertTrue(id_4767 + id_3892 < ids_8286_2785);
		assertEquals(H_Q, id_8365 + id_4767 + id_3892, 0.00000000000000001);
		assertEquals(H_N, H_O + id_3899, 0.00000000000000001); // H-I-O-N

		

	}

	@Test
	public void Testminspt() {
		MinSpanningTree m = new MinSpanningTree(m1);
		Set<StreetI> s = m.getMST();
		assertEquals(14, s.size());
		for (StreetI e : s)
			System.out.println("mst contains" + e.getIdNumber());

	}
	
	@Test
	public void Testbfs() {
		BreadthFirstSearch m = new BreadthFirstSearch(m1);
		Point sourcep = new Point(46.39672434900001, -63.79515405799998);

		IntersectionI source = m.getIntersection(sourcep);

		HashMap<IntersectionI, List<IntersectionI>>  s = m.bfsTraverse(source);
		
		for(IntersectionI i : s.keySet()){
			System.out.println("intersection__" + i);
			for(IntersectionI adj : s.get(i)){
			
				System.out.println(i.isConnected(adj).getIdNumber());
			}
				

		}		
	}
	
	@Test
	public void TestMinTurns() {
		MinTurns m = new MinTurns("roadSet1Formatted.txt");
		//assertEquals(0, m.minTurnsBetween(start, end2));
		//assertEquals(0, m.minTurnsBetween(start, end4));
		//assertEquals(0, m.minTurnsBetween(start, end6));
		assertEquals(1, m.minTurnsBetween(start, end5));



	}

}
