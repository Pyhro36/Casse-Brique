package entitees;

/**
 * @author Pierre-Louis
 * @version 0.2
 */

public enum TypeBonus {
	
	FEU("bonusballefeu.png", "FEU"),
	VERRE("bonusballeverre.png", "VERRE"),
	RAPIDE("bonusballerapinde.png", "RAPIDE"),
	LENTE("bonusballelente.png", "LENTE"),
	GRANDE("bonusraquettegrande.png", "GRANDE"),
	PETITE("bonusraquettepetite.png", "PETITE"),
	COLLE("bonusrquettecolle.png", "COLLE"),
	LASER("bonusraquettelaser.png", "LASER"),
	MULTIBALLE("bonusmultiballe.png", "MULTIBALLE");
	
	private String nomImage;
	private String nom;
	
	private TypeBonus(String nomImage, String nom) {
		
		this.nomImage = nomImage;
		this.nom = nom;
	}
	
	/**
	 * @return le nom su fichier image associe au type de bonus
	 */
	
	public String getNomImage() {
		
		return nomImage;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		
		return nom;
	}
}
