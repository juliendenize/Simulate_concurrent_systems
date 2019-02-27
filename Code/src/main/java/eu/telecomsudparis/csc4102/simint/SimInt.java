package eu.telecomsudparis.csc4102.simint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.PasDAjoutHorsEtatGlobalInitial;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaTermine;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ProgrammeDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.ProgrammeNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.SemaphoreDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.SemaphoreNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ValeurInitialeHorsBorne;

/**
 * Cette classe définit la façade du système.
 * 
 * @author Denis Conan
 */
public class SimInt {
	/**
	 * les processus du système.
	 */
	private Map<String, Processus> processus;

	/**
	 * Les semaphores du système.
	 */
	private Map<String, Semaphore> semaphores;

	/**
	 * Les programmes du système.
	 */
	private Map<String, Programme> programmes;
	/**
	 * l'état global initial.
	 */
	private EtatGlobal etatGlobalInitial;

	/**
	 * le dernier etat global atteint.
	 */
	private EtatGlobal dernierEtatGlobal;

	/**
	 * indique que l'exécution a débuté.
	 */
	private boolean executionDebutee;

	/**
	 * construit la façade.
	 */
	public SimInt() {
		processus = new HashMap<>();
		programmes = new HashMap<>();
		semaphores = new HashMap<>();
		etatGlobalInitial = new EtatGlobal();
		dernierEtatGlobal = etatGlobalInitial;
		executionDebutee = false;
		assert invariant();
	}

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return etatGlobalInitial != null && dernierEtatGlobal != null && programmes != null && semaphores != null
				&& processus != null;
	}

	/**
	 * pour les tests, de manière artificielle, marquer l'exécution comme débutée.
	 */
	public void debuterExecution() {
		executionDebutee = true;
	}

	/**
	 * crée un processus exécutant un programme donné.
	 * 
	 * @param nom
	 *            le nom du processus.
	 * @param nomProg
	 *            le nom du programme
	 * @throws ChaineDeCaracteresNullOuVide
	 *             nom null ou vide.
	 * @throws PasDAjoutHorsEtatGlobalInitial
	 *             ajout non autorisé.
	 * @throws ProcessusDejaPresent
	 *             processus avec ce nom déjà présent.
	 * @throws ExecutionADejaDebute
	 *             exécution déjà débutée.
	 * @throws ProgrammeNonExistant
	 *             le programme avec le nom nomProg n'existe pas.
	 */
	public void creerProcessus(final String nom, final String nomProg) throws ChaineDeCaracteresNullOuVide,
			PasDAjoutHorsEtatGlobalInitial, ProcessusDejaPresent, ExecutionADejaDebute, ProgrammeNonExistant {
		if (nom == null || nom.equals("") || nomProg == null || nomProg.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (executionDebutee) {
			throw new ExecutionADejaDebute("pas d'ajout de processus après le début de l'exécution");
		}
		if (processus.containsKey(nom)) {
			throw new ProcessusDejaPresent("processus '" + nom + "' déjà présent dans le système");
		}
		if (!programmes.containsKey(nomProg)) {
			throw new ProgrammeNonExistant("programme '" + nomProg + "' non présent dans le système");
		}
		Programme prog = programmes.get(nomProg);
		Processus proc = new Processus(nom, prog);
		processus.put(nom, proc);
		etatGlobalInitial.ajouterEtatProcessus(proc);
		assert invariant();
	}

	public void creerProgramme(final String nom) throws ChaineDeCaracteresNullOuVide, PasDAjoutHorsEtatGlobalInitial,
			ExecutionADejaDebute, ProgrammeDejaPresent {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (executionDebutee) {
			throw new ExecutionADejaDebute("pas d'ajout de programme après le début de l'exécution");
		}
		if (programmes.containsKey(nom)) {
			throw new ProgrammeDejaPresent("programme '" + nom + "' déjà présent dans le système");
		}
		Programme prog = new Programme(nom);
		programmes.put(nom, prog);
	}

	public void creerSemaphore(final String nom, final int valeurInitiale) throws ChaineDeCaracteresNullOuVide,
			PasDAjoutHorsEtatGlobalInitial, ExecutionADejaDebute, SemaphoreDejaPresent, ValeurInitialeHorsBorne {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (executionDebutee) {
			throw new ExecutionADejaDebute("pas d'ajout de semaphore après le début de l'exécution");
		}
		if (semaphores.containsKey(nom)) {
			throw new SemaphoreDejaPresent("semaphore '" + nom + "' déjà présent dans le système");
		}
		if (valeurInitiale < 0) {
			throw new ValeurInitialeHorsBorne("La valeur initiale d'un sémaphore doit être supérieure ou égale à 0");
		}
		Semaphore sem = new Semaphore(nom, valeurInitiale);
		semaphores.put(nom, sem);
		etatGlobalInitial.ajouterEtatSemaphore(sem);
	}

	public void avancerExecution(String nom) throws ExecutionNonDebutee, ChaineDeCaracteresNullOuVide,
			ProcessusNonExistant, ProcessusDejaTermine {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (!executionDebutee) {
			throw new ExecutionNonDebutee("pas d'avancement de processus si l'exécution n'a pas débutée");
		}
		if (!processus.containsKey(nom)) {
			throw new ProcessusNonExistant("processus '" + nom + "' n'existe pas");
		}
		EtatProcessus etatProc = this.dernierEtatGlobal.chercherUnEtatProcessus(nom);
		if (etatProc.getEtat() == Etat.termine) {
			throw new ProcessusDejaTermine("processus '" + nom + "' est déjà terminé");
		}
		this.dernierEtatGlobal = new EtatGlobal(this.dernierEtatGlobal);
		this.dernierEtatGlobal.avancerExecution(nom);
	}

	public boolean etablirSystemeEnInterbloquage() {
		dernierEtatGlobal.etablirSystemeEnInterbloquage();
		return dernierEtatGlobal.getSituationInterbloquage();
	}

	public void ajouterInstruction(String nomProg, String nomSem, TypeInstruction type)
			throws ChaineDeCaracteresNullOuVide, ExecutionADejaDebute, ProgrammeNonExistant, SemaphoreNonExistant {
		Objects.requireNonNull(type, "Le type d'instruction doit être différent de null");
		if (nomProg == null || nomProg.equals("") || nomSem == null || nomSem.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (executionDebutee) {
			throw new ExecutionADejaDebute("pas d'ajout d'instruction après le début de l'exécution");
		}
		if (!programmes.containsKey(nomProg)) {
			throw new ProgrammeNonExistant("programme '" + nomProg + "' non présent dans le système");
		}
		if (!semaphores.containsKey(nomSem)) {
			throw new SemaphoreNonExistant("semaphore '" + nomSem + "' non présent dans le système");
		}
		Programme prog = programmes.get(nomProg);
		Semaphore sem = semaphores.get(nomSem);

		prog.ajouterInstruction(new Instruction(type, sem));

	}

	public void afficherEtatsProcessus() {
		this.dernierEtatGlobal.afficherEtatsProcessus();
	}

	public void afficherEtatsSemaphores() {
		this.dernierEtatGlobal.afficherEtatsSemaphores();
	}

	public Processus chercherProcessus(String nomProc) {
		return processus.get(nomProc);
	}

	public Semaphore chercherSemaphore(String nomSem) {
		return semaphores.get(nomSem);

	}

	public Programme chercherProgramme(String nomProg) {
		return programmes.get(nomProg);
	}

	public EtatGlobal getDernierEtatGlobal() {
		return dernierEtatGlobal;
	}
}
