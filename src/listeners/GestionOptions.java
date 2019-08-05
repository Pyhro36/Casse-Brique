package listeners;
import java.awt.event.ActionEvent;

import main.Fenetre;
import framesPanels.OptionPanel;


/**
 * 
 */

/**
 * @author Pierre-Louis
 *
 */
public class GestionOptions extends GestionBoutton {
	
	/**
	 * @param parent
	 */
	public GestionOptions(Fenetre parent) {
		super(parent);
	}

	public void actionPerformed(ActionEvent e) {
		parent.switchPanel(new OptionPanel(parent));
	}
	
}
