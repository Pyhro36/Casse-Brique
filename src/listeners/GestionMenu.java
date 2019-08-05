package listeners;
import java.awt.event.ActionEvent;

import main.Fenetre;
import framesPanels.MenuPanel;

/**
 * @author Clement
 *
 */


public class GestionMenu extends GestionBoutton {

	public GestionMenu(Fenetre parent) {
		super(parent);
	}

	
	public void actionPerformed(ActionEvent e){
		parent.switchPanel(new MenuPanel(parent));
	}
}
	