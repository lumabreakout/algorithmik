package aufgabe1.collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import aufgabe1.dictionary.ChoiseImpl;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.dictionary.DictionaryWordBean;

public class HandleDictionary implements IHandleDictionary {
	
//	private Dictionary<String, DictionaryWordBean> dic
	
	public HandleDictionary() {
		
	}
	

//	FileInputStream fs;
//	try {
//		fs = new FileInputStream(filepath);
//		Scanner scanner = new Scanner(fs);
//		while (scanner.hasNextLine()) {
//			String line = scanner.nextLine();
//			int firstWordLength = line.indexOf(' ');
//			container.insert(line.substring(0, firstWordLength), line.substring(firstWordLength + 1));
//		}
//		fs.close();
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	@Override
	public boolean remove(ChoiseImpl impl, DictionaryWordBean bean) {
		return false;
	}

	@Override
	public boolean insert(ChoiseImpl impl, DictionaryWordBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(ChoiseImpl impl, DictionaryWordBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DictionaryWordBean> getAllWords(ChoiseImpl impl) {
		// TODO Auto-generated method stub
		return null;
	}

}
