package entitees;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Classe d'effet de bonus changeant la vitesse de la balle, heritee de EffetBonusBalle
 * 
 * @author Robiiich
 * @version 0.2
 */

public class EffetVitesseBalle extends EffetBonusBalle {
	
	public EffetVitesseBalle(int ax, int ay, TypeBonus type) {
		super(ax, ay, type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.EffetBonus#draw(java.awt.Graphics)
	 */
	
	public void draw(Graphics g) {
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.EffetBonusBalle#timerEffet(java.util.LinkedList)
	 */
	
	public void timerEffet(LinkedList<Balle> balles) {
		
		if (temps == 0) {
			
			switch (type) {
			
				case RAPIDE :
					
					for (int i = 0; i < balles.size(); i++) {
						
						balles.get(i).vx = 1.5 * balles.get(i).vx;
						balles.get(i).vy = 1.5 * balles.get(i).vy;
					}
					
					break;
				
				case LENTE :
					
					for (int i = 0; i < balles.size(); i++) {
						
						balles.get(i).vx = balles.get(i).vx / 1.5;
						balles.get(i).vy = balles.get(i).vy / 1.5;
					}
					
					break;
				
				default :
					
					break;
			}
		}
		temps++;
	}
}
