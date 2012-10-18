package aufgabe1.gui.tables;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aufgabe1.collection.AbstractMain;
import aufgabe1.dictionary.Dictionary;
import aufgabe1.gui.FrmMainWindowDictionary;
import aufgabe1.gui.binder.BindDictionaryData;
import aufgabe1.gui.panels.BpaWordEntry;

public class TblDictionaryList extends JTable {

	private class NewAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Dictionary.Element<String, String> bean = new Dictionary.Element<String, String>();
			bean = getBinder().writeBean(getDetailEntry(), bean);
			if (bean.value.trim().equals("") || bean.key.trim().equals("")) {
				JOptionPane.showMessageDialog(frmMainWindow,
						"Fehler beim Speichern. Nicht alle Argumente angegeben");
				return;
			}
			
			
			if (getHandler().search(bean.key) != null) {
				JOptionPane.showMessageDialog(frmMainWindow,
						"Fehler beim Speichern. Die Telefonnummer existiert bereits");
			} else {
				getHandler().insert(bean.key, bean.value);
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
				Dictionary.Element<String, String> bean = new Dictionary.Element<String, String>();
				bean = getBinder().writeBean(getDetailEntry(), bean);
								
				if (getHandler().search(bean.key) == null) {
					JOptionPane.showMessageDialog(frmMainWindow,
					"Fehler beim l�schen. Schl�ssel existiert nicht");
				} else {
					getHandler().remove(bean.key);
					setChanged(true);
					refreshList();
				}
			}
		}		
	}
	
	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Dictionary.Element<String, String> searchBean = new Dictionary.Element<String, String>();
			searchBean = getBinder().writeBean(getDetailEntry(), searchBean);						
			
			searchBean.value = getHandler().search(searchBean.key);			
			if (searchBean.value == null) {
				JOptionPane.showMessageDialog(frmMainWindow,
						"Für dieses Wort wurde kein Eintrag gefunden");
				return;
			}
			
			List<Dictionary.Element<String, String>> list = new LinkedList<Dictionary.Element<String, String>>();
			list.add(searchBean);
			getTableModel().setDataToModel(list);		
			getBinder().readBean(getDetailEntry(), searchBean);
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
				
					Dictionary.Element<String, String> bean = getTableModel().getValueAtRowIndex(model.getAnchorSelectionIndex());
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
	
	
	public Dictionary<String, String> getHandler() {
		return (Dictionary) AbstractMain.getBean("Dictionary");
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
