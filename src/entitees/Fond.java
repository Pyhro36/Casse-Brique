package entitees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe du fond d'ecran des phases de jeu
 * 
 * @author PL
 * @version 0.2
 */

public class Fond extends Entite {
	
	/**
	 * Constructeur par defaut du fond, l'image de ce dernier est chargee en fonction des informations stockee dans les options
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	public Fond() throws FileNotFoundException, IOException {
		super(0, 0, "fondEspace.png");
		
		String nomImage;
		FileReader fichier1 = new FileReader("options");
		char[] a = new char[2];
		fichier1.read(a);
		
		switch (a[1] - 48) {
		
			case (1) :
				
				nomImage = "fondEspace.png";
				break;
			
			case (2) :
				
				nomImage = "fondJungle.png";
				break;
			
			case (3) :
				
				nomImage = "fondFractale.png";
				break;
			
			case (4) :
				
				nomImage = "fondAquatique.png";
				break;
			
			default :
				
				nomImage = "fondEspace.png";
				break;
		
		}
		try {
			
			image = ImageIO.read(new File(nomImage));
			
		} catch (Exception err) {
			
			System.out.println(nomImage + " introuvable !");
			System.out.println("Mettre les images dans le repertoire :" + getClass().getClassLoader().getResource(nomImage));
			System.exit(0);
		}
		
		fichier1.close();
	}
	
	/**
	 * Constructeur parametre du fond, avec lequel on peut choisir l'image a afficher
	 * 
	 * @param nomImage
	 *            le nom du fichier image a afficher en fond
	 */
	
	public Fond(String nomImage) {
		super(0, 0, nomImage);
	}
	
}
