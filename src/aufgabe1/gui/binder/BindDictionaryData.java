package aufgabe1.gui.binder;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.gui.panels.BpaWordEntry;
import aufgabe1.gui.tables.TblDictionaryList;

public class BindDictionaryData {
	private TblDictionaryList table;

	public BindDictionaryData(TblDictionaryList table) {
		super();
		this.table = table;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void refreshList() {
		
		Dictionary<String, String> dict = (Dictionary<String, String>)
					AbstractMain.getBean("Dictionary");		
		
		table.getTableModel().setDataToModel(dict.getEntries());
	}
	
	public void readBean(BpaWordEntry detail, Dictionary.Element<String, String> bean) {
		detail.getTfiEnglishWord().setText(bean.value);
		detail.getTfiGermanWord().setText(bean.key);
	}
	
	
	public Dictionary.Element<String, String> writeBean(BpaWordEntry detail, Dictionary.Element<String, String> bean) {
		bean.value = detail.getTfiEnglishWord().getText();
		bean.key = detail.getTfiGermanWord().getText();
		return bean;
	}
	
	
}
