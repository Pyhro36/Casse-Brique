package framesPanels;
import java.io.FileNotFoundException;
import java.io.IOException;

import main.Fenetre;
import framesAux.ParcoursDeNiveaux;

/**
 * 
 */

/**
 * @author Pierre-Louis
 * @version 0.1
 */
public abstract class NiveauPanel extends Panel {
	
	/**
     * 
     */
    private static final long serialVersionUID = 2L;

	/**
	 * 
	 */
	public NiveauPanel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param frame
	 */
	public NiveauPanel(Fenetre frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void charger(String nomNiveau);
	
	public void charge() {
		try {
			ParcoursDeNiveaux nom = new ParcoursDeNiveaux(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
}
