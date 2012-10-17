package aufgabe1.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	
	public static boolean readDictionary(File file, Dictionary<String, DictionaryWordBean> container)  {
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
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public static boolean writeDictionary(File file, Dictionary<String, DictionaryWordBean> container) {
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
					
			for (Dictionary.Element<String, DictionaryWordBean> entry : container.getEntries()) {
				String writeLine = entry.key + " " + entry.value + " ";
				fw.write(writeLine);
			}			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	
}
