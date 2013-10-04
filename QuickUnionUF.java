import java.util.HashMap;
import java.util.List;

/* USING CODE FROM SEDGEWICK */
public class QuickUnionUF {
	private HashMap<IntersectionI, IntersectionI> id; // parent link (site indexed)
	private HashMap<IntersectionI, Integer> sz; // size of component for roots (site indexed)
	private int count; // number of components

	public QuickUnionUF(List<IntersectionI> roadMap) {
		count = roadMap.size();
		id = new HashMap<IntersectionI, IntersectionI>();
		sz = new HashMap<IntersectionI, Integer>();

		for (IntersectionI i : roadMap){
			id.put(i, i);
			sz.put(i, 0);
		}
		
		
	}

	public int count() {
		return count;
	}

	public boolean connected(IntersectionI p, IntersectionI q) {
		return find(p).equals(find(q));
	}

	private IntersectionI find(IntersectionI p) { // Follow links to find a root.
		while (p != id.get(p))
			p = id.get(p);
		return p;
	}

	public void union(IntersectionI p, IntersectionI q) {
		IntersectionI i = find(p);
		IntersectionI j = find(q);
		if (i.equals(j))
			return;
		// Make smaller root point to larger one.
		if (sz.get(i) < sz.get(j)) {
			id.put(i, id.get(j));
			sz.put(j, sz.get(j) + sz.get(i));			
		} else {
			id.put(j, id.get(i));
			sz.put(i, sz.get(i) + sz.get(j));			
		}
		count--;
	}
}
/* END CODE FROM SEDGEWICK */
