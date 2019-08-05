package entitees;

/**
 * @author Pierre-Louis
 * @version 0.2
 */
public enum TypeBalle {
	
	NORMALE("balle.png"),
	FEU("ballefeu.png"),
	VERRE("balleverre.png");
	
	private String nomImage;
	
	private TypeBalle(String nomImage) {
		
		this.nomImage = nomImage;
	}
	
	/**
	 * @return le nom du fichier associe au type de balle
	 */
	
	public String getNomImage() {
		
		return nomImage;
	}
	
}
