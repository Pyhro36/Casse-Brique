package listeners;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe de generaion de l'ecouteur de clavier pour le Casse Brique
 * 
 * @author Pierre-Louis
 * @version 0.1
 */

public class JeuKeyAdapter extends KeyAdapter {
	
	private boolean tEspace;
	private boolean tHaut;
	private boolean tDroite;
	private boolean tGauche;
	private boolean tEnter;
	private boolean tEscape;
    private boolean tQ;
    private boolean tD;
    private boolean t0;
	
	/**
	 * methode evenementielle appelee par l'enfoncement d'une touche du clavier
	 */
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		switch (code) {
		
			case KeyEvent.VK_SPACE :
				tEspace = true;
				break;
			
			case KeyEvent.VK_LEFT :
				tGauche = true;
				break;
			
			case KeyEvent.VK_RIGHT :
				tDroite = true;
				break;
			
			case KeyEvent.VK_UP :
				tHaut = true;
				break;
			
			case KeyEvent.VK_ENTER :
				tEnter = !tEnter;
				break;
			
			case KeyEvent.VK_ESCAPE :
				tEscape = !tEscape;
				break;
                
            case KeyEvent.VK_Q :
                tQ = true;
        		break;
        		
        	case KeyEvent.VK_D :
                tD = true;
                break;
                
        	case KeyEvent.VK_NUMPAD0:
                t0 = true;
                break;
			
			default :
				break;
		}
	}
	
	/**
	 * methode evenementielle appelee lorque qu'une touche du clavier est relachee
	 */
	
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		switch (code) {
		
			case KeyEvent.VK_SPACE :
				tEspace = false;
				break;
			
			case KeyEvent.VK_LEFT :
				tGauche = false;
				break;
			
			case KeyEvent.VK_RIGHT :
				tDroite = false;
				break;
			
			case KeyEvent.VK_UP :
				tHaut = false;
				break;
                    
            case KeyEvent.VK_Q :
                tQ = false;
                break;
 
            case KeyEvent.VK_D :
                tD = false;
                break;
                
            case KeyEvent.VK_NUMPAD0:
            	t0 = false;
            	break;
			
			default :
				break;
		}
	}
	
	/**
	 * @return true si la touche espace est enfoncee, false sinon
	 */
	
	public boolean isEspace() {
		
		return tEspace;
	}
	
	/**
	 * @return true si la touche haut est enfoncee, false sinon
	 */
	
	public boolean isHaut() {
		
		return tHaut;
	}
	
	/**
	 * @return true si la touche droite est enfoncee, false sinon
	 */
	
	public boolean isDroite() {
		
		return tDroite;
	}
	
	/**
	 * @return true si la touche gauche est enfoncee, false sinon
	 */
	
	public boolean isGauche() {
		
		return tGauche;
	}
	
	/**
	 * @return false jusqu'à se qu'on appuie une premiere fois sur enter, puis true, puis false a nouveau si on rappuie sur enter, etc ..
	 */
	
	public boolean isEnter() {
		
		return tEnter;
	}
	
    /**
     * @return true si la touche echap est enfoncee, false sinon
     */
	public boolean isEscape() {
		
		return tEscape;
	}
	
    /**
     * @return true si la touche q est enfoncee, false sinon
     */
	public boolean isQ() {
            
		return tQ;
    }
	
    /**
     * @return true si la touche d est enfoncee, false sinon
     */
	public boolean isD() {
        
		return tD;
    }
	
    /**
     * @return true si la touche 0 du pavé numérique est enfoncee, false sinon
     */
    public boolean is0() {
    
        return t0;
    }	
}
