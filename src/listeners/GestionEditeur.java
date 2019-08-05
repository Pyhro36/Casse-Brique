package listeners;
import java.awt.event.ActionEvent;

import main.Fenetre;
import framesPanels.EditeurPanel;

/**
 * 
 */

/**
 * @author Pierre-Louis
 *
 */
public class GestionEditeur extends GestionBoutton {
	
	/**
	 * @param parent
	 */
	public GestionEditeur(Fenetre parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		parent.switchPanel(new EditeurPanel(parent));
		// TODO Auto-generated method stub
		
	}
	
}
