package entitees;

import java.awt.Rectangle;

import java.io.File;

import javax.imageio.ImageIO;

/**
 * Classe de la raquette de jeu que deplace le joueur et sur laquelle il doit faire rebondir la balle
 * 
 * @author Robiiich
 * @version 0.2
 */

public class Raquette extends EntiteMouv {
	
	private int centrage;
	private TypeRaquette type;
	
	/**
	 * Constructeur de la raquette par defaut, suivant EntiteMouv avec une vitesse nulle
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param ecran
	 *            cf Entite
	 */
	
	public Raquette(int ax, int ay, Rectangle ecran) {
		super(ax, ay, 0, 0, "raquette.png", ecran);
		
		centrage = 1;
		type = TypeRaquette.NORMALE;
	}
	
	/**
	 * Constructeur parametre de la raquette pour instancier des raquettes particulieres selon sa position (centrage)
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param ecran
	 *            cf Entite
	 * @param centrage
	 *            vaut 1 si raquette est en bas au milieu (un joueur), ou (2 joueurs) 2 si la raquette est en bas a gauche, 3 si elle est en bas a
	 *            droite et 4 si elle en haut (opposition)
	 */
	
	public Raquette(int ax, int ay, Rectangle ecran, int centrage) {
		super(ax, ay, 0, 0, "raquette.png", ecran);
		
		this.centrage = centrage; // 1=milieu bas 2=gauche 3=droite 4=milieu haut
		
		String nomimage = "raquette.png";
		
		if (centrage == 2 || centrage == 3) nomimage = "raquettepetite.png";
		else if (centrage == 4) nomimage = "raquetteinversée.png";
		
		try {
			
			image = ImageIO.read(new File(nomimage));
			
		} catch (Exception err) {
			
			System.out.println(nomimage + " introuvable !");
			System.out.println("Mettre les images dans le repertoire :" + getClass().getClassLoader().getResource(nomimage));
			System.exit(0);
		}
		
		h = image.getHeight(null);
		l = image.getWidth(null);
		lim = new Rectangle((int) ax, (int) ay, l, h);
	}

	/*
	 * (non-Javadoc)
	 * @see entitees.EntiteMouv#move()
	 */
	
	public void move() {
		
		switch (centrage) {
		
			case 1 :
			case 4 :
				
				x = x + (int) vx;
				if (x < limframe.x) x = limframe.x;
				else if (x + l > limframe.x + limframe.width)
				    x = limframe.x + limframe.width - l;
				
				break;
			
			case 2 :
				
				x = x + (int) vx;
				if (x < limframe.x) x = limframe.x;
				else if (x + l > limframe.x + limframe.width / 2)
				    x = limframe.x + limframe.width / 2 - l;
				
				break;
			
			case 3 :
				
				x = x + (int) vx;
				if (x < limframe.x + limframe.width / 2) x = limframe.x + limframe.width / 2;
				else if (x + l > limframe.x + limframe.width)
				    x = limframe.x + limframe.width - l;
				
				break;
			
			default :
				break;
		}
		lim.setLocation((int) x, (int) y);
	}
	
	/**
	 * @return le centrage de la raquette
	 */
	
	public int getCentrage() {
		return centrage;
	}
	
	/**
	 * @return le type de raquette
	 */
	
	public TypeRaquette getType() {
		return type;
	}
	
	/**
	 * set du type de la raquette
	 * 
	 * @param type
	 *            son nouveau type
	 */
	
	public void setType(TypeRaquette type) {
		
		this.type = type;
	}
}
