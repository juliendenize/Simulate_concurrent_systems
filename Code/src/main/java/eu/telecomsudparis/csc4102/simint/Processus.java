package eu.telecomsudparis.csc4102.simint;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.InstructionNonExistante;

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
	 * programme exécuté par le processus.
	 */
	private final Programme programme;

	/**
	 * construit un processus.
	 * 
	 * @param nom le nom du processus.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 */
	public Processus(final String nom, final Programme programme) throws ChaineDeCaracteresNullOuVide {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		this.nom = nom;
		this.programme = programme;
		assert this.nom.equals(nom);
		assert this.programme.equals(programme);
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
	
	public Programme getProgramme () {
		return programme;
	}
	
	public Instruction chercherInstruction(int numeroInstruction) throws InstructionNonExistante {
		return programme.chercherInstruction(numeroInstruction);
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
			if (other.getNom() != null) {
				return false;
			}
		} else if (!nom.equals(other.getNom())) {
			return false;
		}
		if (programme == null) {
			if(other.getProgramme() != null) {
				return false;
			}
		} else if(!programme.equals(other.getProgramme())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Processus [nom=" + nom + "]";
	}
}
