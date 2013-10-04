
public class Street implements StreetI {
	
	private int id;
	private String name;
	private Point firstPoint;
	private Point secondPoint;
	private double distance;

	public Street(int id, String name, Point firstPoint, Point secondPoint){
		setIdNumber(id);
		setName(name);
		setPoints(firstPoint, secondPoint);
		computeDistance();
	}
	
	@Override
	public void setIdNumber(int id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setPoints(Point firstPoint, Point secondPoint) {
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;


	}

	@Override
	public Point getFirstPoint() {
		return firstPoint;
	}

	@Override
	public Point getSecondPoint() {
		return secondPoint;
	}

	@Override
	public int getIdNumber() {
		return id;
	}

	private void computeDistance(){
		double x = firstPoint.getx() - secondPoint.getx();
		double y = firstPoint.gety() - secondPoint.gety();
		distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
	}
	
	
	@Override
	public Double getDistance() {		
		return distance;
	}

	
	@Override
	public String toString(){
		return name + "," + getDistance() + "," + id;
	}

}
