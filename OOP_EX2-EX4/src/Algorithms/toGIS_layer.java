package Algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;
/**
 * This class represents an implementation of the GIS_layer interface.
 * In this class we create a toGIS_layer holding a Set of GIS_element.
 * The class has functions implemented from the Set class and other functions implemented from GIS_layer
 * 
 * Some of the functions are: size, add, remove, isEmpty, Iterator, get_meta_data etc.
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class toGIS_layer implements GIS_layer {

	private Set<GIS_element> mySet;

	public toGIS_layer(Set<GIS_element> set) {
		this.mySet=set;
	}

	/**
	 * @return integer size of the set
	 */
	@Override
	public int size() {
		int size = mySet.size();
		return size;
	}
	
	/**
	 * Check if set is empty
	 * @return boolean if set is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return mySet.isEmpty();
	}
	
	/**
	 * Check if the set contains a specific object
	 * @return boolean true or false
	 */
	@Override
	public boolean contains(Object o) {
		return mySet.contains(o);
	}
	
	/**
	 * Iterator for the set
	 */
	@Override
	public Iterator<GIS_element> iterator() {
		return mySet.iterator();
	}
	
	/**
	 * Convert the set into an Array of Objects
	 */
	@Override
	public Object[] toArray() {
		return mySet.toArray();
	}
	
	/**
	 * Convert the set into an Array of a given Object <T> a.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return mySet.toArray(a);
	}
	
	/**
	 * add a GIS_element to the set
	 */
	@Override
	public boolean add(GIS_element e) {
		return mySet.add(e);
	}
	
	/**
	 * remove an object from the set
	 */
	@Override
	public boolean remove(Object o) {
		return mySet.remove(o);
	}
	
	/**
	 * Check if the set contains a Collection of c.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return mySet.containsAll(c);
	}
	
	/**
	 * add all the collection to the Set
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return mySet.addAll(c);
	}
	
	/**
	 * Remove from set all the Collections that aren't included 
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return mySet.retainAll(c);
	}
	
	/**
	 * Remove from set all the Collections
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return mySet.removeAll(c);
	}
	
	/**
	 * Clear the set
	 */
	@Override
	public void clear() {
		mySet.clear();
	}
	
	/**
	 * This function creates the Meta_data associated with the GIS_layer.
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
