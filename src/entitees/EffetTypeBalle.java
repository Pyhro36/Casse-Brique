package entitees;

import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * effet herite de EffetBonusBalle modifiant le type de balle
 * 
 * @author Robiiich
 * @version 0.2
 */

public class EffetTypeBalle extends EffetBonusBalle {
	
	/**
	 * cf EffetBonusBalle
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param type
	 *            cf EffetBonus
	 */
	
	public EffetTypeBalle(int ax, int ay, TypeBonus type) {
		super(ax, ay, type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.EffetBonusBalle#timerEffet(java.util.LinkedList)
	 */
	
	public void timerEffet(LinkedList<Balle> balles) {
		
		if (temps == 0) {
			
			switch (type) {
			
				case FEU :
					
					for (int i = 0; i < balles.size(); i++) {
						
						balles.get(i).setType(TypeBalle.FEU);
					}
					
					break;
				
				case VERRE :
					
					for (int i = 0; i < balles.size(); i++) {
						
						balles.get(i).setType(TypeBalle.VERRE);
					}
					
					break;
				
				default :
					break;
			}
			
			for (int i = 0; i < balles.size(); i++) {
				
				try {
					
					balles.get(i).image = ImageIO.read(new File(balles.get(i).getType().getNomImage()));
					
				} catch (Exception err) {
					
					System.out.println("balleverre.png introuvable !");
					System.exit(0);
				}
			}
		}
		
		temps++;
		
		if (temps >= 600) {
			
			for (int i = 0; i < balles.size(); i++) {
				
				balles.get(i).setType(TypeBalle.NORMALE);
				
				try {
					
					balles.get(i).image = ImageIO.read(new File(balles.get(i).getType().getNomImage()));
					
				} catch (Exception err) {
					System.out.println("balle.png introuvable !");
					System.exit(0);
				}
			}
			actif = false;
		}
	}
}
