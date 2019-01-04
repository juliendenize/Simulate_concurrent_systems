package eu.telecomsudparis.csc4102.simint;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;

/**
 * Cette classe définit le concept de processus.
 * 
 * <p>
 * L'interface {@link Comparable} permet de construire des collections triées.
 * La méthode {@link #compareTo(Processus)} permet de comparer deux processus
 * lors de la manipulation de collections triées.
 * 
 * @author Denis Conan
 */
public class Processus implements Comparable<Processus> {
	/**
	 * l'identifiant du processus.
	 */
	private final String nom;

	/**
	 * construit un processus.
	 * 
	 * @param nom le nom du processus.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 */
	public Processus(final String nom) throws ChaineDeCaracteresNullOuVide {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		this.nom = nom;
		assert invariant();
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return nom != null && !nom.equals("");
	}

	/**
	 * obtient le nom du processus.
	 * 
	 * @return l'identifiant.
	 */
	public String getNom() {
		return nom;
	}

	@Override
	public int compareTo(final Processus other) {
		return nom.compareTo(other.getNom());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Processus)) {
			return false;
		}
		Processus other = (Processus) obj;
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
	public String toString() {
		return "Processus [nom=" + nom + "]";
	}
}
