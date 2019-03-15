package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe définit le concept de programme.
 * 
 * @author julien
 *
 */
public class Programme {
	@Override
	public String toString() {
		return "Programme [nom=" + nom + ", instructions=" + instructions + "]";
	}

	/**
	 * Nom du programme.
	 */
	private String nom;

	/**
	 * Les instructions du programme.
	 */
	private List<Instruction> instructions;
	
	/**
	 * Construit un programme.
	 * 
	 * @param nom
	 * 		Le nom du programme.
	 */
	public Programme(final String nom) {
		if (nom == null || nom.equals("")) {
			throw new IllegalArgumentException("identifiant null ou vide non autorisé");
		}
		this.nom = nom;
		this.instructions = new ArrayList<Instruction>();
		assert invariant();
	}
	
	/**
	 * Ajoute une instruction à la collection des instructions.
	 * 
	 * @param instruction
	 * 			l'instruction concernée.
	 */
	public void ajouterInstruction(final Instruction instruction) {
		this.instructions.add(instruction);
	}
	
	/**
	 * Cherche une instruction dans la collection des instructions.
	 * 
	 * @param numeroInstruction
	 * 			le numéro de l'instruction
	 * @return l'instruction trouvée.
	 */
	public Instruction chercherInstruction(final int numeroInstruction) {
		if (this.instructions.size() <= numeroInstruction) {
			throw new IllegalArgumentException("instruction numéro '" + numeroInstruction + "' n'existe pas");
		}
		return this.instructions.get(numeroInstruction);
	}
	
	/**
	 * Retourne le nombre d'instructions du programme.
	 * 
	 * @return le nombre d'instructions.
	 */
	public int getNombreInstructions() {
		return this.instructions.size();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Programme)) {
			return false;
		}
		Programme other = (Programme) obj;
		if (instructions == null) {
			if (other.instructions != null) {
				return false;
			}
		} else if (!instructions.equals(other.instructions)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		return result;
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
	 * Retourne le nom du programme.
	 * 
	 * @return le nom du programme.
	 */
	public String getNom() {
		return this.nom;
	}

}
