package framesPanels;
import java.awt.Component;
import java.awt.LayoutManager;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Fenetre;

/**
 * @author Clement
 */
public abstract class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
	protected Fenetre parent;
	
	/**
	 * 
	 */
	public Panel() {
		
		parent.transferFocus();
	
		// TODO Auto-generated constructor stub
	}
	
	public Panel(Fenetre frame) {
		
		this.parent = frame;
		
	}
		
	public void setParent(Fenetre frame) {
	
		this.parent = frame;
	}
	
	public Fenetre getParent(){
		
		return this.parent;
	}
}
