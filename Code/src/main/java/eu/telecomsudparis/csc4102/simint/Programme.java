package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import eu.telecomsudparis.csc4102.simint.exception.InstructionNonExistante;

public class Programme {
	/**
	 * Nom du programme
	 */
	private String nom;
	
	/**
	 * Les instructions du programme
	 */
	private List<Instruction> instructions;
	
	public Programme(String nom) {
		this.nom = nom;
		this.instructions = new ArrayList<Instruction>();
		assert this.nom.equals(nom);
		assert invariant();
	}
	
	public void ajouterInstruction(Instruction instruction) {
		this.instructions.add(instruction);
	}
	
	public Instruction chercherInstruction(int numeroInstruction) throws InstructionNonExistante {
		if (this.instructions.size() <= numeroInstruction) {
			throw new InstructionNonExistante("instruction numéro '" + numeroInstruction + "' n'existe pas");
		}
		return this.instructions.get(numeroInstruction);
	}
	
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
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		return true;
	}
	
	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return nom != null && !nom.equals("");
	}

}
