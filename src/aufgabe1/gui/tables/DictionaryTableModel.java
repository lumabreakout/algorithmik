package aufgabe1.gui.tables;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aufgabe1.dictionary.DictionaryWordBean;

public class DictionaryTableModel extends AbstractTableModel {

	public static final int GERMAN_WORD = 0;
	public static final int ENGLISH_WORD = 1;
	
	private List<DictionaryWordBean> data;
	private String[] columnNames;
	
	public DictionaryTableModel() {
		super();
		
		// testing
		List<DictionaryWordBean> list = new LinkedList<DictionaryWordBean>();
		list.add(new DictionaryWordBean("deutsch", "englisch"));
		setDataToModel(list);
	}	
	
	public void setDataToModel(List<DictionaryWordBean> data) {
		this.data = data;		
	}
	
	@Override
	public int getRowCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}
	
	public String getColumnName(int col) {
        return getColumnNames()[col].toString();
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DictionaryWordBean dataBean = data.get(rowIndex);
		
		switch (columnIndex) {
		case GERMAN_WORD:
			return dataBean.getGerman();			
		case ENGLISH_WORD:
			return dataBean.getEnglish();	
		default:
			return "";
		}
	}
	
	public DictionaryWordBean getValueAtRowIndex(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < data.size()) {
			return data.get(rowIndex);
		} 
		return data.get(0);
	}

	public String[] getColumnNames() {
		if (columnNames == null) {
			columnNames = new String[] {"Deutsch", "Englisch"};			
		}	
		return columnNames;
	}

}
