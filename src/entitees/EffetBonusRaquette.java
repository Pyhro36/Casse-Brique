package entitees;

import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * effet de bonus agissant sur la raquette, herite de EffetBonus
 * 
 * @author Robiiich
 * @version 0.2
 */

public class EffetBonusRaquette extends EffetBonus {
	
	/**
	 * cf EffetBonus
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param type
	 *            cf EffetBonus
	 */
	
	public EffetBonusRaquette(int ax, int ay, TypeBonus type) {
		super(ax, ay, type);
	}
	
	/**
	 * methode d'effet sur la raquette en fonction du timer (debut, fin ...)
	 * 
	 * @param raquette
	 *            la raquette a modifier
	 */
	
	public void timerEffet(Raquette raquette) {
		
		if (temps == 0) {
			
			switch (type) {
			
				case GRANDE :
					
					raquette.setType(TypeRaquette.GRANDE);
					break;
				
				case PETITE :
					
					raquette.setType(TypeRaquette.PETITE);
					break;
				
				case COLLE :
					
					raquette.setType(TypeRaquette.COLLE);
					break;
				
				case LASER :
					
					raquette.setType(TypeRaquette.LASER);
					break;
				
				default :
					
					raquette.setType(TypeRaquette.NORMALE);
					break;
			}
			
			String nomImage = raquette.getType().getNomImage();
			
			try {
				raquette.image = ImageIO.read(new File(nomImage));
			} catch (Exception err) {
				System.out.println(nomImage + "introuvable !");
				System.exit(0);
			}
			
			raquette.h = raquette.image.getHeight(null);
			raquette.l = raquette.image.getWidth(null);
			raquette.lim = new Rectangle((int) raquette.x, (int) raquette.y, raquette.l, raquette.h);
		}
		
		temps++;
		
		if (temps >= 600) {
			
			raquette.setType(TypeRaquette.NORMALE);
			
			try {
				raquette.image = ImageIO.read(new File("raquette.png"));
			} catch (Exception err) {
				System.out.println("raquette.png introuvable !");
				System.exit(0);
			}
			
			actif = false;
			raquette.h = raquette.image.getHeight(null);
			raquette.l = raquette.image.getWidth(null);
			raquette.lim = new Rectangle((int) raquette.x, (int) raquette.y, raquette.l, raquette.h);
		}
	}
}
