package aufgabe1.gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import aufgabe1.dictionary.DictionaryWordBean;

public class DirectoryTableModel extends AbstractTableModel {

	public static final int GERMAN_WORD = 0;
	public static final int ENGLISH_WORD = 1;
	
	
	public DirectoryTableModel() {
		super();
		
//		List<TelAddressBean> list = new LinkedList<TelAddressBean>();
//		list.add(new TelAddressBean(1, "test", "extension"));
//		setDataToModel(list);
	}	
	
	public void setDataToModel(List<DictionaryWordBean> data) {
//		this.data = data;		
	}
	
	@Override
	public int getRowCount() {
//		if (data == null) {
//			return 0;
//		}
//		return data.size();
		return 0;
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
//		TelAddressBean dataBean = data.get(rowIndex);
//		
//		switch (columnIndex) {
//		case KEY:
//			return dataBean.getKey();			
//		case NAME:
//			return dataBean.getName();	
//		case EXTENSION:
//			return dataBean.getExtension();	
//		case TELNUMBER:
//			return dataBean.getNumber();	
//		default:
//			return "";
//		}
		return "";
	}
	
	public DictionaryWordBean getValueAtRowIndex(int rowIndex) {
//		if (rowIndex >= 0 && rowIndex < data.size()) {
//			return data.get(rowIndex);
//		} else {
//			return data.get(0);
//		}
		return null;
	}

	public String[] getColumnNames() {
//		if (columnNames == null) {
//			columnNames = new String[] {
//					"Key", "Name", "Zusatz", "Telefonnummer" };			
//		}	
//		return columnNames;
		return null;
	}

}
