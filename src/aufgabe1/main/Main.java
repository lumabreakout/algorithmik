package aufgabe1.main;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.SortedArrayDictionary;
import aufgabe1.gui.FrmMainWindowDictionary;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // create Beans
		 AbstractMain.setBean("Dictionary", new SortedArrayDictionary<String, String>());
		 
		 // create GUI
	     FrmMainWindowDictionary frmDictionary = new FrmMainWindowDictionary();
	     frmDictionary.setVisible(true);
	}

}
