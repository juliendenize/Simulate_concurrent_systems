package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonVivant;
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
	 * @throws ProcessusDejaPresent
	 *             processus avec ce nom déjà présent.
	 * @throws ExecutionADejaDebute
	 *             exécution déjà débutée.
	 * @throws ProgrammeNonExistant
	 *             le programme avec le nom nomProg n'existe pas.
	 */
	public void creerProcessus(final String nom, final String nomProg) throws ChaineDeCaracteresNullOuVide,
		ProcessusDejaPresent, ExecutionADejaDebute, ProgrammeNonExistant {
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
	
	/**
	 * créé un programme qui est ensuite ajouté à la collection des programmes.
	 * @param nom
	 * 			nom du programme.
	 * @throws ChaineDeCaracteresNullOuVide
	 * 			le nom du programme doit être non null et non vide.			
	 * @throws ExecutionADejaDebute
	 * 			l'exécution ne doit pas avoir débutée.
	 * @throws ProgrammeDejaPresent
	 * 			le programme existe déjà.
	 */
	public void creerProgramme(final String nom) throws ChaineDeCaracteresNullOuVide, ExecutionADejaDebute, 
		ProgrammeDejaPresent {
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
	
	/**
	 * créé un semaphore qui est ensuite ajouté à la collection des semaphore, et un état sémaphore ajouté à l'état global intitiale.
	 * @param nom
	 * 			nom du semaphore.
	 * @param valeurInitiale
	 * 			valeur initiale du semaphore.
	 * @throws ChaineDeCaracteresNullOuVide
	 * 			le nom doit être non null ou non vide.
	 * @throws ExecutionADejaDebute
	 * 			l'exécution ne doit pas avoir débutée.
	 * @throws SemaphoreDejaPresent
	 * 			le sémaphore avec ce nom existe déjà.
	 * @throws ValeurInitialeHorsBorne
	 * 			la valeur initiale doit être supérieure ou égale à 0.
	 */
	public void creerSemaphore(final String nom, final int valeurInitiale) throws ChaineDeCaracteresNullOuVide,
			ExecutionADejaDebute, SemaphoreDejaPresent, ValeurInitialeHorsBorne {
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
	
	/**
	 * avance l'exécution du processus identifié par son nom.
	 * @param nom
	 * 			nom du processus
	 * @throws ExecutionNonDebutee
	 * 			l'exécution doit avoir débutée
	 * @throws ChaineDeCaracteresNullOuVide
	 * 			le nom doit être non null ou non vide
	 * @throws ProcessusNonExistant
	 * 			le processus doit exister
	 * @throws ProcessusNonVivant
	 * 			le processus doit être vivant.
	 */
	public void avancerExecution(final String nom) throws ExecutionNonDebutee, ChaineDeCaracteresNullOuVide,
			ProcessusNonExistant, ProcessusNonVivant {
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
		if (etatProc.getEtat() != Etat.vivant) {
			throw new ProcessusNonVivant("processus '" + nom + "' est déjà terminé ou bloqué");
		}
		this.dernierEtatGlobal = new EtatGlobal(this.dernierEtatGlobal);
		this.dernierEtatGlobal.avancerExecution(nom);
		this.etablirSystemeEnInterbloquage();
	}
	
	/**
	 * demande au dernier état global de regarder s'il est interbloqué et renvoie un booleen suivant si c'est le cas ou pas.
	 * @return true si le dernier état global est interbloqué, false sinon.
	 */
	public EtatExecution etablirSystemeEnInterbloquage() {
		dernierEtatGlobal.etablirSystemeEnInterbloquage();
		return dernierEtatGlobal.getEtatExecution();
	}
	
	/**
	 * ajouter une instruction à un programme.
	 * @param nomProg
	 * 			le nom du programme.
	 * @param nomSem
	 * 			le nom du semaphore manipulé par l'instruction.
	 * @param type
	 * 			le type d'instruction.
	 * @throws ChaineDeCaracteresNullOuVide
	 * 			les noms ne doivent pas être nul ou vide.
	 * @throws ExecutionADejaDebute
	 * 			l'exécution ne doit pas avoir commencé.
	 * @throws ProgrammeNonExistant
	 * 			le programme doit exister.
	 * @throws SemaphoreNonExistant
	 * 			le sémaphore doit exister.
	 */
	public void ajouterInstruction(final String nomProg, final String nomSem, final TypeInstruction type)
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
	
	/**
	 * Valide le système.
	 *  
	 * @throws ExecutionADejaDebute
	 * 			L'éxecution ne doit pas avoir débutée.
	 */
	public void validerSysteme() throws ExecutionADejaDebute {
		if (this.executionDebutee) {
			throw new ExecutionADejaDebute("L'exécution a déjà débutée.");
		}
		if (this.processus.isEmpty()) {
			System.out.println("Le système ne contient pas de processus et est donc par conséquent valide.");
		}
		long startTime = System.nanoTime();
		ArrayList<EtatGlobal> etatsGlobauxAtteignables = new ArrayList<>();
		Optional<EtatGlobal> etatGlobalInterbloque;
		this.debuterExecution();
		etatsGlobauxAtteignables.add(etatGlobalInitial);
		
		for (int i = 0; i < etatsGlobauxAtteignables.size(); i++) {
			this.dernierEtatGlobal = etatsGlobauxAtteignables.get(i);
			if (this.dernierEtatGlobal.getEtatExecution().equals(EtatExecution.enCours)) {
				for (EtatProcessus etatProc: this.dernierEtatGlobal.getEtatsProcessus()) {
					if (etatProc.getEtat().equals(Etat.vivant)) {
						try {
							this.avancerExecution(etatProc.getProcessus().getNom());
						} catch (ExecutionNonDebutee | ChaineDeCaracteresNullOuVide | ProcessusNonExistant
								| ProcessusNonVivant e) {
							e.printStackTrace();
						}
						this.dernierEtatGlobal = etatsGlobauxAtteignables.get(i);
					}
				}
				for (EtatGlobal etatGlobal: this.dernierEtatGlobal.getEtatsGlobauxAtteignables()) {
					if (!etatsGlobauxAtteignables.contains(etatGlobal)) {
						etatsGlobauxAtteignables.add(etatGlobal);
					}
				}
			}
		}

		etatGlobalInterbloque = etatsGlobauxAtteignables.stream()
								.filter(etatGlobal -> etatGlobal.getEtatExecution().equals(EtatExecution.interbloque)).findAny();
		final int oneMillion = 1000000;
		long tempsExecution = (System.nanoTime() - startTime) / oneMillion;
		if (!etatGlobalInterbloque.isPresent()) {
			System.out.println("Validation du système = ok: " + EtatGlobal.getCompteurInstanciation() + " états globaux différents ont été générés en" + tempsExecution 
								+ "ms. Pas d'interbloquage trouvé.");
		} else {
			System.out.println("Validation du système: " + etatsGlobauxAtteignables.size() + " états globaux différents ont été générés en " + tempsExecution 
								+ "ms.");
			System.out.println("interbloquage trouvé dans état: " + etatGlobalInterbloque.get());
			this.chercherChemin(etatGlobalInterbloque.get());
		}
	}
	
	/**
	 * Cherche le chemin entre l'état global donné et l'état global initial.
	 * 
	 * @param etatGlobal
	 * 			L'état global dont on doit chercher le chemin.
	 */
	public void chercherChemin(final EtatGlobal etatGlobal) {
		Objects.requireNonNull(etatGlobal, "L'état global ne doit pas être nul");
		
		EtatGlobal iterator = etatGlobal;
		while (iterator != this.etatGlobalInitial) {
			System.out.println(iterator.chaineDeCaracteres());
			iterator = iterator.getEtatGlobalPrecedent();
		}
		System.out.println(iterator.chaineDeCaracteres());
	}
	
	/**
	 * affiche les états processus du dernier état global.
	 */
	public void afficherEtatsProcessus() {
		this.dernierEtatGlobal.afficherEtatsProcessus();
	}
	
	/**
	 * affiche les états sémaphores du dernier état global.
	 */
	public void afficherEtatsSemaphores() {
		this.dernierEtatGlobal.afficherEtatsSemaphores();
	}
	
	/**
	 * renvoie le processus identifié par son nom s'il existe.
	 * @param nomProc
	 * 			le nom du processus
	 * @return le processus s'il existe, null sinon.
	 */
	public Processus chercherProcessus(final String nomProc) {
		return processus.get(nomProc);
	}
	
	/**
	 * renvoie le semaphore identifié par son nom s'il existe.
	 * @param nomSem
	 * 			le nom du semaphore.
	 * @return le semaphore s'il existe, null sinon.
	 */
	public Semaphore chercherSemaphore(final String nomSem) {
		return semaphores.get(nomSem);

	}
	
	/**
	 * renvoie le programme identifié par son nom s'il existe.
	 * @param nomProg
	 * 			le nom du programme.
	 * @return le programme s'il existe, null sinon.
	 */
	public Programme chercherProgramme(final String nomProg) {
		return programmes.get(nomProg);
	}
	
	/**
	 * renvoie le dernier état global.
	 * @return le dernier état global.
	 */
	public EtatGlobal getDernierEtatGlobal() {
		return dernierEtatGlobal;
	}
}
