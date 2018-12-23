package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Algorithms.toGIS_element;
import Algorithms.toGIS_layer;

import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;

/**
 * This class represents a simple conversion of a CSV file into a GIS_layer and from that into a KML file.
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class Csv2Kml {

	/**
	 * This function gets the CSV file address and the output address to save the KML file, and turns the CSV
	 * file into a GIS_layer containing a Set of GIS_element and then converts it into a KML file.
	 * 
	 * @param String inputAddress
	 * @param String outputAddress
	 */
	public static void writeFileKML(String inputAddress, String outputAddress) {	
		ArrayList<String> content =new ArrayList<String>();
		ArrayList<String[]> get=CSVreader.reader(inputAddress);
		Set<GIS_element> mySet = new HashSet<>();
		toGIS_layer myLayer = new toGIS_layer(mySet);
		for (int i = 0; i < get.size(); i++) {
			toGIS_element myElement = new toGIS_element(get.get(i));
			myLayer.add(myElement);
		}
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlduc = "<Document>";
		String kmlduce = "</Document>";
		String kmlend = "</kml>";
		content.add(kmlstart);
		content.add(kmlduc);
		try{
			FileWriter fw = new FileWriter(outputAddress);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<GIS_element> iterator = myLayer.iterator(); 
			while(iterator.hasNext()) {
				toGIS_element tempElement = (toGIS_element) iterator.next();
				String kmlelement ="<Placemark>\n" +
						"<name>"+tempElement.getName()+"</name>\n" +
						"<description>\n" + tempElement.getData() +"\n" + "</description>\n" +
						"<Point>\n" +
						"<coordinates>" + tempElement.getPoint().get_y() + ","  
						+ tempElement.getPoint().get_x() +  "," 
						+ tempElement.getPoint().get_z() + "</coordinates>" +		
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);
			}
			content.add(kmlduce);
			content.add(kmlend);
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i));
			}
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
