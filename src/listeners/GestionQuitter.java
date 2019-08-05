package listeners;
import java.awt.event.ActionEvent;

import main.Fenetre;

public class GestionQuitter extends GestionBoutton {
	
	public GestionQuitter(Fenetre parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
}
