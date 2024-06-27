package commons.utils;

import java.util.Comparator;

class WordComparator implements Comparator<String> {

	public int compare(String s1, String s2) {

		String a = s1.substring(4, s1.length());
		String b = s2.substring(4, s2.length());

		return a.compareToIgnoreCase(b);

	}
}