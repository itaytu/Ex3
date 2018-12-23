package GIS;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import Geom.Point3D;

/**
 * Class that defines the meta data of a pacman
 */
public class pacmanMetaData implements Meta_data {

	String tmp = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(Calendar.getInstance().getTime());
	String[] meta_data;
	static final Long duration = (long) ((120 * 60) * 1000);

	/**
	 * Constructor that set meta data from array
	 * 
	 * @param metadata
	 *            as array
	 */
	public pacmanMetaData(String[] metadata) {
		this.meta_data = metadata;
	}

	/**
	 * UTC is the universal time that associated with this pacman
	 * 
	 * @return time in long format
	 */
	@Override
	public long getUTC() {
		String time = tmp;
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
	 * Get the yaw, pitch and roll of the associated data. Here it null because
	 * pacman has no orientation
	 * 
	 * @return point
	 */
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	/**
	 * The meta data of a pacman
	 * 
	 * @return meta data of pacman as a string
	 */
	@Override
	public String toString() {
		String data = "";
		data += "Type: " + meta_data[0] + "\n";
		data += "Id: " + meta_data[1] + "\n";
		data += "Speed: " + meta_data[5] + "\n";
		data += "Radius: " + meta_data[6] + "\n";
		return data;
	}

}
