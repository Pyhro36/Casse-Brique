package entitees;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe de la brique normale, a n vies, il faut la taper n fois pour la detruire, herite de Brique
 * 
 * @author Robiiich
 * @version 0.2
 */

public class BriqueNVies extends Brique {
	
	private int n;
	private String style;
	
	/**
	 * Constructeur de la brique a n vies
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param vies
	 *            le nombre de vies de la briques, la brique ne peut avoir que de une a trois vies
	 */
	
	public BriqueNVies(int ax, int ay, int vies) {
		super(ax, ay, "brique3viesEspace.png", TypeBrique.NVIES, 100);
		
		n = vies;
		
		if (n > 3) n = 3;
		if (n <= 0) n = 1;
		
		FileReader fichier1;
		String nomImage;
		
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
				
				style = "Espace";
				break;
			
			case (2) :
				
				style = "Jungle";
				break;
			
			case (3) :
				
				style = "Fractale";
				break;
			
			case (4) :
				
				style = "Aquatique";
				break;
			
			default :
				
				style = "Espace";
		}
		switch (n) {
		
			case 3 :
				
				nomImage = "brique3vies" + style + ".png";
				break;
			
			case 2 :
				
				nomImage = "brique2vies" + style + ".png";
				break;
			
			case 1 :
				
				nomImage = "brique1vie" + style + ".png";
				break;
			
			default :
				
				nomImage = "brique1vie" + style + ".png";
				break;
		}
		
		try {
			
			image = ImageIO.read(new File(nomImage));
			
		} catch (Exception err) {
			
			System.out.println(nomImage + " introuvable !");
			System.out.println("Mettre les images dans le repertoire :" + getClass().getClassLoader().getResource(nomImage));
			System.exit(0);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.Brique#doCollision(entitees.Balle)
	 */
	
	public Bonus doCollision(Balle balle) {
		
		super.doCollision(balle);
		
		n--;
		
		String nomImage;
		
		switch (n) {
		
			case 3 :
				
				nomImage = "brique3vies" + style + ".png";
				break;
			
			case 2 :
				
				nomImage = "brique2vies" + style + ".png";
				break;
			
			case 1 :
				
				nomImage = "brique1vie" + style + ".png";
				break;
			
			default :
				
				nomImage = "brique1vie" + style + ".png";
				break;
		}
		if (n == 0) {
			actif = false;
			return null;
		}
		
		try {
			
			image = ImageIO.read(new File(nomImage));
			
		} catch (Exception err) {
			
			System.out.println(nomImage + " introuvable !");
			System.out.println("Mettre les images dans le repertoire :" + getClass().getClassLoader().getResource(nomImage));
			System.exit(0);
		}
		
		return null;
	}
	
	/**
	 * @return le nombre de vies de la brique
	 */
	
	public int getN() {
		return n;
	}
}
