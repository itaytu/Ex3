package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class turns a CSV file into an ArrayList containing Arrays of Strings
 * and another Array of Strings for the type of elements, for example -> name,
 * TimeSeen, SSID, ID etc.
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class CSVreader {

	// private ArrayList<GIS_Object> myUsers;
	private static String[] Elements;
	private static ArrayList<String[]> myData;
	// TODO:
	// private int width, length, rowIndex;

	/**
	 * This functions gets a CSV file and converts the information in it into an
	 * ArrayList.
	 * 
	 * @param file
	 *            file
	 * @return ArrayList containing Arrays of Strings
	 */
	public static ArrayList<String[]> reader(File file) {
		File newFile = new File(file.toString());
		String line = "";
		myData = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(newFile));
			br.readLine();
			String element = br.readLine();
			Elements = element.split(",");
			// System.out.println(Elements.toString());
			while ((line = br.readLine()) != null) {
				String[] lineDataDetails = line.split(",");
				myData.add(lineDataDetails);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file " + file.toString() + " wasn't found " + e);
		}
		return myData;
	}

	/**
	 * This function gets the CSV file String address and converts the information
	 * in it into an ArrayList.
	 * 
	 * @param String
	 *            fileName
	 * @return ArrayList containing Arrays of Strings
	 */
	public static ArrayList<String[]> reader(String fileName, int startReadingFrom) {

		File file = new File(fileName);
		String line = "";
		myData = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < startReadingFrom; i++) {
				br.readLine();
			}
			String element = br.readLine();
			Elements = element.split(",");
			while ((line = br.readLine()) != null) {
				String[] lineDataDetails = line.split(",");
				// System.out.println(Arrays.toString(lineDataDetails));
				myData.add(lineDataDetails);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file " + file.toString() + " wasn't found " + e);
		}
		return myData;
	}

	/**
	 * @return String[] type of elements associated with the file.
	 */
	public static String[] getElements() {
		return Elements;
	}

	/**
	 * @return ArrayList containing the data of the CSV file.
	 */
	public static ArrayList<String[]> getMyData() {
		return myData;
	}

}
