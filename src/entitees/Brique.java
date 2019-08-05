package entitees;

/**
 * Classe abstraite definissant les briques, d'ou herite les different type de briques, herite de Entite
 * 
 * @author Robiiich
 * @version 0.2
 */

public abstract class Brique extends Entite {
	
	private int points;
	private TypeBrique type;
	
	/**
	 * Constructeur de la brique
	 * 
	 * @param ax
	 *            cf Entite
	 * @param ay
	 *            cf Entite
	 * @param nomimage
	 *            cf Entite
	 * @param type
	 *            le type de Brique, les differents types de briques etant definis par l'enum TypeBrique
	 * @param apoints
	 *            le nombre de points que rapporte la brique lorsqu'elle est détruite
	 */
	
	public Brique(int ax, int ay, String nomimage, TypeBrique type, int apoints) {
		super(ax, ay, nomimage);
		
		points = apoints;
		this.type = type;
	}
	
	/**
	 * methode de teste de collision de la brique avec une balle
	 * 
	 * @param balle
	 *            la balle avec laquelle on teste la collision
	 * @return vrai si la balle est en contact avec la brique, faux sinon
	 */
	
	public boolean testCollision(Balle balle) {
		
		if (lim.intersects(balle.lim)) return true;
		else return false;
	}
	
	/**
	 * methode de changement de vitesse de la balle suite a sa collision avec la brique (a revoir) et de disparition de la brique si la balle est FEU
	 * 
	 * @param balle
	 *            la balle qui a collisionne la brique
	 * @return l'eventuel bonus associe a la brique, null sinon
	 */
	
	public Bonus doCollision(Balle balle) {
		
		if (balle.getType() == TypeBalle.FEU) {
			
			this.actif = false;
			return null;
		}
		
		if (this.x < (balle.x + balle.l) && (this.x >= balle.x)) {
			
			if (this.y >= balle.y && this.y <= (balle.y + balle.h)) {
				
				if ((balle.x + balle.l - this.x) < (balle.y + balle.h - this.y)) {
					
					balle.vx = -balle.vx;
					
				} else {
					
					balle.vy = -balle.vy;
				}
				
			} else if ((this.y + this.h) <= (balle.y + balle.h) && (this.y + this.h) >= balle.y) {
				
				if ((balle.x + balle.l - this.x) < (this.y + this.h - balle.y)) {
					
					balle.vx = -balle.vx;
					
				} else {
					
					balle.vy = -balle.vy;
				}
			} else {
				
				balle.vx = -balle.vx;
			}
			
		} else if ((this.x + this.l) > balle.x && (this.x + this.l) <= (balle.x + balle.l)) {
			
			if (this.y >= balle.y && this.y <= (balle.y + balle.h)) {
				
				if ((this.x + this.l - balle.x) < (balle.y + balle.h - this.y)) {
					
					balle.vx = -balle.vx;
					
				} else {
					
					balle.vy = -balle.vy;
				}
				
			} else if ((this.y + this.h) <= (balle.y + balle.h) && (this.y + this.h) >= balle.y) {
				
				if ((this.x + this.l - balle.x) < (this.y + this.h - balle.y)) {
					
					balle.vx = -balle.vx;
					
				} else {
					
					balle.vy = -balle.vy;
				}
			} else {
				
				balle.vx = -balle.vx;
			}
			
		} else if (this.y < (balle.y + balle.h) && (this.y >= balle.y)) {
			
			if (this.x >= balle.x && this.x <= (balle.x + balle.l)) {
				
				if ((balle.y + balle.h - this.y) < (balle.x + balle.l - this.x)) {
					
					balle.vy = -balle.vy;
					
				} else {
					
					balle.vx = -balle.vx;
				}
				
			} else if ((this.x + this.l) <= (balle.x + balle.l) && (this.x + this.l) >= balle.x) {
				
				if ((balle.y + balle.h - this.y) < (this.x + this.l - balle.x)) {
					
					balle.vy = -balle.vy;
					
				} else {
					
					balle.vx = -balle.vx;
				}
			} else {
				
				balle.vy = -balle.vy;
			}
			
		} else if ((this.y + this.h) > balle.y && (this.y + this.h) <= (balle.y + balle.h)) {
			
			if (this.x >= balle.x && this.x <= (balle.x + balle.l)) {
				
				if ((this.y + this.h - balle.y) < (balle.x + balle.l - this.x)) {
					
					balle.vy = -balle.vy;
					
				} else {
					
					balle.vx = -balle.vx;
				}
				
			} else if ((this.x + this.l) <= (balle.x + balle.l) && (this.x + this.l) >= balle.x) {
				
				if ((this.y + this.h - balle.y) < (this.x + this.l - balle.x)) {
					
					balle.vy = -balle.vy;
					
				} else {
					
					balle.vx = -balle.vx;
				}
			} else {
				
				balle.vy = -balle.vy;
			}
		}
		return null;
	}
	
	/**
	 * @return le nombre de points que vaut la destruction de la brique
	 */
	
	public int getScore() {
		
		return points;
	}
	
	/**
	 * @return le type de la brique
	 */
	
	public TypeBrique getType() {
		
		return type;
	}
}
