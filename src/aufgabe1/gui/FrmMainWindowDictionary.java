package aufgabe1.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import aufgabe1.gui.navigation.ChoiseImpl;
import aufgabe1.gui.navigation.IChoiseableMenu;
import aufgabe1.gui.navigation.MnuDictionary;

public class FrmMainWindowDictionary extends JFrame {

	private class CloseAction extends WindowAdapter implements ActionListener {
		@Override
		public void windowClosing(WindowEvent e) {
    		doCloseAction();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			doCloseAction();
		}
		
		private void doCloseAction() {
			int n = JOptionPane.showConfirmDialog(
					FrmMainWindowDictionary.this,
					"Wollen Sie die Anwendung wirklich beenden?",
					"Exit",
					JOptionPane.YES_NO_OPTION
					);
			if (n == JOptionPane.YES_OPTION) {
				System.exit(1);
			}				
		}
	}
	
	private class SaveAction implements ActionListener {
		
		JFileChooser fileChooser;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
			}
			
			int res = fileChooser.showSaveDialog(FrmMainWindowDictionary.this);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
//				getTelHanlder().save(file);
			}
		}		
	}
	
	private class LoadAction implements ActionListener {
		
		JFileChooser fileChooser;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
			}
			
			int res = fileChooser.showOpenDialog(FrmMainWindowDictionary.this);
			if (res == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
//	            getTelHanlder().read(file);
	            
//	            getTblTelefonBuch().refreshList();
//	            getTblTelefonBuch().setChanged(false);
			}
		}		
	}
	
	private class ChooseImplAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof IChoiseableMenu) {				
				IChoiseableMenu mnu = (IChoiseableMenu) e.getSource();
				if (mnu.getHasCoise() == ChoiseImpl.HASH_IMPL) {
					//TODO
					
				} else if (mnu.getHasCoise() == ChoiseImpl.MAP_IMPL) {
					// TODO
					
				} else if (mnu.getHasCoise() == ChoiseImpl.SORTED_ARRAY_IMPL) {
					// TODO
					
				} else if (mnu.getHasCoise() == ChoiseImpl.TREE_IMPL) {
					// TODO
				}
				
				// test
			}
		}
		
	}
	
	protected CloseAction closeAction;
	protected SaveAction saveAction;
	protected LoadAction loadAction;
	protected ChooseImplAction chooseAction;
	
	protected MnuDictionary mnuDictionary;
	
	/**
	 * Default Constructor
	 */
	public FrmMainWindowDictionary() {
    	super();
    	
    	initialize();
    }
    
    protected void initialize() {
    	this.setTitle("Telefonbuch");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(getCloseAction());	
        this.setSize(new Dimension(800, 600));
        this.pack();       	
    	
    	this.setJMenuBar(getMnuDictionary());
//
//    	this.add(getBpaHeader(), BorderLayout.NORTH);
//    	this.add(getBpaTable(), BorderLayout.CENTER);
        
    }
    
    public MnuDictionary getMnuDictionary() {
    	if (mnuDictionary == null) {
			mnuDictionary = new MnuDictionary(this);			
		}
		return mnuDictionary;
    }
    
    public CloseAction getCloseAction() {
 	   if (closeAction == null) {
 		   closeAction = new CloseAction();		
 	   }
 	   return closeAction;
    }
    
    public SaveAction getSaveAction() {
 	   if (saveAction == null) {
 		   saveAction = new SaveAction();
 	   }
 	   return saveAction;
    }
    
    public LoadAction getLoadAction() {
 	   if (loadAction == null) {
 		   loadAction = new LoadAction();		
 	   }
 	   return loadAction;
    }
    
    public ChooseImplAction getChooseImplAction() {
  	   if (chooseAction == null) {
  		 chooseAction = new ChooseImplAction();		
  	   }
  	   return chooseAction;
     }
	
}
