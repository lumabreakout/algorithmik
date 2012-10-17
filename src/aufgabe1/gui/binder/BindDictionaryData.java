package aufgabe1.gui.binder;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.dictionary.DictionaryWordBean;
import aufgabe1.gui.panels.BpaWordEntry;
import aufgabe1.gui.tables.TblDictionaryList;

public class BindDictionaryData {
	private TblDictionaryList table;

	public BindDictionaryData(TblDictionaryList table) {
		super();
		this.table = table;
	}

	
	public void refreshList() {
		Dictionary<String, DictionaryWordBean> dict = (Dictionary<String, DictionaryWordBean>)
					AbstractMain.getBean("Dictionary");
			
		
		
//		table.getTableModel().setDataToModel(list);
	}
	
	public void readBean(BpaWordEntry detail, DictionaryWordBean bean) {
		detail.getTfiEnglishWord().setText(bean.getEnglish());
		detail.getTfiGermanWord().setText(bean.getGerman());
	}
	
	
	public DictionaryWordBean writeBean(BpaWordEntry detail, DictionaryWordBean bean) {
		bean.setEnglish(detail.getTfiEnglishWord().getText());
		bean.setGerman(detail.getTfiGermanWord().getText());
		return bean;
	}
	
	
}
