package eu.telecomsudparis.csc4102.simint;

import java.util.Objects;

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
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return processus != null;
	}

	/**
	 * obtient le processus concerné par l'état.
	 * 
	 * @return le processus.
	 */
	public Processus getProcessus() {
		return processus;
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
			if (other.processus != null) {
				return false;
			}
		} else if (!processus.equals(other.processus)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Proc [#=" + compteurInstance + ", nom=" + processus.getNom() + "]";
	}
}
