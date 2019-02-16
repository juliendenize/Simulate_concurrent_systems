package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EtatSemaphore implements Comparable<EtatSemaphore>{

	/**
	 * Valeur du compteur du semaphore.
	 */
	private int valeurCompteur;
	
	/**
	 * nombre d'états sémaphore instanciés.
	 */
	private static int compteurInstanciation = 0;
	
	/**
	 * numéro d'instance
	 */
	private int compteurInstance;
	
	/**
	 * semaphore
	 */
	private Semaphore semaphore;
	
	/**
	 * Collection de processus mis en attente
	 */
	private List<Processus> fileAttente;
	
	public EtatSemaphore(Semaphore semaphore) {
		this.valeurCompteur = semaphore.getValeurInitiale();
		this.semaphore = semaphore;
		this.fileAttente = new ArrayList<Processus>();
		compteurInstanciation++;
		this.compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	public EtatSemaphore(final EtatSemaphore origine) {
		Objects.requireNonNull(origine, "fournir un état de semaphore origine");
		this.semaphore = origine.getSemaphore();
		this.valeurCompteur = origine.getValeurCompteur();
		this.fileAttente = new ArrayList<Processus>();
		for(Processus proc: origine.getFileAttente()) {
			fileAttente.add(proc);
		}
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	private List<Processus> getFileAttente() {
		return fileAttente;
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return semaphore != null && valeurCompteur >= 0 && compteurInstanciation > 0 && compteurInstance > 0;
	}
	
	public Semaphore getSemaphore() {
		return this.semaphore;
	}
	
	public int getValeurCompteur() {
		return this.valeurCompteur;
	}
	
	public void setValeurCompteur(int valeurCompteur) {
		this.valeurCompteur = valeurCompteur;
	}
	
	public void mettreProcessusEnAttente(Processus proc) {
		this.fileAttente.add(proc);
	}
	
	public Processus retirerProcessusEnAttente() {
		if(this.fileAttente.isEmpty()) {
			return null;
		}
		Processus proc = this.fileAttente.get(0);
		this.fileAttente.remove(0);
		return proc;
	}
	
	@Override
	public int compareTo(final EtatSemaphore other) {
		return semaphore.getNom().compareTo(other.getSemaphore().getNom());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((semaphore == null) ? 0 : semaphore.hashCode());
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
		if (!(obj instanceof EtatSemaphore)) {
			return false;
		}
		EtatSemaphore other = (EtatSemaphore) obj;
		if (semaphore == null) {
			if (other.getSemaphore() != null) {
				return false;
			}
		} else if (!semaphore.equals(other.getSemaphore())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EtatSem [#=" + compteurInstance + ", nom=" + semaphore.getNom() + ", valeurCompteur=" + this.getValeurCompteur() + "]";
	}

}
