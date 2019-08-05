package framesAux;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import entitees.Bonus;
import entitees.Brique;
import entitees.BriqueBonus;
import entitees.BriqueIndes;
import entitees.BriqueNVies;
import entitees.TypeBrique;

/**
 * Classe abstraite contenant les methodes pour convertir un fichier de niveau en grille ou en chaine de brique
 * 
 * @author Peter Ludovic
 * @version 0.2
 */

public abstract class Convertisseur {
	
	/**
	 * methode de conversion d'une matrice (tableau[][]) de brique en un fichier de niveau
	 * 
	 * @param nomFichier
	 *            le nom du fichier (nom du niveau)
	 * @param niveau
	 *            la matrice de brique a convertir
	 * @throws IOException
	 */
	
	public static void sauver(String nomFichier, Brique[][] niveau) throws IOException {
		
		String chaineNiveau = "";
		
		// convertit le tableau de Brique en chaine
		
		for (int i = 0; i < niveau.length; i++) {
			
			for (int j = 0; j < niveau[i].length; j++) {
				
				if (niveau[i][j] != null) {
					
					if (niveau[i][j].getType() == TypeBrique.INDESTRUCTIBLE) {
						
						chaineNiveau = chaineNiveau + (int) niveau[i][j].getX() + "," + (int) niveau[i][j].getY() + "," + "i,";
						
					} else if (niveau[i][j].getType() == TypeBrique.NVIES) {
						
						BriqueNVies a = (BriqueNVies) niveau[i][j];
						chaineNiveau = chaineNiveau + (int) a.getX() + "," + (int) a.getY() + "," + "n" + (int) a.getN() + ",";
						
					} else if (niveau[i][j].getType() == TypeBrique.BONUS) {
						
						BriqueBonus a = (BriqueBonus) niveau[i][j];
						chaineNiveau = chaineNiveau + (int) a.getX() + "," + (int) a.getY() + "," + "b" + a.getBonus().getType() + ",";
					}
				}
			}
		}
		
		// convertit la liste de brique en chaine et la sauvegarde dans un fichier
		
		System.out.println("nomFichier");
		FileWriter fichier = new FileWriter(nomFichier, false);
		fichier.flush();
		fichier.write(chaineNiveau);
		fichier.close();
		
		// répertorie le nouveau niveau
		
		FileWriter listeNiveau = new FileWriter("listeNiveau", true);
		listeNiveau.flush();
		listeNiveau.write(nomFichier + ";");
		listeNiveau.close();
	}
	
	/**
	 * charge dans une matrice de briques qu'elle retourne un fichier de niveau
	 * 
	 * @param nomFichier
	 *            le nom du fichier de niveau
	 * @param ecran
	 *            le rectangle de dimension dans lequel vont s'afficher les briques (pourquoi)
	 * @return la matrice de briques a afficher
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	public static Brique[][] charger(String nomFichier, Rectangle ecran) throws FileNotFoundException, IOException {
		
		// récupère la longueur du fichier
		File fichier2 = new File(nomFichier);
		int n = (int) fichier2.length();
		
		// récupère la sauvegarde d'un fichier et le convertit
		FileReader fichier1 = new FileReader(nomFichier);
		char[] a = new char[n];
		fichier1.read(a);
		
		// faire le décodeur
		Brique[][] test = new Brique[18][25];
		int curseur = 0;
		while (curseur < n) {
			String intermediaire = "";
			int x = 0;
			int y = 0;
			while (a[curseur] != ',') {
				intermediaire = intermediaire + a[curseur];
				curseur++;
			}
			if (intermediaire.length() == 3)
			    x = (100 * (intermediaire.charAt(0) - 48)) + (10 * (intermediaire.charAt(1) - 48)) + (1 * (intermediaire.charAt(2) - 48));
			if (intermediaire.length() == 2) x = (10 * (intermediaire.charAt(0) - 48)) + (1 * (intermediaire.charAt(1) - 48));
			if (intermediaire.length() == 1) x = (1 * (intermediaire.charAt(0) - 48));
			curseur++;
			
			intermediaire = "";
			while (a[curseur] != ',') {
				
				intermediaire = intermediaire + a[curseur];
				curseur++;
			}
			if (intermediaire.length() == 3)
			    y = (100 * (intermediaire.charAt(0) - 48)) + (10 * (intermediaire.charAt(1) - 48)) + (1 * (intermediaire.charAt(2) - 48));
			if (intermediaire.length() == 2) y = (10 * (intermediaire.charAt(0) - 48)) + (1 * (intermediaire.charAt(1) - 48));
			if (intermediaire.length() == 1) y = (1 * (intermediaire.charAt(0) - 48));
			curseur++;
			switch (a[curseur]) {
				case ('i') :
					test[(int) (x / 38)][(int) (y / 19)] = new BriqueIndes(x, y);
					break;
				
				case ('n') :
					test[(int) (x / 38)][(int) (y / 19)] = new BriqueNVies(x, y, a[curseur + 1] - 48);
					break;
				
				case ('b') :
					
					curseur++;
					String chBonus = "";
					while (a[curseur] != ',') {
						chBonus += a[curseur];
						curseur++;
					}
					
					Bonus bonus = new Bonus((int) x, (int) y, ecran, chBonus);
					test[(int) (x / 38)][(int) y / 19] = new BriqueBonus(x, y, bonus);
					break;
				
				default :
					break;
			}
			
			while (a[curseur] != ',') {
				curseur++;
			}
			curseur++;
		}
		
		fichier1.close();
		return test;
	}
	
	/**
	 * methode de conversion d'une matrice de briques en liste LinkedList<> de briques
	 * 
	 * @param test
	 *            la matrice de brique
	 * @return la liste LinkedList de briques
	 */
	
	public static LinkedList<Brique> convertirEnListe(Brique[][] test) {
		LinkedList<Brique> niveau = new LinkedList<Brique>();
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 25; j++) {
				if (test[i][j] != null) niveau.add(test[i][j]);
			}
		}
		return niveau;
	}
}
