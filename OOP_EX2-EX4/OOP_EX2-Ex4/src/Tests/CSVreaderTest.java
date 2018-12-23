package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import File_format.CSVreader;

class CSVreaderTest {
	String[] firstElementExpected = { "40:65:a3:35:4c:c4", "Efrat", "[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]",
			"2017-12-01 10:49:08", "1", "-75", "32.17218268216534", "34.81446401702757", "13.65040888895076", "6",
			"WIFI" };
	ArrayList<String[]> myDataString = CSVreader.reader("C:/Test/WigleWifi_20171201110209.csv", 1);
	String[] firstElementActual = myDataString.get(0);
	String[] expectedCategories = { "MAC", "SSID", "AuthMode", "FirstSeen", "Channel", "RSSI", "CurrentLatitude",
			"CurrentLongitude", "AltitudeMeters", "AccuracyMeters", "Type" };
	String[] actualCategories = CSVreader.getElements();

	@Test
	void testReaderString() {
		for (int i = 0; i < firstElementExpected.length; i++) {
			String expected = firstElementExpected[i];
			String actual = firstElementActual[i];
			assertEquals(expected, actual, "Function is wrong, the data in: " + firstElementActual[i] + "is incorrect");
		}
	}

	@Test
	void testGetElements() {
		for (int i = 0; i < expectedCategories.length; i++) {
			String expected = expectedCategories[i];
			String actual = actualCategories[i];
			assertEquals(expected, actual, "Function is wrong, the data in: " + actualCategories[i] + "is incorrect");
		}
	}

}
