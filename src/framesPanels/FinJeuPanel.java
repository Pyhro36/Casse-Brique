package framesPanels;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Fenetre;
import entitees.Fond;

public class FinJeuPanel extends Panel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6L;
	private Fond fond;
    private long temps;
    
    public FinJeuPanel(Fenetre parent,String nomImage ) {
        super(parent);
        temps=0;
        fond = new Fond(nomImage);
        parent.addKeyListener(new écouteur());
        repaint();
    }
    
    public void paint(Graphics g){
            fond.draw(temps,g);
    }
    
    public class écouteur extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(code==KeyEvent.VK_ESCAPE) parent.switchPanel(new MenuPanel(parent));
        }
    }
}
