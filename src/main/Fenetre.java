package main;
import java.awt.Container;

import javax.swing.*;

import framesPanels.MenuPanel;
import framesPanels.Panel;

/**
 * Classe de generation de la fenetre principale du jeu de Casse Brique
 * 
 * @author PL et Clement
 * @version 0.1
 */

public class Fenetre extends JFrame {
	
	private static final long serialVersionUID = 12L;
	
	private Panel panel;

	/**
	 * Constructeur de la fenetre du Casse-Briques qui se lance automatiquement sur le menu principal
	 * @param nom
	 */
	
	public Fenetre(String nom) {
		super(nom);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(new MenuPanel(this));
		
		this.setVisible(true);
		
	}
	/**
	 * methode pour changer le panel que contient la fenetre principale, 
	 * remplace le panel et detruit l'autre par le garbage collector
	 * @param panel le nouveau panel qui remplace le precedent, attention pour utiliser la methode, 
	 * faire directement switchPanel(new RandomPanel) pour eviter la redondance des déclaration 
	 * (RandomPanel etant le type de Panel que vous voulez generer)
	 */
	
	public void switchPanel(Panel panel) {
		
		this.setVisible(false);
		this.setContentPane(panel);
		this.setVisible(true);
		;
	}
	
}
