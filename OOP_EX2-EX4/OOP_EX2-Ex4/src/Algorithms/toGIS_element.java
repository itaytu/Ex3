package Algorithms;

import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * This class represents an implementation of the GIS_element interface, in this
 * class we create a toGIS_element holding an Array of Strings for the data and
 * an Array of Strings for the name of the data -> Elements This class has a few
 * simple functions like: get_Meta_Data, getGeom, getName, getTimeStamp,
 * translate etc.
 *
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class toGIS_element implements GIS_element {

	private String[] data;
	private String[] Elements;
	private MyCoords mycoords = new MyCoords();
	private Point3D myPoint;

	public toGIS_element(String[] s, String[] elements, int ind_x, int ind_y, int ind_z) {
		this.data = s;
		this.Elements = elements;
		myPoint = new Point3D(s, ind_x, ind_y, ind_z);
	}

	public toGIS_element(String[] s, int ind_x, int ind_y, int ind_z) {
		this.data = s;
		myPoint = new Point3D(s, ind_x, ind_y, ind_z);
	}

	public toGIS_element(String[] categories) {
		this.Elements = categories;
	}

	/**
	 * This function returns the geometric data associated to the element.
	 * Distance3D, Distance2D
	 * 
	 * @return Geom_element
	 */
	@Override
	public Geom_element getGeom() {
		GeomElement G = new GeomElement(myPoint);
		return G;
	}

	/**
	 * This function returns the Meta_data associated with the element. TimeStamp,
	 * Data, UTC, Orientation, toString
	 * 
	 * @return Meta_data
	 */
	@Override
	public Meta_data getData() {
		metaData metadata = new metaData(data);
		return metadata;
	}

	/**
	 * This function returns the name of the Object element by searching for the
	 * category of the names in the Array.
	 *
	 * for example -> Category: "SSID" -> Object names: Efrat, David, etc.
	 * 
	 * @param String
	 *            Category name
	 * @return String name
	 */

	public String getName(String categoryName) {
		int index = getElementIndex(Elements, categoryName);
		return data[index];
	}

	public String getName() {
		return data[1];
	}

	/**
	 * This function returns the timeStamp for the GIS_element creation as a String.
	 * 
	 * @return String TimeStamp
	 */
	public String getTimeStamp() {
		String tmp = "";
		tmp += data[3];
		tmp = tmp.replace(" ", "T");
		tmp += "Z";
		return tmp;
	}

	/**
	 * @return Point3D point
	 */

	public Point3D getPoint() {
		return myPoint;
	}

	/**
	 * Translates from a vector and a Point to a Second Point
	 */
	@Override
	public void translate(Point3D vec) {
		myPoint = mycoords.add(this.myPoint, vec);
	}

	/**
	 * This function returns the index for a given element.
	 * 
	 * For example -> search for category: "LastSeen" -> return the index of the
	 * category searched column.
	 * 
	 * @param String
	 *            [] Elements
	 * @param String
	 *            element
	 * @return integer index of element
	 */
	public int getElementIndex(String[] Elements, String element) {
		int index = -1;
		for (int i = 0; i < Elements.length; i++) {
			if (Elements[i].equals(element))
				return i;
		}
		return index;
	}

	public String[] getAllData() {
		return Elements;
	}

}
