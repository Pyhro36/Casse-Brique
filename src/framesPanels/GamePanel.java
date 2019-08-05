package framesPanels;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Timer;

import listeners.JeuKeyAdapter;
import main.Fenetre;
import entitees.Balle;
import entitees.Bonus;
import entitees.Brique;
import entitees.BriqueBonus;
import entitees.BriqueIndes;
import entitees.EffetBonusBalle;
import entitees.EffetBonusRaquette;
import entitees.Fond;
import entitees.Raquette;
import framesAux.Convertisseur;

/**
 * Classe de generaion du panel de jeu avec gestion d'affichage frame par frame et utilisation du KeyAdapter
 * 
 * @author PL
 */
public class GamePanel extends NiveauPanel {
	
	private static final long serialVersionUID = 7L;
	private long temps;
	private Timer timer;
	private Graphics buffer;
	private int score;
	private double vitesseBase;
	private int nombreVies;
	private int aDetruire;
	private Fond fond;
	private LinkedList<Brique> listeBriques;
	private LinkedList<Balle> listeBalles;
	private LinkedList<Bonus> listeBonus;
	private Raquette raquette;
	private BufferedImage bufferImage;
	private Rectangle ecran;
	private JeuKeyAdapter jeuKeyAdapter;
	
	/**
	 * Contructeur du panel de jeu ou se deroule le niveau avec la balle, les briques et tout et tout ...
	 * 
	 * @param frame
	 *            la fenêtre principale
	 */
	
