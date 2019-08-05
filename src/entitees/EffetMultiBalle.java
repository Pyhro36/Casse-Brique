package entitees;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * classe d'effet de bonus multipliant par 3 le nombre de balles dans le jeu, heritee de EffetBonusBalle
 * 
 * @author Robiiich
 * @version 0.2
 */

public class EffetMultiBalle extends EffetBonusBalle {
	
	/**
	 * cf EffetBonusBalle
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 */
	
	public EffetMultiBalle(int ax, int ay) {
		super(ax, ay, TypeBonus.MULTIBALLE);
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
			
			double vitx;
			double vity;
			int taille = balles.size();
			
			for (int i = 0; i < taille; i++) {
				
				vitx = balles.get(i).vx;
				vity = balles.get(i).vy;
				
				balles.add(new Balle(balles.get(i).x, balles.get(i).y, balles.get(0).limframe));
				balles.get(balles.size() - 1).vx = vitx;
				balles.get(balles.size() - 1).vy = -vity;
				balles.get(balles.size() - 1).setType(balles.get(i).getType());
				balles.get(balles.size() - 1).image = balles.get(i).image;
				
				balles.add(new Balle(balles.get(i).x, balles.get(i).y, balles.get(0).limframe));
				balles.get(balles.size() - 1).vx = -vitx;
				balles.get(balles.size() - 1).vy = vity;
				balles.get(balles.size() - 1).setType(balles.get(i).getType());
				balles.get(balles.size() - 1).image = balles.get(i).image;
			}
		}
		temps++;
	}
}
