package entitees;

import java.awt.Color;
import java.awt.Graphics;

/**
 * classe abstraite d'effet de bonus qui agit a son activation et sa desactivation s'il y en a une sur le jeu et s'affiche sur le cote
 * 
 * @author PL
 * @version 0.2
 */

public abstract class EffetBonus extends Entite {
	
	protected TypeBonus type;
	protected long temps;
	
	/**
	 * Contructeur de l'effet de bonus
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param type
	 *            du bonus activant l'effet, c'est un enum TypeBonus
	 */
	
	public EffetBonus(int ax, int ay, TypeBonus type) {
		super(ax, ay, type.getNomImage());
		
		this.type = type;
		temps = 0;
		actif = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.Entite#draw(java.awt.Graphics)
	 */
	
	public void draw(Graphics g) {
		super.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString("" + (600 - temps), x + l + 10, y + (h / 2));
	}
}
