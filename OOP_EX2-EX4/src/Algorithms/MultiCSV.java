package Algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import File_format.CSVreader;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
	/**
	 * This class represents a simple function to convert a folder with CSV files into a GIS_Project and then into a KML file
	 * 
	 * @author Itay Tuson and Sagi Oshri
	 *
	 */
public class MultiCSV {

	private static ArrayList<String> myFiles;
	static Scanner s = new Scanner(System.in);


	/**
	 * 
	 * This function scans a folder recursively and converts all the CSV files in it into a toGIS_project 
	 * by using a function called createProject
	 * 
	 * code for scanning a folder recursively from:
	 * https://coderanch.com/t/544063/java/Finding-CSV-files-directory
	 * @param String FolderPath
	 * @return toGIS_project
	 */
	public static toGIS_project parseForCsvFiles(String parentDirectory){
		myFiles = new ArrayList<>();
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				parseForCsvFiles(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			if("csv".equals(fileExtenstion)){
				myFiles.add(filePath);
			}
		} 
		return createProject(myFiles);
	}
	
	/**
	 * This function takes a GIS_project and creates a KML file from it.
	 * 
	 * @param GIS_project myProject
	 * @param String outputAdress
	 * @throws IOException
	 */

	public static void ProjectToKML(GIS_project myProject, String outputAdress) throws IOException {
		ArrayList<String> content =new ArrayList<String>();
		String ProjectTime = myProject.get_Meta_data().toString();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlDocStart = "<Document>\n";
		String kmlDocEnd = "</Document>";
		String kmlend = "</kml>";
		String kmlFolderStart = "<Folder>\n"; String kmlFolderEnd = "</Folder>";
		String kmlFolderName = "<name>"; String kmlFolderNameClose = "</name>\n";
		String kmlProjectTime = "<TimeStamp>\n" + "<when>" + ProjectTime + "</when>\n" + "</TimeStamp>\n";
		content.add(kmlstart);
		content.add(kmlDocStart);
		content.add(kmlProjectTime);
		int index = 1;
		Iterator<GIS_layer> layerIT = myProject.iterator();
		Set<GIS_element> mySet= new HashSet<>();
		GIS_layer tempLayer = new toGIS_layer(mySet);
		try {
			FileWriter fw = new FileWriter(outputAdress);
			BufferedWriter bw = new BufferedWriter(fw);
			while(layerIT.hasNext()) {
				tempLayer= layerIT.next();
				String LayerTime = tempLayer.get_Meta_data().toString();
				String kmlLayerTime = "<TimeStamp>\n" + "<when>" + LayerTime + "</when>\n" + "</TimeStamp>\n";
				Iterator<GIS_element> ElementIT = tempLayer.iterator();
				content.add(kmlFolderStart);
				content.add(kmlLayerTime);
				content.add(kmlFolderName);
				String FolderName=giveFolderaName(index); index++;
				content.add(FolderName);
				content.add(kmlFolderNameClose);
				while(ElementIT.hasNext()) {
					toGIS_element tempElement = (toGIS_element) ElementIT.next();
					String kmlelement ="<Placemark>\n" + 
							"<TimeStamp>\n" + "<when>" + tempElement.getTimeStamp() + "</when>\n" + "</TimeStamp>\n" + 
							"<name>"+tempElement.getName()+"</name>\n" +
							"<description>\n" + tempElement.getData() +"\n" + "</description>\n" +
							"<Point>\n" +
							"<coordinates>" + tempElement.getPoint().get_y() + ","  
							+ tempElement.getPoint().get_x() +  "," 
							+ tempElement.getPoint().get_z() + "</coordinates>\n" +		
							"</Point>\n" +
							"</Placemark>\n";

					content.add(kmlelement);
				}
				content.add(kmlFolderEnd);
			}
			content.add(kmlDocEnd);
			content.add(kmlend);
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i));
			}
			bw.close();
		} catch (Exception e) {
		}
		s.close();
	}
	
	/**
	 * This is a helper function to create a toGIS_project from an ArrayList of string holding the path for 
	 * @param myFiles
	 * @return
	 */

	public static toGIS_project createProject(ArrayList<String> myFiles) {
		Set<GIS_layer> project = new HashSet<>();
		toGIS_project myProject = new toGIS_project(project);
		for (int i = 0; i < myFiles.size(); i++) {
			ArrayList<String[]> tmp = CSVreader.reader(myFiles.get(i));
			Set<GIS_element> mySet = new HashSet<>();
			GIS_layer myLayer  = new toGIS_layer(mySet);
			for (int j = 0; j < tmp.size(); j++) {
				toGIS_element tmpElement = new toGIS_element(tmp.get(j));
				myLayer.add(tmpElement);
			}
			myProject.add(myLayer);			
		}
		return myProject;
	}


	public static ArrayList<String> getMyFiles() {
		return myFiles;
	}
	
	/**
	 * A Function to give the KML folder a name
	 * 
	 * @param int index
	 * @return String KML folder name
	 */
	public static String giveFolderaName(int index) {
		System.out.println("Give folder number " + index + " a name");
		String name = s.nextLine();
		return name;
	}
	
}
