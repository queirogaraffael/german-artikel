package commons.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextArea;

public class FileManager {

	private static final String FILE_PATH = "C:\\Users\\raffa\\Downloads\\german-artikel\\src\\bibliothek\\woerter.txt";

	private FileWriter file;
	private PrintWriter write;
	int repeat = 0;

	public void openFileReverse() {
		try {
			file = new FileWriter(FILE_PATH, false);
			write = new PrintWriter(file);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void openFile() {
		try {
			file = new FileWriter(FILE_PATH, true);
			write = new PrintWriter(file);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addText(String w) {
		write.print(w);

	}

	public void removeLine(int line) {

		List<String> content = new ArrayList<String>();

		try {
			content = Files.readAllLines(Paths.get(FILE_PATH));
		} catch (Exception e) {
			System.out.println("Coudln't read the file");
		}

		content.remove(line);

		String text = "";

		for (String s : content) {
			text += s + "\n";
		}

		openFileReverse();
		addText(text);
		closeFile();
	}

	public void closeFile() {

		try {
			file.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getLine(int line) {
		String text = "";

		text += getArtikel(line) + " " + getWort(line) + "\n";

		return text;
	}

	public int listLines() {
		int lines = 0;

		try {
			lines = Files.readAllLines(Paths.get(FILE_PATH)).size();
		} catch (Exception e) {
			System.out.println("Coudln't read the file");
		}

		return lines;
	}

	public String SortAll() {

		List<String> content = new ArrayList<String>();

		try {
			content = Files.readAllLines(Paths.get(FILE_PATH));
		} catch (Exception e) {
			System.out.println("Coudln't read the file");
		}

		Collections.sort(content, new WordComparator());

		String text = "";

		for (String s : content) {
			text += s + "\n";
		}

		openFileReverse();
		addText(text);
		closeFile();

		return text;

	}

	public String getPlural(int line) {
		String plural = "";

		try {
			plural = Files.readAllLines(Paths.get(FILE_PATH)).get(line);
		} catch (Exception e) {
			System.out.println("Couldn't read the file");
		}

		String[] words = plural.split(" ");
		plural = words[2];
		return plural;
	}

	public String getArtikel(int line) {
		String artk = "";

		try {
			artk = Files.readAllLines(Paths.get(FILE_PATH)).get(line);
		} catch (Exception e) {
			System.out.println("Couldn't read the file");
		}

		String[] words = artk.split(" ");
		artk = words[0];
		return artk;
	}

	public String getWort(int line) {
		String wort = "";

		try {
			wort = Files.readAllLines(Paths.get(FILE_PATH)).get(line);
		} catch (Exception e) {
			System.out.println("Couldn't read the file");
		}

		String[] words = wort.split(" ");
		wort = words[1];
		return wort;
	}

	public void search(String s, JList<String> list) {

		for (int i = 0; i < listLines(); i++) {
			if (getWort(i).contains(s)) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
			}
		}

	}

	public String getRandomWort(List<Integer> list) {

		if (repeat == 0) {
			repeat++;
			return getWort(list.get(repeat));

		}

		repeat++;

		if (repeat == listLines()) {
			repeat = 0;
			Collections.shuffle(list);

		}

		return getWort(list.get(repeat));

	}

	public int getIndex(JTextArea j) {

		for (int i = 0; i < listLines(); i++) {
			if (j.getText().equals(getWort(i))) {
				return i;
			}
		}

		return 0;

	}

}
