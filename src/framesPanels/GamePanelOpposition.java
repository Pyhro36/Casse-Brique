package framesPanels;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Timer;

import listeners.JeuKeyAdapter;
import main.Fenetre;
import entitees.Balle;
import entitees.Brique;
import entitees.BriqueBonus;
import entitees.BriqueNVies;
import entitees.Fond;
import entitees.Raquette;
import framesAux.Convertisseur;

/**
 * Classe de generaion du panel de jeu avec gestion d'affichage frame par frame et utilisation du KeyAdapter
 * 
 * @author PL
 */
public class GamePanelOpposition extends NiveauPanel {
	
	private static final long serialVersionUID = 9L;
	private long temps;
	private Timer timer;
	private Graphics buffer;
	private int nombreViesJoueur1;
	private int nombreViesJoueur2;
	private Fond fond;
	private LinkedList<Brique> listeBriques;
	private LinkedList<Balle> listeBalles;
	private Raquette raquette;
	private Raquette raquette1;
	private BufferedImage bufferImage;
	private Rectangle ecran;
	private JeuKeyAdapter jeuKeyAdapter;
	
	/**
	 * Contructeur du panel de jeu ou se deroule le niveau avec la balle, les briques et tout et tout ...
	 * 
	 * @param frame
	 *            la fenêtre principale
	 */
	
