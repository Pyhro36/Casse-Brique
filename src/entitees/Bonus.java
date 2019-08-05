package entitees;

import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * classe des bonus qui tombent des briques bonus quand la balle les tape
 * 
 * @author PL
 * @version 0.2
 */

public class Bonus extends EntiteMouv {
	
	private TypeBonus type;
	public static LinkedList<EffetBonus> listeEffets;
	
	/**
	 * contructeur du bonus
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param ecran
	 *            cf EntiteMouv
	 * @param type
	 *            le type du Bonus defini selon l'enum TypeBonus, et lui donnant son image
	 */
	
	public Bonus(int ax, int ay, Rectangle ecran, TypeBonus type) {
		super(ax, ay, 0, 3, type.getNomImage(), ecran);
		this.type = type;
	}
	
	/**
     * @param x
     * @param y
     * @param ecran
     * @param chBonus
     */
    public Bonus(int x, int y, Rectangle ecran, String chBonus) {
    	super(x, y, 0, 3, chBonus, ecran);
    	
    	switch(chBonus){
    		
    		case "FEU" :
    			
    			type = TypeBonus.FEU;
    			break;
    			
    		case "COLLE" :
    			
    			type = TypeBonus.COLLE;
    			break;
    			
    		case "RAPIDE" :
    			
    			type = TypeBonus.RAPIDE;
    			break;
    			
    		case "VERRE" :
    			
    			type = TypeBonus.VERRE;
    			break;
    		
    		case "LENTE" :
    			
    			type = TypeBonus.LENTE;
    			break;
    			
    		case "LASER" :
    			
    			type = TypeBonus.LASER;
    			break;
    			
    		case "PETITE" :
    			
    			type = TypeBonus.PETITE;
    			break;
    			
    		case "GRANDE" : 
    			
    			type = TypeBonus.GRANDE;
    			break;
    			
    		case "MULTIBALLE" :
    			
    			type = TypeBonus.MULTIBALLE;
    			break;
    	}
	    // TODO Auto-generated constructor stub
    }

	/**
	 * methode d'initialisation de la liste globale statique des effets des bonus durant le jeu
	 */
	
	public static void initialiseListeEffets() {
		listeEffets = new LinkedList<EffetBonus>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see entitees.EntiteMouv#move()
	 */
	
	public void move() {
		y = y + (int) vy;
		if (y > limframe.y + limframe.height) actif = false;
		lim.setLocation((int) x, (int) y);
	}
	
	/**
	 * methode de test de collision du bonus avec la raquette
	 * 
	 * @param r
	 *            la raquette avec laquelle on fait le test
	 * @return vrai si le bonus est en contact avec la raquette, faux sinon
	 */
	
	public boolean testCollision(Raquette r) {
		if (lim.intersects(r.lim)) return true;
		else return false;
	}
	
	/**
	 * methode d'instantiation de l'effet de bonus dans la liste globale d'effets de bonus suite a la collisison du bonus sur la raquette et en
	 * fonction du type dudit bonus
	 */
	
	public void doCollision() {
		
		boolean instance = false;
		switch (type) {
		
			case FEU :
			case VERRE :
				
				for (int i = 0; i < listeEffets.size(); i++) {
					
					if (listeEffets.get(i) instanceof EffetTypeBalle) {
						
						instance = true;
						listeEffets.set(i, new EffetTypeBalle(700, 50, type));
					}
				}
				
				if (!instance) {
					
					listeEffets.add(new EffetTypeBalle(700, 50, type));
				}
				
				break;
			
			case RAPIDE :
			case LENTE :
				
				for (int i = 0; i < listeEffets.size(); i++) {
					
					if (listeEffets.get(i) instanceof EffetVitesseBalle) {
						
						instance = true;
						listeEffets.set(i, new EffetVitesseBalle(700, 70, type));
					}
				}
				
				if (!instance) {
					
					listeEffets.add(new EffetVitesseBalle(700, 70, type));
				}
				
				break;
			
			case MULTIBALLE :
				
				for (int i = 0; i < listeEffets.size(); i++) {
					
					if (listeEffets.get(i) instanceof EffetMultiBalle) {
						
						instance = true;
						listeEffets.set(i, new EffetMultiBalle(700, 70));
					}
				}
				
				if (!instance) {
					
					listeEffets.add(new EffetMultiBalle(700, 70));
				}
				
				break;
			
			case GRANDE :
			case PETITE :
			case LASER :
			case COLLE :
				
				for (int i = 0; i < listeEffets.size(); i++) {
					
					if (listeEffets.get(i) instanceof EffetBonusRaquette) {
						
						instance = true;
						listeEffets.set(i, new EffetBonusRaquette(700, 90, type));
					}
				}
				
				if (!instance) {
					
					listeEffets.add(new EffetBonusRaquette(700, 90, type));
				}
				
				break;
			
			default :
				break;
		}
		actif = false;
	}
	
	/**
	 * @return le type de bonus
	 */
	public TypeBonus getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
}
