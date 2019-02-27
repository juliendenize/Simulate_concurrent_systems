package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cette classe définit le concept d'un état sémphore.
 * 
 * @author julien
 */
public class EtatSemaphore implements Comparable<EtatSemaphore> {

	/**
	 * Valeur du compteur du semaphore.
	 */
	private int valeurCompteur;

	/**
	 * nombre d'états sémaphore instanciés.
	 */
	private static int compteurInstanciation = 0;

	/**
	 * numéro d'instance.
	 */
	private int compteurInstance;

	/**
	 * le semaphore.
	 */
	private Semaphore semaphore;

	/**
	 * Collection de processus mis en attente.
	 */
	private List<Processus> fileAttente;

	/**
	 * Construcit un état sempahore.
	 * 
	 * @param semaphore
	 * 		le semaphore concerné.
	 */
	public EtatSemaphore(final Semaphore semaphore) {
		this.valeurCompteur = semaphore.getValeurInitiale();
		this.semaphore = semaphore;
		this.fileAttente = new ArrayList<Processus>();
		compteurInstanciation++;
		this.compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	/**
	 * Construit un état semaphore par copie.
	 * 
	 * @param origine
	 * 			l'état semaphore copié.
	 */
	public EtatSemaphore(final EtatSemaphore origine) {
		Objects.requireNonNull(origine, "fournir un état de semaphore origine");
		this.semaphore = origine.getSemaphore();
		this.valeurCompteur = origine.getValeurCompteur();
		this.fileAttente = new ArrayList<Processus>();
		for (Processus proc : origine.getFileAttente()) {
			fileAttente.add(proc);
		}
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	/**
	 * Retourne la file d'attente.
	 * 
	 * @return la file d'attente.
	 */
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
	
	/**
	 * Retourne le semaphore.
	 * 
	 * @return le semaphore.
	 */
	public Semaphore getSemaphore() {
		return this.semaphore;
	}
	
	/**
	 * Retourne la valeur compteur.
	 * 
	 * @return la valeur compteur.
	 */
	public int getValeurCompteur() {
		return this.valeurCompteur;
	}
	
	/**
	 * Met à jour la valeur compteur.
	 * 
	 * @param valeurCompteur
	 * 		La nouvelle valeur compteur.
	 */
	public void setValeurCompteur(final int valeurCompteur) {
		this.valeurCompteur = valeurCompteur;
		assert invariant();
	}
	
	/**
	 * Ajoute un processus à la file d'attente.
	 * 
	 * @param proc
	 * 			le processus concerné.
	 */
	public void mettreProcessusEnAttente(final Processus proc) {
		this.fileAttente.add(proc);
		assert invariant();
	}
	
	/**
	 * Libère un processus en attente.
	 * 
	 * @return null si aucun processus était en attente, le processus sinon.
	 */
	public Processus libererProcessus() {
		Processus proc = null;
		if (!this.fileAttente.isEmpty()) {
			proc = this.fileAttente.get(0);
			this.fileAttente.remove(0);
		}
		assert invariant();
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
			if (other.semaphore != null) {
				return false;
			}
		} else if (!semaphore.equals(other.semaphore)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EtatSemaphore [valeurCompteur=" + valeurCompteur + ", compteurInstance=" + compteurInstance
				+ ", semaphore=" + semaphore + ", fileAttente=" + fileAttente + "]";
	}
	
	/**
	 * Retourne le compteur instanciation.
	 * 
	 * @return le compteur instanciation.
	 */
	public static int getCompteurInstanciation() {
		return compteurInstanciation;
	}
	
	/**
	 * Retourne le compteur insance.
	 * 
	 * @return le compteur instance.
	 */
	public int getCompteurInstance() {
		return compteurInstance;
	}

}
