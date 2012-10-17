package aufgabe1.gui.navigation;

import javax.swing.JMenuItem;

public abstract class AbstractChoiseableMenuItem extends JMenuItem implements IChoiseableMenu {

	public AbstractChoiseableMenuItem(String title) {
		super(title);
	}

}
