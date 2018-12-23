package GUI;

import java.util.ArrayList;

import GIS.Fruit;
import GIS.Pacman;
import Geom.Point3D;

/**
 * Class that defines the smooth movement of a pacman between two points
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class pacmanMovement {

	private ArrayList<Fruit> fruitsPath;
	private ArrayList<Point3D> pointsPath;
	private Point3D startPoint;
	private Pacman pacman;
	private double pathTime;

	/**
	 * Construct new movemnt from a pacman's fruit paths
	 * 
	 * @param p
	 *            pacman
	 */
	public pacmanMovement(Pacman p) {
		this.pacman = p;
		this.fruitsPath = pacman.getFruitPath();
		startPoint = new Point3D(pacman.getPoint());
		pointsPath = new ArrayList<>();
		this.pathTime = pacman.getTime();
		CreatePath(pacman);
	}

	/**
	 * Calculates and creates new path with smooth movement for a given pacman
	 * 
	 * @param p
	 *            pacman
	 * @return The smooth movement path
	 */
	private ArrayList<Point3D> CreatePath(Pacman p) {
		Point3D tmp;
		Point3D startPoint = new Point3D(p.getPoint());
		double t2 = 2;
		for (int i = 0; i < p.getFruitPath().size(); i++) {
			Point3D endPoint = new Point3D(p.getFruitPath().get(i).getPoint());
			pathTime = p.getTimePath().get(i);
			double t1 = pathTime;
			while (t1 - t2 > 2) {
				double x1 = (t2 / pathTime) * (endPoint.get_x() - startPoint.get_x()) + startPoint.get_x();
				double y1 = (t2 / pathTime) * (endPoint.get_y() - startPoint.get_y()) + startPoint.get_y();
				startPoint.set_x(x1);
				startPoint.set_y(y1);
				tmp = new Point3D(startPoint);
				pointsPath.add(tmp);
				t2 = t2 + 2;
			}
			startPoint.set_x(endPoint.get_x());
			startPoint.set_y(endPoint.get_y());
			tmp = new Point3D(startPoint);
			pointsPath.add(tmp);
			t2 = 2;
		}
		return pointsPath;
	}

	public ArrayList<Point3D> getMovement() {
		return this.pointsPath;
	}
}
