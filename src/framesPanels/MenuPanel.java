package framesPanels;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.Fenetre;

public class MenuPanel extends Panel {
	
    private static final long serialVersionUID = 3L;
	public JButton jouer;
	public JButton options;
	public JButton quitter;
	public JButton editeur;
        public JButton coop;
        public JButton op;
	public JLabel title;
	
	public MenuPanel(Fenetre frame) {
		super(frame);
		this.setLayout(null);
		
		title = new JLabel();
		title.setText("Skimming Stones");
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setBounds(245, 50, 400, 100);
		title.setForeground(Color.red);
		this.add(title);
		
		jouer = new JButton();
		jouer.setText("Jouer");
		jouer.setBounds(300, 200, 200, 40);
		jouer.setBackground(Color.green);
		jouer.setFont(new Font("Arial", Font.BOLD, 25));
		this.add(jouer);
		jouer.addActionListener(new GestionBouton());
                
                op = new JButton();
                op.setText("Opposition");
                op.setBounds(300, 260, 200, 40);
                op.setBackground(Color.green);
                op.setFont(new Font("Arial", Font.BOLD, 25));
                this.add(op);
                op.addActionListener(new GestionBouton());
                
                coop = new JButton();
                coop.setText("Coopération");
                coop.setBounds(300, 320, 200, 40);
                coop.setBackground(Color.green);
                coop.setFont(new Font("Arial", Font.BOLD, 25));
                this.add(coop);
                coop.addActionListener(new GestionBouton());
		
		editeur = new JButton();
		editeur.setText("Editeur");
		editeur.setBounds(300, 380, 200, 40);
		editeur.setFont(new Font("Arial", Font.BOLD, 25));
		editeur.addActionListener(new GestionBouton());
		this.add(editeur);
		
		options = new JButton();
		options.setText("Options");
		options.setBounds(300, 440, 200, 40);
		options.setFont(new Font("Arial", Font.BOLD, 25));
		options.addActionListener(new GestionBouton());
		this.add(options);
		
		quitter = new JButton();
		quitter.setText("Quitter");
		quitter.setBounds(300, 500, 200, 40);
		quitter.setBackground(Color.red);
		quitter.setFont(new Font("Arial", Font.BOLD, 25));
		this.add(quitter);
		quitter.addActionListener(new GestionBouton());
	}
        
        public class GestionBouton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jouer){
                try {
                    parent.switchPanel(new GamePanel(parent));
                } catch (FileNotFoundException f) {
                }
            }
            
            if(e.getSource()==coop){
                try {
                    parent.switchPanel(new GamePanelCoop(parent));
                } catch (FileNotFoundException f) {
                }
            }
            
            if(e.getSource()==op){
                try {
                    parent.switchPanel(new GamePanelOpposition(parent));
                } catch (FileNotFoundException f) {
                }
            }
            
            if(e.getSource()==editeur){
                parent.switchPanel(new EditeurPanel(parent));
            }
            
            if(e.getSource()==options){
                parent.switchPanel(new OptionPanel(parent));
            }
            
            if(e.getSource()==quitter){
                System.exit(0);
            }
        }
    }
}
