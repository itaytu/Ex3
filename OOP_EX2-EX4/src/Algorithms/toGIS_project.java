package Algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;

/**
 * This class represents an implementation of the GIS_project interface.
 * In this class we create a toGIS_project holding a Set of GIS_layer.
 * The class has functions implemented from the Set class and other functions implemented from GIS_project
 * 
 * Some of the functions are: size, add, remove, isEmpty, Iterator, get_meta_data etc.
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */

public class toGIS_project implements GIS_project {

	private Set<GIS_layer> myProject;
	
	public toGIS_project(Set<GIS_layer> layerToAdd) {
		this.myProject = layerToAdd;
	}
	
	/**
	 * @return integer size of the set
	 */
	@Override
	public int size() {
		return myProject.size(); 
	}

	/**
	 * Check if set is empty
	 * @return boolean if set is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return myProject.isEmpty();
	}

	/**
	 * Check if the set contains a specific object
	 * @return boolean true or false
	 */
	@Override
	public boolean contains(Object o) {
		return myProject.contains(o);
	}

	/**
	 * Iterator for the set
	 */
	@Override
	public Iterator<GIS_layer> iterator() {
		return myProject.iterator();
	}

	/**
	 * Convert the set into an Array of Objects
	 */
	@Override
	public Object[] toArray() {
		return myProject.toArray();
	}

	/**
	 * Convert the set into an Array of a given Object <T> a.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return myProject.toArray(a);
	}

	/**
	 * add a GIS_layer to the set
	 */
	@Override
	public boolean add(GIS_layer e) {
		return myProject.add(e);
	}

	/**
	 * remove an object from the set
	 */
	@Override
	public boolean remove(Object o) {
		return myProject.remove(o);
	}

	/**
	 * Check if the set contains a Collection of c.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return myProject.containsAll(c);
	}

	/**
	 * add all the collection to the Set
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return myProject.addAll(c);
	}

	/**
	 * Remove from set all the Collections that aren't included 
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return myProject.retainAll(c);
	}

	/**
	 * Remove from set all the Collections
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return myProject.removeAll(c);
	}

	/**
	 * Clear the set
	 */
	@Override
	public void clear() {
		myProject.clear();
	}

	/**
	 * This function creates the Meta_data associated with the GIS_project.
	 * The Meta_data contains: UTC, Orientation, toString
	 * 
	 * @return Meta_data
	 */
	@Override
	public Meta_data get_Meta_data() {
		layerProjectMetaData myData = new layerProjectMetaData();
		return myData;
	}

}
