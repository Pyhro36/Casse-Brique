package entitees;

import java.awt.Rectangle;

/**
 * Classe qui permet de creer une balle, herite de EntiteMouv
 * @author Robiiich
 * @version 0.2
 */

public class Balle extends EntiteMouv {
	
	private boolean multijoueur;
	private TypeBalle type;
	
	/**
	 * constructeur d'une balle herite de EntiteMouv. Initialise la balle avec une vitesse nulle
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param ecran
	 *            cf EntiteMouv
	 * @param multijoueur
	 *            bouleen vrai si le jeu est multijoueur (2 joueurs), faux sinon
	 */
	
	public Balle(int ax, int ay, Rectangle ecran, boolean multijoueur) {
		super(ax, ay, 0, 0, "balle.png", ecran);
		
		this.multijoueur = multijoueur;
		this.type = TypeBalle.NORMALE;
	}
	
	/**
	 * constructeur d'une balle herite de EntiteMouv. Initialise la balle avec une vitesse nulle
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param ecran
	 *            cf EntiteMouv
	 */
	
	public Balle(int ax, int ay, Rectangle ecran) {
		super(ax, ay, 0, 0, "balle.png", ecran);
		
		this.multijoueur = false;
		this.type = TypeBalle.NORMALE;
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.EntiteMouv#move()
	 */
	
	public void move() {
		x = x + (int) vx;
		y = y + (int) vy;
		if (x < limframe.x) {
			x = limframe.x;
			vx = -vx;
		} else if (x + l > limframe.x + limframe.width) {
			x = limframe.x + limframe.width - l;
			vx = -vx;
		}
		if (y < limframe.y && !multijoueur) {
			y = limframe.y;
			vy = -vy;
		}
		
		lim.setLocation((int) x, (int) y);
	}
	
	/**
	 * retourne vrai et passe actif a faux si la balle sort de la limite inferieure de l'ecran
	 * 
	 * @return
	 */
	
	public boolean testMort() {
		
		if (y > limframe.y + limframe.height || actif == false) {
			actif = false;
			return true;
		}
		else return false;
	}
	
	/**
	 * @return vrai et passe actif a faux si la balle sort de la limite superieure de l'ecran, faux sinon
	 */
	
	public boolean testMortHaut() {
		
		if (y < 0 || actif == false) {
			actif = false;
			return true;
		}
		else return false;
	}
	
	/**
	 * @param r
	 *            la raquette avec laquelle on teste si la balle est en collision
	 * @return vrai si la balle est en collision avec le haut de la raquette en parametre, ou le abs si la raquette est en haut de l'ecran
	 */
	
	public boolean testCollision(Raquette r) {
		
		if (lim.intersects(r.lim) && ((r.getCentrage() == 4 && y > (r.y + r.h / 2)) || (r.getCentrage() != 4 && y + h < (r.y + r.h / 2)))) return true;
		else return false;
	}
	
	/**
	 * methode de changement de vitesse de la balle suite au rebond qur la raquette
	 * 
	 * @param r
	 *            la raquette sur laquelle elle rebondit
	 */
	
	public void doCollision(Raquette r) {
		
		double vitesse = Math.sqrt((vx * vx) + (vy * vy));
		double xb = x + ((double) l / 2);
		double xr = r.x + ((double) r.l / 2);
		double theta;
		
		switch (r.getType()) {
		
			case PETITE :
				
				theta = 0.7 * Math.PI * ((xb - xr) / (r.l));
				break;
			
			default :
				
				theta = 0.9 * Math.PI * ((xb - xr) / (r.l));
				break;
		}
		
		vx = (vitesse * Math.sin(theta));
		vy = ((r.getCentrage() == 4) ? 1 : -1) * (vitesse * Math.cos(theta));
	}
	
	/**
	 * @return vrai si multijoueur, faux sinon
	 */
	public boolean isMultijoueur() {
		return multijoueur;
	}
	
	/**
	 * @return le type de balle
	 */
	
	public TypeBalle getType() {
		return type;
	}
	
	/**
	 * set du type de balle
	 * 
	 * @param type
	 *            le nouveau type de balle
	 */
	
	public void setType(TypeBalle type) {
		
		this.type = type;
	}
	
}
