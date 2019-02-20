package eu.telecomsudparis.csc4102.simint;

public class Instruction {
	
	/**
	 * Type d'instruction: P ou V.
	 */
	private TypeInstruction typeInstruction;
	
	/**
	 * semaphore manipulé par l'instruction.
	 */
	private Semaphore semaphore;
	
	public Instruction(TypeInstruction typeInstruction, Semaphore semaphore) {
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
	
	public Semaphore getSemaphore() {
		return this.semaphore;
	}
	
	public TypeInstruction getTypeInstruction() {
		return this.typeInstruction;
	}

}
