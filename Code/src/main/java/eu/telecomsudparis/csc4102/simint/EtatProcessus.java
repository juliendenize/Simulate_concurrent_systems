package eu.telecomsudparis.csc4102.simint;

import java.util.Objects;

import eu.telecomsudparis.csc4102.simint.exception.InstructionNonExistante;

/**
 * Cette classe définit le concept d'état d'un processus.
 * 
 * @author Denis Conan
 */
public class EtatProcessus implements Comparable<EtatProcessus> {
	/**
	 * le processus concerné.
	 */
	private final Processus processus;
	
	/**
	 * etat courant du processus: vivant, bloqué ou terminé 
	 */
	private Etat etat;
	
	
	/**
	 * Numéro de la dernière instruction qui a été exécutée
	 */
	private int instructionCourante;
	
	/**
	 * compteur d'instanciation.
	 */
	private static int compteurInstanciation = 0;
	
	/**
	 * compteur d'instance.
	 */
	private int compteurInstance;

	/**
	 * construit un état d'un processus.
	 * 
	 * @param processus le processus concerné.
	 */
	public EtatProcessus(final Processus processus) {
		Objects.requireNonNull(processus, "processus ne peut pas être null");
		this.processus = processus;
		this.etat = Etat.vivant;
		this.instructionCourante = 0;
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	/**
	 * construit un état d'un processus comme une copie légère (le processus n'est
	 * pas copié).
	 * 
	 * @param origine l'état du processus d'origine.
	 */
	public EtatProcessus(final EtatProcessus origine) {
		Objects.requireNonNull(origine, "fournir un état de processus origine");
		this.processus = origine.processus;
		this.etat = origine.getEtat();
		this.instructionCourante = origine.getInstructionCourante();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	private int getInstructionCourante() {
		return this.instructionCourante;
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return processus != null && etat != null && instructionCourante >= 0 && 
				compteurInstanciation > 0 && compteurInstance > 0;
	}

	/**
	 * obtient le processus concerné par l'état.
	 * 
	 * @return le processus.
	 */
	public Processus getProcessus() {
		return processus;
	}
	
	public Instruction chercherInstruction() throws InstructionNonExistante {
		return processus.chercherInstruction(instructionCourante);
		
	}

	@Override
	public int compareTo(final EtatProcessus other) {
		return processus.getNom().compareTo(other.processus.getNom());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((processus == null) ? 0 : processus.hashCode());
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
		if (!(obj instanceof EtatProcessus)) {
			return false;
		}
		EtatProcessus other = (EtatProcessus) obj;
		if (processus == null) {
			if (other.getProcessus() != null) {
				return false;
			}
		} else if (!processus.equals(other.getProcessus())) {
			return false;
			}
		if(!etat.equals(other.getEtat())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EtatProc [#=" + compteurInstance + ", nom=" + processus.getNom() + ", etat=" + etat + ", instruction=" + instructionCourante +"]";
	}
	
	public Etat getEtat() {
		return this.etat;
	}
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void avancerInstruction() {
		if(instructionCourante == processus.getProgramme().getNombreInstructions() - 1) {
			etat = Etat.termine;
		} else {
			instructionCourante++;
		}
	}
}
