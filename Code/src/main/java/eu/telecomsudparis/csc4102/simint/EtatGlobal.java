package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.EtatNonVivant;
import eu.telecomsudparis.csc4102.simint.exception.InstructionNonExistante;
import eu.telecomsudparis.csc4102.simint.exception.PasDAjoutHorsEtatGlobalInitial;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.SemaphoreDejaPresent;

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
	 * true si système interbloqué
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
	 * @param origine l'état global de départ.
	 */
	public EtatGlobal(final EtatGlobal origine) {
		Objects.requireNonNull(origine, "fournir un état global origine");
		estEtatGlobalInitial = false;
		etatsProcessus = new TreeSet<>();
		etatsSemaphores = new TreeSet<>();
		for (EtatProcessus etatProcessus : origine.getEtatsProcessus()) {
			etatsProcessus.add(new EtatProcessus(etatProcessus));
		}
		for (EtatSemaphore etatSemaphore : origine.getEtatsSemaphores()) {
			etatsSemaphores.add(new EtatSemaphore(etatSemaphore));
		}
		situationInterbloquage = origine.getSituationInterbloquage();
		etatsGlobauxAtteignables = new ArrayList<>();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}
	
	public SortedSet<EtatSemaphore> getEtatsSemaphores() {
		return this.etatsSemaphores;
	}

	public SortedSet<EtatProcessus> getEtatsProcessus() {
		return this.etatsProcessus;
	}

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
				&& compteurInstanciation > 0 && compteurInstance > 0;
		}

	/**
	 * ajoute un état de processus à l'état global initial.
	 * 
	 * @param proc le processus concerné.
	 * @throws PasDAjoutHorsEtatGlobalInitial ajout non autorisé.
	 * @throws ChaineDeCaracteresNullOuVide   identifiant null ou vide.
	 * @throws ProcessusDejaPresent           processus avec cet identifiant déjà
	 *                                        présent.
	 */
	public void ajouterEtatProcessus(final Processus proc)
			throws PasDAjoutHorsEtatGlobalInitial, ChaineDeCaracteresNullOuVide, ProcessusDejaPresent {
		if (!estEtatGlobalInitial) {
			throw new PasDAjoutHorsEtatGlobalInitial("ajout d'un processus non possible");
		}
		Objects.requireNonNull(proc, "proc ne peut pas être null");
		EtatProcessus etat = new EtatProcessus(proc);
		if (!etatsProcessus.add(etat)) {
			throw new ProcessusDejaPresent("état de processus '" + proc.getNom() + "' déjà présent dans l'état global");
		}
		assert invariant();
	}
	
	/**
	 * ajoute un état de semaphore à l'état global initial.
	 * 
	 * @param proc le processus concerné.
	 * @throws PasDAjoutHorsEtatGlobalInitial ajout non autorisé.
	 * @throws ChaineDeCaracteresNullOuVide   identifiant null ou vide.
	 * @throws SemaphoreDejaPresent           semaphore avec cet identifiant déjà
	 *                                        présent.
	 */
	public void ajouterEtatSemaphore(final Semaphore sem)
			throws PasDAjoutHorsEtatGlobalInitial, ChaineDeCaracteresNullOuVide, SemaphoreDejaPresent {
		if (!estEtatGlobalInitial) {
			throw new PasDAjoutHorsEtatGlobalInitial("ajout d'un processus non possible");
		}
		Objects.requireNonNull(sem, "proc ne peut pas être null");
		EtatSemaphore etat = new EtatSemaphore(sem);
		if (!etatsSemaphores.add(etat)) {
			throw new SemaphoreDejaPresent("état de semaphore '" + sem.getNom() + "' déjà présent dans l'état global");
		}
		assert invariant();
	}
	
	/**
	 * cherche l'état d'un semaphore de nom donné.
	 * 
	 * @param nom le nom du semaphore.
	 * @return l'état du semaphore trouvé.
	 */
	public EtatSemaphore chercherUnEtatSemaphore(final String nom) {
		for (EtatSemaphore etatSemaphore : etatsSemaphores) {
			if (etatSemaphore.getSemaphore().getNom().equals(nom)) {
				return etatSemaphore;
			}
		}
		throw new IllegalStateException("pas d'état pour le semaphore '" + nom + "'");
	}


	/**
	 * cherche l'état d'un processus de nom donné.
	 * 
	 * @param nom le nom du processus.
	 * @return l'état du processus trouvé.
	 */
	public EtatProcessus chercherUnEtatProcessus(final String nom) {
		for (EtatProcessus etatProcessus : etatsProcessus) {
			if (etatProcessus.getProcessus().getNom().equals(nom)) {
				return etatProcessus;
			}
		}
		throw new IllegalStateException("pas d'état pour le processus '" + nom + "'");
	}
	
	public void avancerExecution(final String nomProcessus) throws InstructionNonExistante, EtatNonVivant, ChaineDeCaracteresNullOuVide {
		if(nomProcessus == null || nomProcessus.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
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
			Processus procRetire = etatSem.libérerProcessus();
			if(procRetire != null) {
				this.chercherUnEtatProcessus(procRetire.getNom()).setEtat(Etat.vivant);
				avancerExecution(procRetire.getNom());
			}
		}
		
		if(instructionExecutee) {
			etatProc.avancerExécution();
		}
	}

	public void afficherEtatsProcessus() {
		for(EtatProcessus etatProc: etatsProcessus) {
			System.out.println(etatProc);
		}
	}
	
	public void afficherEtatsSemaphores() {
		for(EtatSemaphore etatSem: etatsSemaphores) {
			System.out.println(etatSem);
		}
	}
	
	@Override
	public String toString() {
		return "EtatGlobal [#=" + compteurInstance + ", proc=" + etatsProcessus + ", estInitial=" + estEtatGlobalInitial
				+ ", etatsAtteignables=" + etatsGlobauxAtteignables + "]";
	}

	public void etablirSystemeEnInterbloquage() {
		if(!this.situationInterbloquage) {
			boolean existeProcBloque = false;
			for (EtatProcessus etatProcessus : etatsProcessus) {
				if(etatProcessus.getEtat().equals(Etat.vivant)) return;
				else if (etatProcessus.getEtat().equals(Etat.bloque)) {
					existeProcBloque = true;
				}
			}
			this.situationInterbloquage = existeProcBloque;
		}
		assert invariant();
	}

	public boolean getSystemeInterbloquage() {
		return this.situationInterbloquage;
	}

	public static int getCompteurInstanciation() {
		return compteurInstanciation;
	}

	public int getCompteurInstance() {
		return compteurInstance;
	}
}
