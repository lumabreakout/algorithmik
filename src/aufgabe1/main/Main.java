package aufgabe1.main;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.dictionary.SortedArrayDictionary;
import aufgabe1.gui.FrmMainWindowDictionary;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // create Beans
//		 AbstractMain.setBean("IHandleTelefonBuch", new HandleTelefonBuch());
		 
		 // create GUI
	     FrmMainWindowDictionary frmDictionary = new FrmMainWindowDictionary();
	     frmDictionary.setVisible(true);
		Dictionary<String, String> dict = new SortedArrayDictionary<>();
		AbstractMain.readDictionary("D:\\dtengl.txt", dict);
		
		System.out.println(dict.toString());
	}

}
