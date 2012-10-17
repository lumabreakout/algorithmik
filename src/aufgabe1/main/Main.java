package aufgabe1.main;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.dictionary.DictionaryWordBean;
import aufgabe1.dictionary.SortedArrayDictionary;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dictionary<String, String> dict = new SortedArrayDictionary<>();
		AbstractMain.readDictionary("D:\\dtengl.txt", dict);
		
		System.out.println(dict.toString());
	}

}
