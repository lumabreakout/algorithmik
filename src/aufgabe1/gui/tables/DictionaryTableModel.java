package aufgabe1.gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import aufgabe1.dictionary.Dictionary;

public class DictionaryTableModel extends AbstractTableModel {

	public static final int GERMAN_WORD = 0;
	public static final int ENGLISH_WORD = 1;
	
	private List<Dictionary.Element<String, String>> data;
	private String[] columnNames;
	
	public DictionaryTableModel() {
		super();
		
		// testing
//		List<Dictionary.Element<String, String>> list = new LinkedList<Dictionary.Element<String, String>>();
//		setDataToModel(list);
	}	
	
	public void setDataToModel(List<Dictionary.Element<String, String>> data) {
		this.data = data;	
		fireTableDataChanged();
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
		Dictionary.Element<String, String> dataBean = data.get(rowIndex);
		
		switch (columnIndex) {
		case GERMAN_WORD:
			return dataBean.key;			
		case ENGLISH_WORD:
			return dataBean.value;	
		default:
			return "";
		}
	}
	
	public Dictionary.Element<String, String> getValueAtRowIndex(int rowIndex) {
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
