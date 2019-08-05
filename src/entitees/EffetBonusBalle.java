package entitees;

import java.util.LinkedList;

/**
 * effet bonus correspondant a changement sur la balle (type, vitesse, ...), herite de EffetBonus
 * 
 * @author PL
 * @version 0.2
 */

public abstract class EffetBonusBalle extends EffetBonus {
	
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
	
	public EffetBonusBalle(int ax, int ay, TypeBonus type) {
		super(ax, ay, type);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * effetcue le changement sur la ou les balle(s) en fonction du timer du bonus (debut, fin ...)
	 * 
	 * @param balles
	 */
	
	public abstract void timerEffet(LinkedList<Balle> balles);
}
