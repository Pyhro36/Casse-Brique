package framesAux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import framesPanels.EditeurPanel;

/**
 * cette classe permet de créer une petite fenetre afin de taper le nom du fichier où l'on veut sauver un niveau
 * 
 * @author Peter Ludovic
 * @version 0.2
 */

public class Sauvegarde extends JFrame {
	
	private static final long serialVersionUID = 3L;
	private JTextField nom = new JTextField(20);
	private JPanel jPanel = new JPanel();
	private JButton retour = new JButton("retour");
	private JButton sauver = new JButton("sauver");
	private EditeurPanel test;
	
	/**
	 * Ce constructeur fabrique la fenetre
	 * 
	 * @param EditeurPanel
	 *            test le panel editeur ou est le niveau à sauvegarder
	 */
	
	public Sauvegarde(EditeurPanel test) {
		
		this.setLocationRelativeTo(null);
		this.test = test;
		
		jPanel.add(nom);
		jPanel.add(retour);
		jPanel.add(sauver);
		
		this.setContentPane(jPanel);
		this.setSize(400, 80);
		this.setVisible(true);
		
		sauver.addActionListener(new GestionBouton());
		retour.addActionListener(new GestionBouton());
	}
	
	/**
	 * cette classe est l'écouteur associé à la fenètre de sauvegarde
	 * 
	 * @version 0.2
	 */
	
	public class GestionBouton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == sauver) {
				
				String niveau = nom.getText();
				setVisible(false);
				
				test.getParent().setAlwaysOnTop(true);
				test.repaint();
				test.sauver(niveau);
				
				dispose();
			}
			
			if (e.getSource() == retour) {
				
				dispose();
			}
		}
	}
}
