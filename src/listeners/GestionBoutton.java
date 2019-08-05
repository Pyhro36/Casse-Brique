package listeners;
import java.awt.event.ActionListener;

import main.Fenetre;

public abstract class GestionBoutton implements ActionListener {
	
	protected Fenetre parent;
	
	public GestionBoutton(Fenetre parent) {
		super();
		
		this.parent = parent;
	}
}
