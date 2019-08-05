package entitees;

/**
 * Classe de la brique bonus, herite de brique et contient en plus un Bonus, qui tombe lorsqu'on touche la brique
 * 
 * @author Robiiich
 * @version 0.2
 */

public class BriqueBonus extends Brique {
	
	private Bonus bonus;
	final static int SCORE = 50;
	
	/**
	 * Constructeur de la brique Bonus
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param abonus
	 *            le bonus que doit contenir la brique
	 */
	
	public BriqueBonus(int ax, int ay, Bonus abonus) {
		super(ax, ay, "briquebonus.png", TypeBrique.BONUS, SCORE);
		
		bonus = abonus;
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.Brique#doCollision(entitees.Balle)
	 */
	
	public Bonus doCollision(Balle balle) {
		super.doCollision(balle);
		
		actif = false;
		return bonus;
	}
	
	/**
	 * @return le bonus associe a la brique
	 */
	
	public Bonus getBonus() {
		
		return bonus;
	}
}
