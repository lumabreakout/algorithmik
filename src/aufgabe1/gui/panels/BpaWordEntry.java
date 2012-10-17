package aufgabe1.gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BpaWordEntry extends JPanel {
	
	private JPanel bpaDefault;
	private JLabel lblGermanWord;
	private JTextField tfiGermanWord;
	private JLabel lblEnglishWord;
	private JTextField tfiEnglishWord;
	
	private JPanel bpaButtons;
	private JButton btnOk;
	private JButton btnClose;
	
	private JDialog dialog;
	
	public BpaWordEntry() {
		super();		
		
		initialize();
	}
	
	private void initialize() {
		this.setPreferredSize(new Dimension(750, 80));
		this.setBorder(BorderFactory.createTitledBorder("Wörterbuch Eintrag"));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
    	this.add(getBpaDefault());
    	this.add(getBpaButtons());
	}
	
	protected JPanel getBpaDefault() {
		if (bpaDefault == null) {
			bpaDefault = new JPanel();		
			bpaDefault.setLayout(new GridLayout(2, 2));
			bpaDefault.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			bpaDefault.add(getLblGermanWord());
			bpaDefault.add(getTfiGermanWord());
			bpaDefault.add(getLblEnglishWord());
			bpaDefault.add(getTfiEnglishWord());
		}
		return bpaDefault;			
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
		}
		return tfiGermanWord;
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
		}
		return tfiEnglishWord;
	}
	
	protected JPanel getBpaButtons() {
		if (bpaButtons == null) {
			bpaButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bpaButtons.setPreferredSize(new Dimension(750, 25));
			bpaButtons.add(getBtnOk());
			bpaButtons.add(getBtnClose());
		}
		return bpaButtons;
	}
	
	
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setText("OK");
			btnOk.setPreferredSize(new Dimension(80, 20));
			// TODO
		}
		return btnOk;
	}
	
	public JButton getBtnClose() {
		if (btnClose == null) {
			btnClose = new JButton();
			btnOk.setText("Close");
			btnOk.setPreferredSize(new Dimension(80, 20));
			btnOk.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);										
				}
			});
		}

		return btnClose;
	}
	
}
