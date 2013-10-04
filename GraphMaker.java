import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GraphMaker {
	private List<IntersectionI> roadMap;
	private Set<StreetI> streets;
	
	
	public GraphMaker(String fileName) {

		roadMap = new ArrayList<IntersectionI>();
		streets = new HashSet<StreetI>();

		try {
			parseInput(fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void parseInput(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				fileName)));
		Set<Point> points = new HashSet<Point>();

		scanner.nextLine();
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");
			double x1 = Double.parseDouble(line[1]);
			double y1 = Double.parseDouble(line[2]);
			Point firstPoint = new Point(x1, y1);
			double x2 = Double.parseDouble(line[3]);
			double y2 = Double.parseDouble(line[4]);
			Point secondPoint = new Point(x2, y2);
			int id = Integer.parseInt(line[0]);
			String name = line[5];
			StreetI street = new Street(id, name, firstPoint, secondPoint);
			streets.add(street);
			points.add(firstPoint);
			points.add(secondPoint);
		}

		for (Point point : points) {
			ArrayList<StreetI> pointStreets = new ArrayList<StreetI>();
			for (StreetI street : streets) {
				boolean first = street.getFirstPoint().equals(point);
				boolean second = street.getSecondPoint().equals(point);
				if (first || second) 
					pointStreets.add(street);
				
			}
			Intersection i = new Intersection(point, pointStreets);

			roadMap.add(i);

		}

	}

	public void printPoints() {

		FileWriter outFile;
		try {
			outFile = new FileWriter("adjacencyList.txt");
			BufferedWriter out = new BufferedWriter(outFile);

			for (IntersectionI i : roadMap) {
				out.write("(" + i.getLocation() + ") Adjacent to:");

				for (StreetI street : i.getStreetList()) {
					Point firstPoint = street.getFirstPoint();
					Point secondPoint = street.getSecondPoint();

					if (i.getLocation().equals(firstPoint))
						out.write("(" + secondPoint + ",");

					else
						out.write("(" + firstPoint + ",");
					out.write(street + "),");

				}
				out.write("\n");
			}
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<IntersectionI> getRoadMap() {
		return roadMap;
	}

	public Set<StreetI> getStreets() {
		return streets;
	}

}
