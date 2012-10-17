package aufgabe1.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import aufgabe1.dictionary.Dictionary;
import aufgabe1.dictionary.DictionaryWordBean;

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
	
	public static void readDictionary(File file, Dictionary<String, DictionaryWordBean> container)  {
		FileInputStream fs;
		try {
			fs = new FileInputStream(file);
			Scanner scanner = new Scanner(fs);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int firstWordLength = line.indexOf(' ');
				String de = line.substring(0, firstWordLength);
				String en = line.substring(firstWordLength + 1);
				DictionaryWordBean obj = new DictionaryWordBean(de, en);
				container.insert(de, obj);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
}
