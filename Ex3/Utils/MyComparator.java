package Utils;

import java.util.Comparator;

import Algorithms.Path;

public class MyComparator implements Comparator<Path> {

	@Override
	public int compare(Path p1, Path p2) {
		if (p1.getTimeToFruit() < p2.getTimeToFruit()) {
			return -1;
		} else if (p2.getTimeToFruit() < p1.getTimeToFruit()) {
			return 1;
		}
		return 0;
	}

}
