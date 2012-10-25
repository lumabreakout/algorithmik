package aufgabe1.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import aufgabe1.dictionary.Dictionary;

public class AbstractMain {

	private static TreeMap<String,Object> beans = new TreeMap<String, Object>();
	
	public static Object getBean(String objClass) {
		return beans.get(objClass);
	}
	
	public static boolean setBean(String objClass, Object obj) {
		beans.put(objClass, obj);
		if (beans.get(objClass) == null) {			
			return true;
		}			
		return false;
	}
	
	/**
	 * Insert every entry that the pre-initialized scanner offers to the given dictionary.
	 */
	public static void scanInput(Scanner scanner, Dictionary<String, String> container)  {
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int firstWordLength = line.indexOf(' ');
			String de = line.substring(0, firstWordLength);
			String en = line.substring(firstWordLength + 1);				
			container.insert(de, en);
		}
	}
	
	public static boolean readFileToStringBuilder(File file, StringBuilder stBuilder) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(file);
			Scanner scanner = new Scanner(fs);
			while (scanner.hasNextLine()) {
				stBuilder.append(scanner.nextLine());
				stBuilder.append("\n");
			}
			scanner.close();
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
	
	/**
	 * Insert every entry that the given dictionary file to the container. 
	 */
	public static boolean readDictionary(File file, Dictionary<String, String> container)  {
		FileInputStream fs;
		try {
			fs = new FileInputStream(file);
			Scanner scanner = new Scanner(fs);
			scanInput(scanner, container);
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
	
	public static boolean writeDictionary(File file, Dictionary<String, String> container) {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
					
			for (Dictionary.Element<String, String> entry : container.getEntries()) {
				String writeLine = entry.key + " " + entry.value + "\n";
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
