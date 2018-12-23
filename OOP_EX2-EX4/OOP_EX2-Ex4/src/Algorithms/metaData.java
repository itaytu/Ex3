package Algorithms;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import GIS.Meta_data;
import Geom.Point3D;

/**
 * This class represents a simple GIS_element Meta_data implementing the
 * Meta_Data interface The GIS_element Meta_data has info like: TimeStamp, UTC
 * time, toString, Orientation
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class metaData implements Meta_data {

	String[] data;
	String[] elements;
	long date;
	long timeStamp;
	static final Long duration = (long) ((120 * 60) * 1000);

	public metaData(String[] s, String[] element) {
		this.data = s;
		this.elements = element;
	}

	public metaData(String[] s) {
		this.data = s;
	}

	public metaData() {
	}

	/**
	 * Setting and Getting the TimeStamp for the moment a GIS_element is created
	 * 
	 * @return long TimeStamp
	 */
	public long SetTimeStamp() {
		this.timeStamp = new Date().getTime();
		return timeStamp;
	}

	public long GetTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * returns the Universal Time Clock associated with this data.
	 */
	@Override
	public long getUTC() {
		// int index = getTimeIndex(elements, "FirstSeen");
		String time = data[3];
		SimpleDateFormat df;
		if (time.contains("-"))
			df = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		else
			df = new SimpleDateFormat("yyyy/dd/MM hh:mm:ss");
		java.util.Date dt;
		try {
			dt = df.parse(time);
			Long l = dt.getTime() + duration;
			return l;
		} catch (ParseException | java.text.ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Get the time for an element from the CSV file
	 * 
	 * @return
	 */
	public String getTime() {
		return data[3];
	}

	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	/**
	 * @return a String representing this data
	 */
	@Override
	public String toString() {
		String meta = "";
		meta += "MacAdress: " + data[0] + "\n";
		meta += "SSID: " + data[1] + "\n";
		meta += "Capabilites: " + data[2] + "\n";
		meta += "Channel: " + data[4] + "\n";
		meta += "RSSI: " + data[5] + "\n";
		meta += "Type: " + data[10];
		return meta;
	}

	/**
	 * Finding the LastTimeSeen element in the CSV file.
	 * 
	 * @param String[]
	 *            Elements
	 * @param String
	 *            element
	 * @return the Index for the LastTimeSeen element in the Array
	 */
	public int getTimeIndex(String[] Elements, String element) {
		int index = -1;
		for (int i = 0; i < Elements.length; i++) {
			if (Elements[i].equals(element))
				return i;
		}
		return index;
	}

}
