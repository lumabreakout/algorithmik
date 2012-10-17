package aufgabe1.gui.navigation;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import aufgabe1.gui.FrmMainWindowDictionary;

public class MnuDictionary extends JMenuBar {

	private FrmMainWindowDictionary frmMainWindow;
	
	private JMenu mnuFile;
	private JMenuItem mniClose;
	private JMenuItem mniSave;
	private JMenuItem mniLoad;	
	
	private JMenu mnuChoise;
	private AbstractChoiseableMenuItem mniArrayDictionary;
	private AbstractChoiseableMenuItem mniHashDictionary;
	private AbstractChoiseableMenuItem mniTreeDictionary;
	private AbstractChoiseableMenuItem mniMapDictionary;
	
    public MnuDictionary(FrmMainWindowDictionary frmMainWindow) {
        this.frmMainWindow = frmMainWindow;
        this.add(getMnuFile());
        this.add(getMnuCoise());
    }

    private JMenu getMnuFile() {
    	if (mnuFile == null) {
			mnuFile = new JMenu("Datei");
			mnuFile.add(getMniLoad());
			mnuFile.add(getMniSave());
			mnuFile.addSeparator();
			mnuFile.add(getMniClose());
		}
		return mnuFile;
    }
    
    private JMenuItem getMniClose(){
    	if (mniClose == null) {
			mniClose = new JMenuItem("Beenden");
			mniClose.addActionListener(frmMainWindow.getCloseAction());
		}
		return mniClose;
    }
    
    private JMenuItem getMniSave() {
    	if (mniSave == null) {
			mniSave = new JMenuItem("TelefonBuch speichern");
			mniSave.addActionListener(frmMainWindow.getSaveAction());			
		}
		return mniSave;
    }
    
    private JMenuItem getMniLoad() {
    	if (mniLoad == null) {
			mniLoad = new JMenuItem("TelefonBuch laden");
			mniLoad.addActionListener(frmMainWindow.getLoadAction());
		}
		return mniLoad;
    }
    
    
    private JMenu getMnuCoise() {
    	if (mnuChoise == null) {
			mnuChoise = new JMenu("Implementierungen");
			mnuChoise.add(getMniArrayDictionary());
			mnuChoise.add(getMniHashDictionary());
			mnuChoise.add(getMniTreeDictionary());
			mnuChoise.add(getMniMapDictionary());			
		}
		return mnuChoise;
    }
    
    
    private JMenuItem getMniArrayDictionary(){
    	if (mniArrayDictionary == null) {
    		mniArrayDictionary = new AbstractChoiseableMenuItem("ArrayDictionary") {
				@Override
				public ChoiseImpl getHasCoise() {
					return ChoiseImpl.SORTED_ARRAY_IMPL;
				}    			
    		};
    		mniArrayDictionary.addActionListener(frmMainWindow.getChooseImplAction());
		}
		return mniArrayDictionary;
    }
    
    private JMenuItem getMniHashDictionary(){
    	if (mniHashDictionary == null) {
    		mniHashDictionary = new AbstractChoiseableMenuItem("HashDictionary") {
				@Override
				public ChoiseImpl getHasCoise() {
					return ChoiseImpl.HASH_IMPL;
				}    			
    		};
    		mniArrayDictionary.addActionListener(frmMainWindow.getChooseImplAction());
		}
		return mniHashDictionary;
    }
    
    private JMenuItem getMniTreeDictionary(){
    	if (mniTreeDictionary == null) {
    		mniTreeDictionary = new AbstractChoiseableMenuItem("TreeDictionary") {
				@Override
				public ChoiseImpl getHasCoise() {
					return ChoiseImpl.TREE_IMPL;
				}    			
    		};
    		mniArrayDictionary.addActionListener(frmMainWindow.getChooseImplAction());
		}
		return mniTreeDictionary;
    }
    
    private JMenuItem getMniMapDictionary(){
    	if (mniMapDictionary == null) {
    		mniMapDictionary = new AbstractChoiseableMenuItem("MapDictionary") {
				@Override
				public ChoiseImpl getHasCoise() {
					return ChoiseImpl.MAP_IMPL;
				}    			
    		};
    		mniArrayDictionary.addActionListener(frmMainWindow.getChooseImplAction());
		}
		return mniMapDictionary;
    }
    
	
}
