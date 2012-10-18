package aufgabe1.dictionary;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import aufgabe1.collection.AbstractMain;

public class DictionaryPerfTest {
	
	public static void main(String[] agrs) {
		executeTests(new File("8000.txt"), ChoiseImpl.SORTED_ARRAY_IMPL);
//		executeTests(new File("16000.txt"), ChoiseImpl.SORTED_ARRAY_IMPL);
		
		executeTests(new File("8000.txt"), ChoiseImpl.MAP_HASHMAP_IMPL);
//		executeTests(new File("16000.txt"), ChoiseImpl.MAP_HASHMAP_IMPL);
		
		executeTests(new File("8000.txt"), ChoiseImpl.MAP_TREEMAP_IMPL);
//		executeTests(new File("16000.txt"), ChoiseImpl.MAP_TREEMAP_IMPL);
		
		executeTests(new File("8000.txt"), ChoiseImpl.HASH_IMPL);
//		executeTests(new File("16000.txt"), ChoiseImpl.HASH_IMPL);
	
		executeTests(new File("8000.txt"), ChoiseImpl.TREE_IMPL);
//		executeTests(new File("16000.txt"), ChoiseImpl.TREE_IMPL);
	}

	private static Dictionary<String, String> createDictionary(ChoiseImpl implementation) {
		switch (implementation) {
		case SORTED_ARRAY_IMPL:
			return new SortedArrayDictionary<String, String>();
		case HASH_IMPL:
			return new HashDictionary<String, String>();
		case MAP_HASHMAP_IMPL:
			return new MapDictionary<String, String>(new HashMap<String, String>());
		case MAP_TREEMAP_IMPL:
			return new MapDictionary<String, String>(new TreeMap<String, String>());
		case TREE_IMPL:
			return new TreeDictionary<String, String>();
		}
		return null;
	}

	private static void executeTests(File input, ChoiseImpl implementation) {
		int testingCount = 3;  
		double nf = Math.pow(10, 6);  // factor to convert from nano seconds to milliseconds

		System.out.printf("~~~ Perfoming tests on %s, using %s%n" +
				"~~~ average results based on %d test runs:%n", input.getName(), implementation.name(), testingCount);
		
		Dictionary<String, String> dict = null;
		Scanner scanner = null;
		StringBuilder stBuilder = new StringBuilder();

		// Read test input from file
		AbstractMain.readFileToStringBuilder(input, stBuilder);

		double runtime = 0;

		// --------------------------------  Perform loading test -------------------------------------

		for (int i = 0; i <= testingCount; i++) {

			// Prepare objects
			dict = createDictionary(implementation);
			scanner = new Scanner(stBuilder.toString());
			long starttime = System.nanoTime();

			// create dictionary containing the entries offered by the scanner.
			AbstractMain.scanInput(scanner, dict); 

			if (i != 0) // first test run is ignored because of java`s just-in-time compilation
				runtime += (System.nanoTime() - starttime) / nf;
		}
		
		runtime = runtime / testingCount;
		System.out.printf("average load time: %.1f msec%n", runtime);

		// -----------------------------  Perform successfull-search test --------------------------------

		// dict created in the last test is used again at this point.
		// preperare list of entries that are going to be searched (and found)
		List<Dictionary.Element<String, String>> entries = dict.getEntries();
		runtime = 0;
		for (int i = 0; i <= testingCount; i++) {

			long starttime = System.nanoTime();
			for (Dictionary.Element<String, String> element : entries) {
				if (dict.search(element.key) == null) throw new RuntimeException("darfs net geben");
			}

			if (i != 0) // first test run is ignored because of java`s just-in-time compilation
				runtime += (System.nanoTime() - starttime) / nf;
		}
		
		runtime = runtime / testingCount;
		System.out.printf("average full-search (existing elements) time: %.1f msec%n", runtime);
		
		// -----------------------------  Perform successfull-search test --------------------------------

		// dict and entries created in the last tests are used again at this point.
		
		runtime = 0;
		for (int i = 0; i <= testingCount; i++) {

			long starttime = System.nanoTime();
			for (Dictionary.Element<String, String> element : entries) {
				dict.search(element.value);
			}

			if (i != 0) // first test run is ignored because of java`s just-in-time compilation
				runtime += (System.nanoTime() - starttime) / nf;
		}
		
		runtime = runtime / testingCount;
		System.out.printf("average full-search (non-existing elements) time: %.1f msec%n", runtime);
	}


}