	public GamePanel(Fenetre frame) throws FileNotFoundException {
		super(frame);
		
		temps = 0;
		score = 0;
		aDetruire = 0;
		
		// definition des espaces de jeu et graphiques
		ecran = new Rectangle(0, 0, parent.getSize().width - parent.getInsets().right - parent.getInsets().left - 110, parent.getSize().height -
		        parent.getInsets().bottom - parent.getInsets().top);
		bufferImage = new BufferedImage(parent.getSize().width, parent.getSize().height, BufferedImage.TYPE_INT_RGB);
		buffer = bufferImage.getGraphics();
		
		try {
			fond = new Fond();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("resource") 
		FileReader fichier1 = new FileReader("options");
		char[] a = new char[2];
		try {
			fichier1.read(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("bite");
		}
		
		switch (a[0] - 48) {
		
			case 1 :
				
				nombreVies = 5;
				vitesseBase = 3;
				
				break;
			
			case 2 :
				
				nombreVies = 3;
				vitesseBase = 3;
				
				break;
			
			case 3 :
				
				nombreVies = 3;
				vitesseBase = 4;
				
				break;
			
			case 4 :
				
				nombreVies = 2;
				vitesseBase = 5;
				
				break;
			
			default :
				
				nombreVies = 3;
				vitesseBase = 3;
				
				break;
		}
		
		// instantiation des briques
		listeBriques = new LinkedList<Brique>();
		charge();
		
		// instantiation de la raquette
		raquette = new Raquette((int) ecran.getCenterX(), ecran.getSize().height - 35, ecran);
		
		// instantiation des balles
		listeBalles = new LinkedList<Balle>();
		listeBalles.add(new Balle(raquette.x + (raquette.l / 2), raquette.y - 20, ecran));
		
		// instantiation des bonus et effetBonus
		listeBonus = new LinkedList<Bonus>();
		Bonus.initialiseListeEffets();
		
		// instantiation du KeyListener et du Timer
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
			
			listeBriques = Convertisseur.convertirEnListe(Convertisseur.charger(nomNiveau, ecran));
			for (int i = 0; i < listeBriques.size(); i++)
				if (listeBriques.get(i).nom != NomObjet.BINDES)
				    aDetruire++;
			
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
		
		// affichage des briques
		for (int i = 0; i < listeBriques.size(); i++) {
			
			listeBriques.get(i).draw(temps, buffer);
		}
		
		// affichage des balles
		for (int i = 0; i < listeBalles.size(); i++) {
			
			listeBalles.get(i).draw(temps, buffer);
		}
		
		// affichage des bonus
		
		for (int i = 0; i < listeBonus.size(); i++) {
			
			listeBonus.get(i).draw(temps, buffer);
		}
		
		// affichage des effets de bonus
		
		for (int i = 0; i < Bonus.listeEffets.size(); i++) {
			
			if (Bonus.listeEffets.get(i).actif) Bonus.listeEffets.get(i).draw(temps, buffer);
		}
		
		// affichage des informations de jeu
		buffer.setColor(Color.WHITE);
		buffer.drawString("Vies :" + nombreVies, 695, 500);
		buffer.drawString("Score :" + score, 695, 520);
		
		g.drawImage(bufferImage, 0, 0, parent);
	}
	
	/**
	 * methode des instructions de mouvement et de collision à chaque frame
	 * 
	 * @param temps
	 *            le temps en nombre de frame qui s'est ecoule depuis le lancement du niveau
	 */
	
	private void run(long temps) {
		
		// bouton echap pour revenir au menu a tout moment
		
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
		
		// mouvement et collision des bonus qui tombe
		
		for (int i = 0; i < listeBonus.size(); i++) {
			
			listeBonus.get(i).move(temps);
			
			if (listeBonus.get(i).testCollision(raquette)) {
				listeBonus.get(i).doCollision();
			}
			
			if (!listeBonus.get(i).actif) {
				
				listeBonus.remove(i);
			}
		}
		
		// avance des effets de bonus
		
		for (int i = 0; i < Bonus.listeEffets.size(); i++) {
			
			if (Bonus.listeEffets.get(i).actif) {
				
				if (Bonus.listeEffets.get(i) instanceof EffetBonusBalle) {
					
					((EffetBonusBalle) Bonus.listeEffets.get(i)).timerEffet(listeBalles);
					
				} else if (Bonus.listeEffets.get(i) instanceof EffetBonusRaquette) {
					
					((EffetBonusRaquette) Bonus.listeEffets.get(i)).timerEffet(raquette);
					
				}
			}
		}
		
		// mouvement et test des collisions des balles avec la raquette
		
		for (int i = 0; i < listeBalles.size(); i++) {
			
			if (listeBalles.get(i).vx == 0 && listeBalles.get(i).vy == 0 && jeuKeyAdapter.isEspace()) {
				
				listeBalles.get(i).vx = vitesseBase;
				listeBalles.get(i).vy = vitesseBase;
			}
			
			listeBalles.get(i).move(temps);
			
			if (listeBalles.get(i).testCollision(raquette))
			    listeBalles.get(i).doCollision(raquette);
			
			if (listeBalles.get(i).testMort()) listeBalles.remove(i);
		}
		
		if (listeBalles.isEmpty()) {
			
			nombreVies--;
			
			if (nombreVies != 0) {
				
				raquette.x = (int) ecran.getCenterX();
				raquette.y = (int) ecran.getSize().height - 35;
				listeBalles.add(new Balle(raquette.x + (raquette.l / 2), raquette.y - 20, ecran));
				
			} else {
				
				timer.stop();
				parent.removeKeyListener(jeuKeyAdapter);
				parent.switchPanel(new FinJeuPanel(parent, "GameOver.png"));
			}
		}
		
		// collision sur les briques
		
		int n;
		boolean collision;
		
		for (int j = 0; j < listeBalles.size(); j++) {
			
			n = 0;
			collision = false;
			
			while (n < listeBriques.size() && !collision) {
				
				if (listeBriques.get(n).testCollision(listeBalles.get(j))) {
					
					collision = true;
					if (listeBriques.get(n) instanceof BriqueBonus) listeBonus.add((Bonus) listeBriques.get(n).doCollision(listeBalles.get(j)));
					
					else listeBriques.get(n).doCollision(listeBalles.get(j));
				}
				
				if (!listeBriques.get(n).actif) {
					
					score += listeBriques.get(n).getScore(listeBriques.get(n));
					if(!(listeBriques.get(n) instanceof BriqueIndes)) aDetruire--;
					listeBriques.remove(n);
				}
				
				n++;
			}
		}
		
		if (aDetruire == 0 && score > 0) {
			
			timer.stop();
			parent.removeKeyListener(jeuKeyAdapter);
			parent.switchPanel(new FinJeuPanel(parent, "Victory.png"));
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
