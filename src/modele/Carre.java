package modele;

public class Carre implements Formes {

	//propriétés ou attributs
	private float cote;
	
	public Carre(float cote) {
		this.cote = cote;
	}

	@Override
	public float perimetre() {
		return this.cote * 4;
	}

	@Override
	public float surface() {
		return this.cote * this.cote;
	}

	/*
	 * getter
	 */
	public float getCote() {
		return this.cote;
	}
}
