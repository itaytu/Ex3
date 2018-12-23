package Tests;

import java.io.IOException;

import Algorithms.MultiCSV;
import Algorithms.toGIS_project;

public class mainTest {

	public static void main(String[] args) throws IOException {
		// String inputAddress = "C:\\Test\\WigleWifi_20171201110209.csv";
		String outputAddress = "C:\\Test\\yuval.kml";
		toGIS_project project = MultiCSV.parseForCsvFiles("C:\\Test");
		MultiCSV.ProjectToKML(project, outputAddress);
	}

}
