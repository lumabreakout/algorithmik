package aufgabe1.collection;

import java.util.List;

import aufgabe1.dictionary.ChoiseImpl;
import aufgabe1.dictionary.DictionaryWordBean;

public interface IHandleDictionary {

	public boolean remove(ChoiseImpl impl, DictionaryWordBean bean);
	
	public boolean insert(ChoiseImpl impl, DictionaryWordBean bean);
	
	public boolean search(ChoiseImpl impl, DictionaryWordBean bean);
	
	public List<DictionaryWordBean> getAllWords(ChoiseImpl impl);
	
}
