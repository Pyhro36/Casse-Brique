package framesAux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framesPanels.NiveauPanel;

/**
 * Cette classe ouvre une fenetre qui permet de choisir un des niveaux sauvegarder pour y jouer, le modifier ou le supprimer
 * 
 * @author Peter Ludovic
 * @version 0.2
 */

public class ParcoursDeNiveaux extends JFrame {
	
	private static final long serialVersionUID = 12L;
	private JPanel container = new JPanel();
	private JComboBox<String> combo = new JComboBox<String>();
	private JLabel label = new JLabel("choisir niveau");
	private String afficher = "";
	private String[] tab;
	private JButton charger = new JButton("charger");
	private JButton supprimer = new JButton("supprimer");
	private JButton retour = new JButton("retour");
	private NiveauPanel editeur;
	
	/**
	 * Ce constructeur recupere les noms des differents niveaux existant et initialise les variables liees à la fenètre
	 * 
	 * @param NiveauPanel
	 *            editeur un panel de jeu ou d'éditeur dans lequel afficher le niveau que l'on recupere
	 */
	
	public ParcoursDeNiveaux(NiveauPanel editeur) throws FileNotFoundException, IOException {
		
		this.editeur = editeur;
		File fichier2 = new File("listeNiveau");
		FileReader fichier1;
		fichier1 = new FileReader("listeNiveau");
		int n = (int) fichier2.length();
		char[] a = new char[n];
		
		fichier1.read(a);
		int nbSousChaine = 0;
		fichier1.close();
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] == ';')
			    nbSousChaine++;
		}
		
		String[] test = new String[nbSousChaine];
		
		int charN = 0;
		
		for (int i = 0; i < test.length; i++) {
			
			test[i] = "";
			
			while (charN < a.length && a[charN] != ';') {
				
				test[i] = test[i] + a[charN];
				charN++;
			}
			
			charN++;
		}
		
		this.tab = test;
		this.setTitle("Selectionner niveau");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		combo.setPreferredSize(new Dimension(100, 20));
		combo.addItem("");
		
		for (int i = 0; i < tab.length; i++) {
			
			combo.addItem(tab[i]);
		}
		
		// Ajout du listener
		combo.addActionListener(new ItemAction());
		combo.setPreferredSize(new Dimension(100, 20));
		combo.setForeground(Color.blue);
		retour.addActionListener(new GestionBouton());
		supprimer.addActionListener(new GestionBouton());
		charger.addActionListener(new GestionBouton());
		
		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);
		container.add(top, BorderLayout.NORTH);
		JPanel bottom = new JPanel();
		bottom.add(charger);
		bottom.add(retour);
		bottom.add(supprimer);
		container.add(bottom, BorderLayout.SOUTH);
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	
	public void paint(Graphics g) {
		
		container.removeAll();
		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);
		ImageIcon icon = new ImageIcon(new ImageIcon(afficher + ".sc").getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT));
		JLabel img = new JLabel(icon);
		JPanel middle = new JPanel();
		middle.add(img);
		container.add(top, BorderLayout.NORTH);
		container.add(middle, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bottom.add(charger);
		bottom.add(retour);
		bottom.add(supprimer);
		container.add(bottom, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true);
		
	}
	
	/**
	 * Cette classe est l'ecouteur associe à une fenetre de parcours de niveau
	 * 
	 * @version 0.2
	 */
	
	private class ItemAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			afficher = (String) combo.getSelectedItem();
			repaint();
		}
	}
	
	/**
	 * classe d'ecoute et de modification des changements de niveau selectionne
	 * 
	 * @version 0.2
	 */
	
	private class GestionBouton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == charger) {
				
				editeur.charger(afficher);
				dispose();
			}
			
			if (e.getSource() == retour) {
				
				dispose();
			}
			
			if (e.getSource() == supprimer) {
				
				File suppr = new File(afficher);
				File supprSC = new File(afficher + ".sc");
				suppr.delete();
				supprSC.delete();
				
				for (int i = 0; i < tab.length; i++) {
					
					if (tab[i].equals(afficher))
					    
					    tab[i] = null;
				}
				
				String chaineNiveau = "";
				
				for (int i = 0; i < tab.length; i++) {
					
					if (tab[i] != null)
					    
					    chaineNiveau += tab[i] + ";";
				}
				
				FileWriter listeNiveau;
				
				try {
					
					listeNiveau = new FileWriter("listeNiveau", false);
					listeNiveau.flush();
					listeNiveau.write(chaineNiveau);
					listeNiveau.close();
					
				} catch (IOException f) {
					
				}
				
				File fichier2 = new File("listeNiveau");
				FileReader fichier1;
				int n = (int) fichier2.length();
				char[] a = new char[n];
				
				try {
					
					fichier1 = new FileReader("listeNiveau");
					fichier1.read(a);
					
				} catch (IOException f) {
				}
				
				int nbSousChaine = 0;
				
				for (int i = 0; i < a.length; i++) {
					
					if (a[i] == ';')
					    
					    nbSousChaine++;
				}
				
				String[] test = new String[nbSousChaine];
				int charN = 0;
				
				for (int i = 0; i < test.length; i++) {
					
					test[i] = "";
					
					while (charN < a.length && a[charN] != ';') {
						
						test[i] = test[i] + a[charN];
						charN++;
					}
					
					charN++;
				}
				
				tab = test;
				combo.removeItem(afficher);
			}
		}
	}
}
