package entitees;

/**
 * @author Pierre-Louis
 * @version 0.2
 */
public enum TypeRaquette {
	
	NORMALE("raquette.png"),
	COLLE("raquettecolle.png"),
	LASER("raquettelaser.png"),
	GRANDE("raquettegrande.png"),
	PETITE("raquettepetite.png");
	
	private String nomImage;
	
	private TypeRaquette(String nomImage) {
		
		this.nomImage = nomImage;
	}
	
	/**
	 * @return le nom du fichier image correspondant au type de raquette
	 */
	
	public String getNomImage() {
		
		return nomImage;
	}
}
