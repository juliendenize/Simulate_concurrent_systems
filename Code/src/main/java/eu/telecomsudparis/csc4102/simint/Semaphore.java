package eu.telecomsudparis.csc4102.simint;

public class Semaphore {
	
	/**
	 * Nom du semaphore
	 */
	private String nom;
	
	/**
	 * valeur initiale du semaphore
	 */
	private int valeurInitiale;
	
	public Semaphore(String nom, int valeurInitiale) {
		this.nom = nom;
		this.valeurInitiale = valeurInitiale;
		assert this.nom.equals(nom);
		assert (this.valeurInitiale == valeurInitiale);
		assert invariant();
	}
	
	public int getValeurInitiale() {
		return this.valeurInitiale;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Semaphore)) {
			return false;
		}
		Semaphore other = (Semaphore) obj;
		if (nom == null) {
			if (other.getNom() != null) {
				return false;
			}
		} else if (!nom.equals(other.getNom())) {
			return false;
		}
		if (valeurInitiale != other.getValeurInitiale()) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "Semaphore [nom=" + nom + "]";
	}
	
	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return nom != null && !nom.equals("") && valeurInitiale >= 0;
	}
	
	public String getNom() {
		return nom;
	}

}
