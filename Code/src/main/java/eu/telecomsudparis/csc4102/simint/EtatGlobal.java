package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Cette classe définit le concept d'état global.
 * 
 * @author Denis Conan
 */
public class EtatGlobal {
	/**
	 * l'ensemble des états des processus participant à la simulation.
	 */
	private SortedSet<EtatProcessus> etatsProcessus;

	/**
	 * l'ensemble des états des semaphores participant à la simulation.
	 */
	private SortedSet<EtatSemaphore> etatsSemaphores;

	/**
	 * true si c'est l'état initial.
	 */
	private boolean estEtatGlobalInitial;
	/**
	 * collection d'états atteignables à partir de cet état.
	 */
	private List<EtatGlobal> etatsGlobauxAtteignables;
	/**
	 * compteur d'instanciation.
	 */
	private static int compteurInstanciation = 0;
	/**
	 * compteur d'instance.
	 */
	private int compteurInstance;

	/**
	 * true si système interbloqué.
	 */
	private boolean situationInterbloquage;

	/**
	 * construit l'état global initial.
	 */
	public EtatGlobal() {
		etatsProcessus = new TreeSet<>();
		etatsSemaphores = new TreeSet<>();
		estEtatGlobalInitial = true;
		situationInterbloquage = false;
		etatsGlobauxAtteignables = new ArrayList<>();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	/**
	 * construit un état global comme une copie profonde d'un état global de départ.
	 * Cet état global n'est pas un état global initial.
	 * 
	 * <p>
	 * L'ensemble des états des processus est une collection d'états de processus
	 * obtenus par copie légère des états des processus de l'état de départ. Par
	 * copie légère, nous signifions que les programmes ne sont pas des copies.
	 * 
	 * @param origine
	 *            l'état global de départ.
	 */
	public EtatGlobal(final EtatGlobal origine) {
		Objects.requireNonNull(origine, "fournir un état global origine");
		estEtatGlobalInitial = false;
		this.etatsProcessus = origine.getEtatsProcessus().stream()
								   .map(etatProcessus -> new EtatProcessus(etatProcessus))
								   .collect(Collectors.toCollection(TreeSet::new));
		this.etatsSemaphores = origine.getEtatsSemaphores().stream()
				   .map(etatSemaphore -> new EtatSemaphore(etatSemaphore))
				   .collect(Collectors.toCollection(TreeSet::new));
		situationInterbloquage = origine.getSituationInterbloquage();
		etatsGlobauxAtteignables = new ArrayList<>();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	/**
	 * retourne les états sémaphores de l'état global.
	 * 
	 * @return les états sémaphores de l'état global.
	 */
	public SortedSet<EtatSemaphore> getEtatsSemaphores() {
		return this.etatsSemaphores;
	}
	
	/**
	 * retourne les états processus de l'état global.
	 * 
	 * @return les états processus de l'état global.
	 */
	public SortedSet<EtatProcessus> getEtatsProcessus() {
		return this.etatsProcessus;
	}
	
	/**
	 * retourne true si l'état global est en situation d'interbloquage.
	 * 
	 * @return true si l'état global est en situation d'interbloquage
	 */
	public boolean getSituationInterbloquage() {
		return situationInterbloquage;
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return etatsProcessus != null && etatsSemaphores != null && etatsGlobauxAtteignables != null
				&& compteurInstanciation > 0 && compteurInstance > 0 &&
				etatsSemaphores.stream()
							   .map(etatSemaphore -> etatSemaphore.getFileAttente())
							   //.collect(Collectors.toList())
							   .flatMap(List::stream)
							   .distinct()
							   .allMatch(processus -> this.chercherUnEtatProcessus(processus.getNom()) != null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etatsProcessus == null) ? 0 : etatsProcessus.hashCode());
		result = prime * result + ((etatsSemaphores == null) ? 0 : etatsSemaphores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EtatGlobal)) {
			return false;
		}
		EtatGlobal other = (EtatGlobal) obj;
		other.getEtatsProcessus()
		if (etatsProcessus == null) {
			if (other.etatsProcessus != null) {
				return false;
			}
		} else if (!etatsProcessus.equals(other.etatsProcessus)) {
			return false;
		}
		if (etatsSemaphores == null) {
			if (other.etatsSemaphores != null) {
				return false;
			}
		} else if (!etatsSemaphores.equals(other.etatsSemaphores)) {
			return false;
		}
		return true;
	}

	/**
	 * ajoute un état de processus à l'état global initial.
	 * 
	 * @param proc
	 *            le processus concerné.
	 */
	public void ajouterEtatProcessus(final Processus proc) {
		if (!estEtatGlobalInitial) {
			throw new IllegalStateException("ajout d'un processus non possible");
		}
		Objects.requireNonNull(proc, "proc ne peut pas être null");
		EtatProcessus etat = new EtatProcessus(proc);
		if (!etatsProcessus.add(etat)) {
			throw new IllegalArgumentException("état de processus '" + proc.getNom() + "' déjà présent dans l'état global");
		}
		assert invariant();
	}

