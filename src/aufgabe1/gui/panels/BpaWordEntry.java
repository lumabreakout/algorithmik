package aufgabe1.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aufgabe1.gui.FrmMainWindowDictionary;
import aufgabe1.gui.tables.TblDictionaryList;

public class BpaWordEntry extends JPanel {
	
	public final static int READ =  0;
	public final static int NEW = 1;
	public final static int UPDATE = 2;
	
	private JPanel bpaDefault;
	private JPanel de;
	private JLabel lblGermanWord;
	private JTextField tfiGermanWord;
	private JPanel en;
	private JLabel lblEnglishWord;
	private JTextField tfiEnglishWord;
	
	private JPanel bpaButtons;
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnSearch;
	
	private JDialog dialog;	
	private String wrkBean;
	
	private TblDictionaryList table;
	private FrmMainWindowDictionary mainWindow;
	
	public BpaWordEntry(TblDictionaryList table, FrmMainWindowDictionary mainWindow) {
		super();		
		this.table = table;
		this.mainWindow = mainWindow;
		
		initialize();
	}
	
	private void initialize() {
		this.setPreferredSize(new Dimension(750, 150));
		this.setBorder(BorderFactory.createTitledBorder("Woerterbuch Eintrag"));
		this.setLayout(new BorderLayout());
    	this.add(getBpaDefault(), BorderLayout.CENTER);
    	this.add(getBpaButtons(), BorderLayout.SOUTH);
	}
	
	public void changeEditableComponents() {
		
	}
	
	protected JPanel getBpaDefault() {
		if (bpaDefault == null) {
			bpaDefault = new JPanel();		
			bpaDefault.setLayout(new FlowLayout());
			bpaDefault.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			bpaDefault.add(getBpaDe());
			bpaDefault.add(getBpaEn());			
		}
		return bpaDefault;			
	}

	protected JPanel getBpaDe() {
		if (de == null) {
			de = new JPanel(new FlowLayout());
			de.setPreferredSize(new Dimension(750, 25));
			de.add(getLblGermanWord());
			de.add(getTfiGermanWord());
		}
		return de;
	}
	
	private JLabel getLblGermanWord() {
		if (lblGermanWord == null) {
			lblGermanWord = new JLabel("Deutsches Word: ");
		}
		return lblGermanWord;
	}
	
	public JTextField getTfiGermanWord() {
		if (tfiGermanWord == null) {
			tfiGermanWord = new JTextField();		
			tfiGermanWord.setPreferredSize(new Dimension(200, 20));
		}
		return tfiGermanWord;
	}
	
	protected JPanel getBpaEn() {
		if (en == null) {
			en = new JPanel(new FlowLayout());
			en.setPreferredSize(new Dimension(750, 25));
			en.add(getLblEnglishWord());
			en.add(getTfiEnglishWord());
		}
		return en;
	}
	
	private JLabel getLblEnglishWord() {
		if (lblEnglishWord == null) {
			lblEnglishWord = new JLabel("Englisches Word: ");
		}
		return lblEnglishWord;
	}
	
	public JTextField getTfiEnglishWord() {
		if (tfiEnglishWord == null) {
			tfiEnglishWord = new JTextField();		
			tfiEnglishWord.setPreferredSize(new Dimension(200, 20));
		}
		return tfiEnglishWord;
	}
	
	protected JPanel getBpaButtons() {
		if (bpaButtons == null) {
			bpaButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bpaButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			bpaButtons.setPreferredSize(new Dimension(500, 30));
			bpaButtons.add(getBtnNew());
			bpaButtons.add(getBtnDelete());
			bpaButtons.add(getBtnSearch());
		}
		return bpaButtons;
	}
	
	
	public JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setText("Einfuegen");
			btnNew.setPreferredSize(new Dimension(120, 20));
			btnNew.addActionListener(table.getNewAction());
		}
		return btnNew;
	}
	
	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setText("Loeschen");
			btnDelete.setPreferredSize(new Dimension(120, 20));
			btnDelete.addActionListener(table.getDeleteAction());
		}

		return btnDelete;
	}
	
	public JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch =  new JButton();
			btnSearch.setText("Suchen");
			btnSearch.setPreferredSize(new Dimension(120, 20));
			btnSearch.addActionListener(table.getSearchAction());
		}

		return btnSearch;
	}
	
	public String getWrkBean() {
		return wrkBean;
	}

	public void setWrkBean(String wrkBean) {
		this.wrkBean = wrkBean;
	}
	
}