	public GamePanelOpposition(Fenetre frame) throws FileNotFoundException {
		super(frame);
		
		temps = 0;
		nombreViesJoueur1 = 3;
		nombreViesJoueur2 = 3;
		
		// definition des espaces de jeu et graphiques
		ecran = new Rectangle(0, 0, parent.getSize().width - parent.getInsets().right - parent.getInsets().left - 110, parent.getSize().height -
		        parent.getInsets().bottom - parent.getInsets().top);
		bufferImage = new BufferedImage(parent.getSize().width, parent.getSize().height, BufferedImage.TYPE_INT_RGB);
		buffer = bufferImage.getGraphics();
		
		try {
			fond = new Fond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// instantiation des briques
		listeBriques = new LinkedList<Brique>();
		charge();
		
		// instantiaiton de la raquette
		raquette = new Raquette((int) ecran.getCenterX(), ecran.getSize().height - 35, ecran, 1);
		raquette1 = new Raquette((int) ecran.getCenterX(), 15, ecran, 4);
		
		// instantiation des balles
		listeBalles = new LinkedList<Balle>();
		listeBalles.add(new Balle(raquette.x + (raquette.l / 2), raquette.y - 20, ecran, true));
		listeBalles.add(new Balle(raquette1.x + (raquette1.l / 2), raquette1.y + raquette1.h + 4, ecran, true));
		
		// intantiation du KeyListener et du Timer
		jeuKeyAdapter = new JeuKeyAdapter();
		parent.addKeyListener(jeuKeyAdapter);
		timer = new Timer(15, new TimerAction());
		timer.setDelay(15);
		timer.start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see NiveauPanel#charger(java.lang.String) methode de chargement de la liste de briques à partir de du fichier de niveau
	 */
	@Override
	public void charger(String nomNiveau) {
		// TODO Auto-generated method stub
		try {
			Brique[][] niveau = Convertisseur.charger(nomNiveau, ecran);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 18; j++) {
					niveau[j][i] = null;
				}
			}
			for (int i = 5; i < 25; i++) {
				for (int j = 0; j < 18; j++) {
					if (niveau[j][i] instanceof BriqueBonus) niveau[j][i] = new BriqueNVies(niveau[j][i].x, niveau[j][i].y, 1);
				}
			}
			listeBriques = Convertisseur.convertirEnListe(niveau);
			for (int i = 0; i < listeBriques.size(); i++) {
			}	
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	/**
	 * methode d'affichage appelee pour mettre a jour l'affichage a chaque frame (repaint())
	 */
	
	public void paint(Graphics g) {
		super.paint(g);
		
		fond.draw(temps, buffer);
		// affichage de la raquette
		raquette.draw(temps, buffer);
		raquette1.draw(temps, buffer);
		
		// affichage des briques
		for (int i = 0; i < listeBriques.size(); i++) {
			
			listeBriques.get(i).draw(temps, buffer);
		}
		
		// affichage des balles
		for (int i = 0; i < listeBalles.size(); i++) {
			
			listeBalles.get(i).draw(temps, buffer);
		}
		
		// affichage des informations de jeu
		buffer.setColor(Color.WHITE);
		buffer.drawString("Vies :" + nombreViesJoueur1, 695, 500);
		buffer.drawString("Lancer la balle : 0", 695, 460);
		buffer.drawString("Vies :" + nombreViesJoueur2, 695, 60);
		buffer.drawString("Lancer la balle :", 695, 20);
		buffer.drawString("Espace", 695, 35);
		g.drawImage(bufferImage, 0, 0, parent);
	}
	
	/**
	 * methode des instructions de mouvement et de collision à chaque frame
	 * 
	 * @param temps
	 *            le temps en nombre de frame qui s'est ecoule depuis le lancement du niveau
	 */
	
	private void run(long temps) {
		
		// bouton echape pour revenir au menu a tout moment
		
		if (jeuKeyAdapter.isEscape()) {
			
			timer.stop();
			parent.removeKeyListener(jeuKeyAdapter);
			parent.switchPanel(new MenuPanel(parent));
		}
		
		// mouvement de la raquette
		
		if (jeuKeyAdapter.isDroite()) {
			
			raquette.vx = 5;
			
		} else if (jeuKeyAdapter.isGauche()) {
			
			raquette.vx = -5;
			
		} else {
			
			raquette.vx = 0;
		}
		
		raquette.move(temps);
		
		if (jeuKeyAdapter.isD()) {
			
			raquette1.vx = 5;
			
		} else if (jeuKeyAdapter.isQ()) {
			
			raquette1.vx = -5;
			
		} else {
			
			raquette1.vx = 0;
		}
		
		raquette1.move(temps);
		
		// mouvement et test des collisions des balles avec la raquette
		
		for (int i = 0; i < listeBalles.size(); i++) {
			
			if (listeBalles.get(i).vx == 0 && listeBalles.get(i).vy == 0 && listeBalles.get(i).y > ecran.getCenterY() && jeuKeyAdapter.is0()) {
				listeBalles.get(i).vx = 3;
				listeBalles.get(i).vy = 3;
			}
			
			if (listeBalles.get(i).vx == 0 && listeBalles.get(i).vy == 0 && listeBalles.get(i).y < ecran.getCenterY() && jeuKeyAdapter.isEspace()) {
				listeBalles.get(i).vx = 3;
				listeBalles.get(i).vy = -3;
			}
			
			listeBalles.get(i).move(temps);
			
			if (listeBalles.get(i).testCollision(raquette))
			    listeBalles.get(i).doCollision(raquette);
			
			if (listeBalles.get(i).testCollision(raquette1))
			    listeBalles.get(i).doCollision(raquette1);
			
			if (listeBalles.get(i).testMort()) {
				listeBalles.remove(i);
				nombreViesJoueur1--;
				if (nombreViesJoueur1 != 0) {
					raquette.x = (int) ecran.getCenterX();
					raquette.y = (int) ecran.getSize().height - 35;
					listeBalles.add(new Balle(raquette.x + (raquette.l / 2), raquette.y - 20, ecran, true));
					
				} else {
					timer.stop();
					parent.removeKeyListener(jeuKeyAdapter);
					parent.switchPanel(new FinJeuPanel(parent, "VictoryP2.png"));
					
				}
			}
			
			if (listeBalles.get(i).testMortHaut()) {
				listeBalles.remove(i);
				nombreViesJoueur2--;
				if (nombreViesJoueur2 != 0) {
					raquette1.x = (int) ecran.getCenterX();
					raquette1.y = 15;
					listeBalles.add(new Balle(raquette1.x + (raquette1.l / 2), raquette1.y + raquette1.h + 4, ecran, true));
					
				} else {
					timer.stop();
					parent.removeKeyListener(jeuKeyAdapter);
					parent.switchPanel(new FinJeuPanel(parent, "VictoryP1.png"));
				}
			}
		}
		
		// collision sur les briques
		
		for (int j = 0; j < listeBalles.size(); j++) {
			
			int n = 0;
			boolean collision = false;
			
			while (n < listeBriques.size() && !collision) {
				
				if (listeBriques.get(n).testCollision(listeBalles.get(j))) {
					
					collision = true;
					listeBriques.get(n).doCollision(listeBalles.get(j));
					
				}
				
				if (!listeBriques.get(n).actif) {
					listeBriques.remove(n);
				}
				n++;
			}
		}
		
		this.repaint();
	}
	
	/**
	 * classe interne d'écoute du timer de la fenetre de jeu
	 * 
	 * @author Pierre-Louis
	 */
	
	private class TimerAction implements ActionListener {
		
		/**
		 * ActionListener appelee toutes les 15 millisecondes (60 Hz), reactualise la fenetre et decompte le temps si le jeu n'est pas en pause
		 * (isEnter() == true)
		 * 
		 * @param e
		 *            evenement de reactualisation de la frame
		 */
		
		public void actionPerformed(ActionEvent e) {
			
			if (!jeuKeyAdapter.isEnter()) {
				run(temps);
				temps++;
			}
		}
	}
}
