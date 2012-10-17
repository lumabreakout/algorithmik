package aufgabe1.gui.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aufgabe1.gui.FrmMainWindowDictionary;
import aufgabe1.gui.panels.BpaWordEntry;

public class TblDictionaryList extends JTable {

	private class NewAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			TelAddressBean bean = new TelAddressBean();
//			bean = getBinder().writeBean(detail, bean);
//			if (!getHandler().insert(bean)) {
//				JOptionPane.showMessageDialog(frmMainWindow,
//						"Fehler beim Speichern. Die Telefonnummer existiert bereits");
//			} else {
//				setChanged(true);
//				refreshList();
//			}
		}		
	}
	
	private class DeleteAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int res = JOptionPane.showConfirmDialog(
					frmMainWindow,
					"Wollen Sie wirklich löschen?",
					"Speichern",
					JOptionPane.YES_NO_OPTION
					);
			if (res == JOptionPane.YES_OPTION) {
//				TelAddressBean bean = new TelAddressBean();
//				bean = getBinder().writeBeanEntry(entry, bean);
//				if (!getHandler().remove(bean)) {
//					JOptionPane.showMessageDialog(frmMainWindow,
//					"Fehler beim löschen. Schlüssel existiert nicht");
//				} else {
//					setChanged(true);
//					refreshList();
//				}
			}
		}		
	}
	
	private class SearchExactAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			TelAddressBean searchBean = new TelAddressBean();
//			searchBean = getBinder().
//				writeBeanEntry(entry, searchBean);			
//			getBinder().searchExcact(searchBean);					
		}
	}
	
	private class SearchPrafixAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			TelAddressBean searchBean = new TelAddressBean();
//			searchBean = getBinder().
//				writeBeanEntry(entry, searchBean);
//			getBinder().searchPraefix(searchBean);
		}
	}
	
//	private static TelefonBuchTableModel model;
	
	private FrmMainWindowDictionary frmMainWindow;
	private BpaWordEntry entry;
		
//	private BindTelData binder;
	
	private boolean changed;
		
	protected NewAction newAction;
	protected DeleteAction deleteAction;
	protected SearchExactAction searchExactAction;
	protected SearchPrafixAction searchPraefixAction;
	
	public TblDictionaryList(FrmMainWindowDictionary frmMainWindow) {
//		super(getTableModel());
			
		this.frmMainWindow = frmMainWindow;
		this.changed = false;
		
	}
		
	
//	public void refreshList() {
//		getBinder().refreshList();
//	}
	
//	public void showNoEntryFoundMessage() {
//		JOptionPane.showMessageDialog(frmMainWindow,
//				"Keinen Eintrag mit den Suchkriterien gefunden");
//	}
	
	
	
//	public static TelefonBuchTableModel getTableModel() {
//		if (model == null) {
//			model = new TelefonBuchTableModel();
//		}
//		return model;		
//	}
	
	
	public ActionListener getNewAction() {
		if (newAction == null) {
			newAction = new NewAction();			
		}
		return newAction;
	}
	
	public ActionListener getDeleteAction() {
		if (deleteAction == null) {
			deleteAction = new DeleteAction();			
		}
		return deleteAction;
	}
	
	public ActionListener getSearchExactAction() {
		if (searchExactAction == null) {
			searchExactAction = new SearchExactAction();			
		}
		return searchExactAction;
	}
	
	public ActionListener getSearchPraefixAction() {
		if (searchPraefixAction == null) {
			searchPraefixAction = new SearchPrafixAction();			
		}
		return searchPraefixAction;
	}
	
//	public IHandleTelefonBuch getHandler() {
//		return (IHandleTelefonBuch) AbstractMain.getBean("IHandleTelefonBuch");
//	}
//	
//	public BindTelData getBinder() {
//		if (binder == null) {
//			binder = new BindTelData(this);			
//		}
//		return binder;
//	}
//

	public void setChanged(boolean changed) {
		this.changed = changed;
	}


	public boolean isChanged() {
		return changed;
	}
	
	public void setBpaEntry(BpaWordEntry entry) {
		this.entry = entry;
	}
	
	
}
