package entitees;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.io.File;

import javax.imageio.ImageIO;

/**
 * Classe mere de toutes les entites du jeu qui doivent etre affichees
 * 
 * @author Robiiich
 * @version 0.2
 */

public abstract class Entite {
	
	protected int x;
	protected int y;
	protected int h;
	protected int l;
	protected Image image;
	protected Rectangle lim;
	protected boolean actif;
	
	/**
	 * Constructeur d'entite a afficher en fonction de sa position par rapport a l'origine en haut a gauche
	 * 
	 * @param ax
	 *            l'abscisse (vers la droite) du point en haut a gauche de l'image
	 * @param ay
	 *            l'ordonnee (vers le bas) du point en haut a gauche de l'image
	 * @param nomimage
	 *            le nom du fichier de l'imFage de l'entite
	 */
	
	protected Entite(int ax, int ay, String nomimage) {
		
		x = ax;
		y = ay;
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
		actif = true;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}
	
	/**
	 * @return the l
	 */
	public int getL() {
		return l;
	}
	
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * @return the lim
	 */
	public Rectangle getLim() {
		return lim;
	}
	
	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}
	
	/**
	 * affiche dans le Graphics g l'image de l'entite aux coordonnees x et y
	 * 
	 * @param g
	 */
	
	public void draw(Graphics g) {
		
		g.drawImage(image, (int) x, (int) y, null);
	}
}
