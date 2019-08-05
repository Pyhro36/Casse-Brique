package listeners;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import main.Fenetre;
import framesPanels.GamePanel;

public class GestionJouer extends GestionBoutton {
	
	public GestionJouer(Fenetre parent) {
		super(parent);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
	        parent.switchPanel(new GamePanel(parent));
        } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
        }
	}
	
}
