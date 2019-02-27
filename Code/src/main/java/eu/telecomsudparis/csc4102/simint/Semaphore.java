package eu.telecomsudparis.csc4102.simint;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ValeurInitialeHorsBorne;

/**
 * Cette classe définit le concept de semaphore.
 * 
 * @author julien
 *
 */
public class Semaphore {

	/**
	 * Nom du semaphore.
	 */
	private String nom;

	/**
	 * valeur initiale du semaphore.
	 */
	private int valeurInitiale;
	
	/**
	 * Construit un semaphore.
	 * 
	 * @param nom
	 * 		Le nom du semaphore.
	 * @param valeurInitiale
	 * 		La valeur initiale du semaphore.
	 * @throws ChaineDeCaracteresNullOuVide
	 * 		Le nom est null ou vide.
	 * @throws ValeurInitialeHorsBorne
	 * 		La valeur initiale est inférieure à 0.
	 */
	public Semaphore(final String nom, final int valeurInitiale) throws ChaineDeCaracteresNullOuVide, ValeurInitialeHorsBorne {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (valeurInitiale < 0) {
			throw new ValeurInitialeHorsBorne("La valeur initiale d'un sémaphore doit être supérieure à 0");
		}
		this.nom = nom;
		this.valeurInitiale = valeurInitiale;
		assert invariant();
	}
	
	/**
	 * Retourne la valeur initiale.
	 * 
	 * @return la valeur initiale.
	 */
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
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
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
		return "Semaphore [nom=" + nom + ", valeurInitiale=" + valeurInitiale + "]";
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return nom != null && !nom.equals("") && valeurInitiale >= 0;
	}
	
	/**
	 * Retourne le nom du semaphore.
	 * 
	 * @return le nom du semaphore.
	 */
	public String getNom() {
		return nom;
	}

}
