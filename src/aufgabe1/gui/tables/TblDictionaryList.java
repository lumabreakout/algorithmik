package aufgabe1.gui.tables;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aufgabe1.collection.AbstractMain;
import aufgabe1.collection.IHandleDictionary;
import aufgabe1.dictionary.ChoiseImpl;
import aufgabe1.dictionary.DictionaryWordBean;
import aufgabe1.gui.FrmMainWindowDictionary;
import aufgabe1.gui.binder.BindDictionaryData;
import aufgabe1.gui.panels.BpaWordEntry;

public class TblDictionaryList extends JTable {

	private class NewAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DictionaryWordBean bean = new DictionaryWordBean();
			bean = getBinder().writeBean(getDetailEntry(), bean);
			if (bean.getEnglish().trim().equals("") || bean.getGerman().trim().equals("")) {
				JOptionPane.showMessageDialog(frmMainWindow,
						"Fehler beim Speichern. Nicht alle Argumente angegeben");
				return;
			}
			
			
			if (!getHandler().insert(frmMainWindow.getImpl(), bean)) {
				JOptionPane.showMessageDialog(frmMainWindow,
						"Fehler beim Speichern. Die Telefonnummer existiert bereits");
			} else {
				setChanged(true);
				refreshList();
			}
		}		
	}
	
	private class DeleteAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int res = JOptionPane.showConfirmDialog(
					frmMainWindow,
					"Wollen Sie wirklich l�schen?",
					"Speichern",
					JOptionPane.YES_NO_OPTION
					);
			if (res == JOptionPane.YES_OPTION) {
				DictionaryWordBean bean = new DictionaryWordBean();
				bean = getBinder().writeBean(getDetailEntry(), bean);
				if (!getHandler().remove(frmMainWindow.getImpl(),  bean)) {
					JOptionPane.showMessageDialog(frmMainWindow,
					"Fehler beim löschen. Schlüssel existiert nicht");
				} else {
					setChanged(true);
					refreshList();
				}
			}
		}		
	}
	
	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DictionaryWordBean searchBean = new DictionaryWordBean();
			searchBean = getBinder().
				writeBean(getDetailEntry(), searchBean);			
			getHandler().search(frmMainWindow.getImpl(), searchBean);					
		}
	}
	
	
	private static DictionaryTableModel model;
	
	private FrmMainWindowDictionary frmMainWindow;
	private BpaWordEntry entry;
	private MnuDictionaryTbl tblMenu;
		
	private BindDictionaryData binder;
	
	private boolean changed;
		
	protected NewAction newAction;
	protected DeleteAction deleteAction;
	protected SearchAction searchAction;
	
	public TblDictionaryList(FrmMainWindowDictionary frmMainWindow) {
		super(getTableModel());
			
		this.frmMainWindow = frmMainWindow;
		this.changed = false;
		
		this.frmMainWindow.getJMenuBar().add(getTblMenu());
		frmMainWindow.add(getDetailEntry(), BorderLayout.NORTH);
		frmMainWindow.pack();
		frmMainWindow.repaint();
		
		initialize();
	}
		
	public void initialize() {
		this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {		    
				if (e.getSource() instanceof DefaultListSelectionModel) {
					DefaultListSelectionModel model = (DefaultListSelectionModel) e.getSource();
				
					DictionaryWordBean bean = getTableModel().getValueAtRowIndex(model.getAnchorSelectionIndex());
					getBinder().readBean(getDetailEntry(), bean);		 
				}
			}
		});
	}
	
	
	public void refreshList() {
		getBinder().refreshList();
	}
	
//	public void showNoEntryFoundMessage() {
//		JOptionPane.showMessageDialog(frmMainWindow,
//				"Keinen Eintrag mit den Suchkriterien gefunden");
//	}
	
	public BpaWordEntry getDetailEntry() {
		if (entry == null) {
			entry = new BpaWordEntry(this, frmMainWindow);			
		}
		return entry;
	}
	
	public MnuDictionaryTbl getTblMenu() {
		if (tblMenu == null) {
			tblMenu = new MnuDictionaryTbl(this);
		}
		return tblMenu;
	}
	
	public static DictionaryTableModel getTableModel() {
		if (model == null) {
			model = new DictionaryTableModel();
		}
		return model;		
	}
	
	public ActionListener getSearchAction() {
		if (searchAction == null) {
			searchAction = new SearchAction();			
		}
		return searchAction;
	}
	
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
	
	
	public IHandleDictionary getHandler() {
		return null;
//		return (IHandleDictionary) AbstractMain.getBean("IHandleDictionary");
	}
	
	public BindDictionaryData getBinder() {
		if (binder == null) {
			binder = new BindDictionaryData(this);			
		}
		return binder;
	}


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
