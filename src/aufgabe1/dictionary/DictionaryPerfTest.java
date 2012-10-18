package aufgabe1.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import aufgabe1.collection.AbstractMain;

public class DictionaryPerfTest {
	
	public static void main(String[] agrs) {
		int testingCount = 5;
		double divTimeConstant = Math.pow(10, 9);
		
		Dictionary<String, String> dict = null;
		Scanner scanner = null;
		StringBuilder stBuilder = null;
		
		// read File
		stBuilder = new StringBuilder();
		AbstractMain.readFileToStringBuilder(new File("8000.txt"), stBuilder);
	
		
		// Test create Directory
		for (int i = 1; i <= testingCount; i++) {
			dict = new SortedArrayDictionary<String, String>();
			scanner = new Scanner(stBuilder.toString());
			
			long t1 = System.nanoTime();
			
			AbstractMain.scannInput(scanner, dict); // create Dictionary		
			
			System.out.printf("Testing 8000 entries (SortedArray): %d) %.3f %n", i, (System.nanoTime() - t1) / divTimeConstant);		
		}
		
		// Test search Directory
		for (int i = 1; i <= testingCount; i++) {
			long t1 = System.nanoTime();
			for (Dictionary.Element<String, String> entry : dict.getEntries()) {
				dict.search(entry.key);
			}		
			System.out.printf("Searching 8000 entries (SortedArray): %d) %.3f %n", i, (System.nanoTime() - t1) / divTimeConstant);		
		}
		
		
		// ----------------------------------
		
		// read File
		stBuilder = new StringBuilder();
		AbstractMain.readFileToStringBuilder(new File("16000.txt"), stBuilder);
	
		
		// Test create Directory
		for (int i = 1; i <= testingCount; i++) {
			dict = new SortedArrayDictionary<String, String>();
			scanner = new Scanner(stBuilder.toString());
			
			long t1 = System.nanoTime();
			
			AbstractMain.scannInput(scanner, dict); // create Dictionary		
			
			System.out.printf("Testing 16000 entries (SortedArray): %d) %.3f %n", i, (System.nanoTime() - t1) / divTimeConstant);			
		}
		

	}

}