	/**
	 * ajoute un état de semaphore à l'état global initial.
	 * 
	 * @param sem
	 * 			le semaphore
	 */
	public void ajouterEtatSemaphore(final Semaphore sem) {
		if (!estEtatGlobalInitial) {
			throw new IllegalStateException("ajout d'un processus non possible");
		}
		Objects.requireNonNull(sem, "proc ne peut pas être null");
		EtatSemaphore etat = new EtatSemaphore(sem);
		if (!etatsSemaphores.add(etat)) {
			throw new IllegalArgumentException("état de semaphore '" + sem.getNom() + "' déjà présent dans l'état global");
		}
		assert invariant();
	}

	/**
	 * cherche l'état d'un semaphore de nom donné.
	 * 
	 * @param nom
	 *            le nom du semaphore.
	 * @return l'état du semaphore trouvé.
	 */
	public EtatSemaphore chercherUnEtatSemaphore(final String nom) {
		for (EtatSemaphore etatSemaphore : etatsSemaphores) {
			if (etatSemaphore.getSemaphore().getNom().equals(nom)) {
				return etatSemaphore;
			}
		}
		throw new IllegalArgumentException("pas d'état pour le semaphore '" + nom + "'");
	}

	/**
	 * cherche l'état d'un processus de nom donné.
	 * 
	 * @param nom
	 *            le nom du processus.
	 * @return l'état du processus trouvé.
	 */
	public EtatProcessus chercherUnEtatProcessus(final String nom) {
		for (EtatProcessus etatProcessus : etatsProcessus) {
			if (etatProcessus.getProcessus().getNom().equals(nom)) {
				return etatProcessus;
			}
		}
		throw new IllegalArgumentException("pas d'état pour le processus '" + nom + "'");
	}
	
	
	/**
	 * Avance l'éxecution du processus dont le nom est donné en paramètre.
	 * Cet avancement se fait dans une copie de cet état global et si cela débloque un processus, ce processus est
	 * à son tour exécuter. 
	 * @param nomProcessus
	 * 			le nom du processus à exécuter.
	 */
	public void avancerExecution(final String nomProcessus) {
		if (nomProcessus == null || nomProcessus.equals("")) {
			throw new IllegalArgumentException("nom null ou vide non autorisé");
		}
		EtatProcessus etatProc = chercherUnEtatProcessus(nomProcessus);
		Instruction instruction = etatProc.chercherInstruction();
		EtatSemaphore etatSem = chercherUnEtatSemaphore(instruction.getSemaphore().getNom());
		boolean instructionExecutee = false;
		int valeurCompteur = etatSem.getValeurCompteur();

		if (instruction.getTypeInstruction().equals(TypeInstruction.P)) {
			if (valeurCompteur > 0) {
				etatSem.setValeurCompteur(valeurCompteur - 1);
				instructionExecutee = true;
			} else {
				etatSem.mettreProcessusEnAttente(etatProc.getProcessus());
				etatProc.setEtat(Etat.bloque);
			}
		} else {
			instructionExecutee = true;
			etatSem.setValeurCompteur(valeurCompteur + 1);
			Processus procRetire = etatSem.libererProcessus();
			if (procRetire != null) {
				this.chercherUnEtatProcessus(procRetire.getNom()).setEtat(Etat.vivant);
				avancerExecution(procRetire.getNom());
			}
		}

		if (instructionExecutee) {
			etatProc.avancerExecution();
		}
	}
	
	/**
	 * Affiche les états processus de l'état global.
	 */
	public void afficherEtatsProcessus() {
		for (EtatProcessus etatProc : etatsProcessus) {
			System.out.println(etatProc);
		}
	}
	
	/**
	 * Affiche les états sémaphores de l'état global.
	 */
	public void afficherEtatsSemaphores() {
		for (EtatSemaphore etatSem : etatsSemaphores) {
			System.out.println(etatSem);
		}
	}

	@Override
	public String toString() {
		return "EtatGlobal [#=" + compteurInstance + ", proc=" + etatsProcessus + ", estInitial=" + estEtatGlobalInitial
				+ ", etatsAtteignables=" + etatsGlobauxAtteignables + "]";
	}
	
	/**
	 * Etablit si le système est en interbloquage en stockant dans le booléen situationInterbloquage le résultat.
	 */
	public void etablirSystemeEnInterbloquage() {
		if (!this.situationInterbloquage) {
			if(etatsProcessus.stream().allMatch(etatProcessus -> 
									  				etatProcessus.getEtat().equals(Etat.bloque) ||
													etatProcessus.getEtat().equals(Etat.termine))) {
				this.situationInterbloquage = etatsProcessus.stream()
															.anyMatch(etatProcessus -> etatProcessus.getEtat().equals(Etat.bloque));
			}
		}
		assert invariant();
	}

	
	/**
	 * retourne le compteur d'instanciation.
	 * 
	 * @return le compteur d'instanciation.
	 */
	public static int getCompteurInstanciation() {
		return compteurInstanciation;
	}
	
	/**
	 * retourne le compteur d'instance.
	 * 
	 * @return le compteur d'instance.
	 */
	public int getCompteurInstance() {
		return compteurInstance;
	}
}
