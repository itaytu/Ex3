package Algorithms;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import GIS.Meta_data;
import Geom.Point3D;

/**
 * This class represents a simple GIS_layer/GIS_project Meta_data implementing the Meta_Data interface
 * The Meta_data has info like: TimeStamp, UTC time, toString, Orientation
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class layerProjectMetaData implements Meta_data{
	
	String tmp= new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(Calendar.getInstance().getTime());
	static String timeStamp;
	static final Long duration = (long) ((120*60) * 1000);

	/**
	 * returns the Universal Time Clock associated with this data.
	 */
	@Override
	public long getUTC() {
		String time = tmp;
		SimpleDateFormat df;
		if(time.contains("-")) df = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		else df = new SimpleDateFormat("yyyy/dd/MM hh:mm:ss");
		java.util.Date dt;
		try {
			dt = df.parse(time);
			Long l = dt.getTime()+duration;
			return l;
		} catch (ParseException | java.text.ParseException e) {
			e.printStackTrace();
		}                                      		
		return -1;
	}

	/**
	 *@return the orientation: yaw, pitch and roll associated with this data;
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
		timeStamp="";
		timeStamp+=tmp.replace(" ", "T") + "Z";
		return timeStamp;
	}

	
}
