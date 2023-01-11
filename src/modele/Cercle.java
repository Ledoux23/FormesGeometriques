package modele;

public class Cercle implements Formes {

	//propriétés ou attributs
	private float rayon;
	
	public Cercle(float rayon) {
		this.rayon = rayon;
	}

	@Override
	public float perimetre() {
		return (float)(2*Math.PI*this.rayon);
	}

	@Override
	public float surface() {
		return (float) (Math.PI*this.rayon*this.rayon);
	}
	
	/*
	 * getter
	 */
	public float getRayon() {
		return rayon;
	}

}
