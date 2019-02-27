package eu.telecomsudparis.csc4102.simint;

import java.util.Objects;

/**
 * Cette classe définit le concept d'instruction.
 * 
 * @author julien
 *
 */
public class Instruction {

	/**
	 * Type d'instruction: P ou V.
	 */
	private TypeInstruction typeInstruction;

	/**
	 * semaphore manipulé par l'instruction.
	 */
	private Semaphore semaphore;
	
	/**
	 * Construit une instruction.
	 * 
	 * @param typeInstruction
	 * 		le type d'instruction
	 * @param semaphore
	 * 		le semaphore manipulé
	 */
	public Instruction(final TypeInstruction typeInstruction, final Semaphore semaphore) {
		Objects.requireNonNull(typeInstruction);
		Objects.requireNonNull(semaphore);
		this.typeInstruction = typeInstruction;
		this.semaphore = semaphore;
		assert invariant();
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return typeInstruction != null && semaphore != null;
	}
	
	/**
	 * Retourne le semaphore.
	 * 
	 * @return le semaphore.
	 */
	public Semaphore getSemaphore() {
		return this.semaphore;
	}
	
	/**
	 * Retourne le type d'instruction.
	 * 
	 * @return le type d'instruction.
	 */
	public TypeInstruction getTypeInstruction() {
		return this.typeInstruction;
	}

	@Override
	public String toString() {
		return "Instruction [typeInstruction=" + typeInstruction + ", semaphore=" + semaphore + "]";
	}
	
	

}
