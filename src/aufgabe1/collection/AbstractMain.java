package aufgabe1.collection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeMap;

import aufgabe1.dictionary.Dictionary;

public class AbstractMain {

	private static TreeMap<String,Object> beans = new TreeMap<String, Object>();
	
	public static Object getBean(String objClass) {
		return beans.get(objClass);
	}
	
	public static boolean setBean(String objClass, Object obj) {
		if (beans.get(objClass) == null) {
			beans.put(objClass, obj);
			return true;
		}	
		return false;
	}
	
	public static void readDictionary(String filepath, Dictionary<String, String> container) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(filepath);
			Scanner scanner = new Scanner(fs);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int firstWordLength = line.indexOf(' ');
				container.insert(line.substring(0, firstWordLength), line.substring(firstWordLength) + 1);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}
	
	
}
