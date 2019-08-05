package entitees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe de la brique indestructible, herite de la brique
 * 
 * @author Robiiich
 * @version 0.2
 */

public class BriqueIndes extends Brique {
	
	private final static int SCORE = 200;
	
	/**
	 * Constructeur de la brique indestructible
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 */
	
	public BriqueIndes(int ax, int ay) {
		super(ax, ay, "briqueindesEspace.png", TypeBrique.INDESTRUCTIBLE, SCORE);
		
		FileReader fichier1;
		String nomimage;
		
		try {
			
			fichier1 = new FileReader("options");
			
		} catch (FileNotFoundException e) {
			
			fichier1 = null;
		}
		
		char[] a = new char[2];
		
		try {
			
			fichier1.read(a);
			
		} catch (IOException e) {
			
		}
		
		switch (a[1] - 48) {
		
			case (1) :
				
				nomimage = "briqueindesEspace.png";
				break;
			
			case (2) :
				
				nomimage = "briqueindesJungle.png";
				break;
			
			case (3) :
				
				nomimage = "briqueindesFractale.png";
				break;
			
			case (4) :
				
				nomimage = "briqueindesAquatique.png";
				break;
			
			default :
				
				nomimage = "briqueindesEspace.png";
		}
		
		try {
			
			image = ImageIO.read(new File(nomimage));
			
		} catch (Exception err) {
			
			System.out.println(nomimage + " introuvable !");
			System.out.println("Mettre les images dans le repertoire :" + getClass().getClassLoader().getResource(nomimage));
			System.exit(0);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.Brique#doCollision(entitees.Balle)
	 */
	
	public Bonus doCollision(Balle balle) {
		
		if (balle.getType() == TypeBalle.VERRE) {
			
			balle.actif = false;
			return null;
		}
		
		super.doCollision(balle);
		
		return null;
	}
}
