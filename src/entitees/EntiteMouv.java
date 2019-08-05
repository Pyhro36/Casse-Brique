package entitees;

import java.awt.Rectangle;

/**
 * Classe abstraite d'ou herite les instances d'entite qui peuvent se deplacer sur l'ecran, avec une vitesse en attributs, herite de Entite
 * 
 * @author Robiiich
 * @version 0.2
 */

public abstract class EntiteMouv extends Entite {
	
	protected double vx;
	protected double vy;
	protected Rectangle limframe;
	
	/**
	 * constructeur d'entite pouvant bouger
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param avx
	 *            la composante de sa vitesse selon x
	 * @param avy
	 *            la composante de sa vitesse selon y
	 * @param nomimage
	 *            cf Entite
	 * @param ecran
	 *            le rectangle dans lequelle doit evoluer l'entite
	 */
	
	protected EntiteMouv(int ax, int ay, double avx, double avy, String nomimage, Rectangle ecran) {
		super(ax, ay, nomimage);
		vx = avx;
		vy = avy;
		limframe = ecran;
	}
	
	/**
	 * methode de deplacement de l'entite a appeler a chaque rafraichissement de l'ecran
	 */
	
	public abstract void move();
}
