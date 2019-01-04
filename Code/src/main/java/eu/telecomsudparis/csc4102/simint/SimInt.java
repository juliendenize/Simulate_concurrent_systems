package eu.telecomsudparis.csc4102.simint;

import java.util.HashMap;
import java.util.Map;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.PasDAjoutHorsEtatGlobalInitial;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;

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
	 * l'état global initial.
	 */
	private EtatGlobal etatGlobalInitial;
	/**
	 * indique que l'exécution a débuté.
	 */
	private boolean executionDebutee;

	/**
	 * construit la façade.
	 */
	public SimInt() {
		processus = new HashMap<>();
		etatGlobalInitial = new EtatGlobal();
		executionDebutee = false;
		assert invariant();
	}

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return etatGlobalInitial != null && processus != null;
	}

	/**
	 * pour les tests, de manière artificielle, marquer l'exécution comme débutée.
	 */
	public void mettreExecutionDebutee() {
		executionDebutee = true;
	}

	/**
	 * crée un processus exécutant un programme donné.
	 * 
	 * @param nom       le nom du processus.
	 * @throws ChaineDeCaracteresNullOuVide   nom null ou vide.
	 * @throws PasDAjoutHorsEtatGlobalInitial ajout non autorisé.
	 * @throws ProcessusDejaPresent           processus avec ce nom déjà présent.
	 * @throws ExecutionADejaDebute           exécution déjà débutée.
	 */
	public void creerProcessus(final String nom) throws ChaineDeCaracteresNullOuVide,
			PasDAjoutHorsEtatGlobalInitial, ProcessusDejaPresent, ExecutionADejaDebute {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom null ou vide non autorisé");
		}
		if (executionDebutee) {
			throw new ExecutionADejaDebute("pas d'ajout de processus après le début de l'exécution");
		}
		if (processus.containsKey(nom)) {
			throw new ProcessusDejaPresent("processus '" + nom + "' déjà présent dans le système");
		}
		Processus proc = new Processus(nom);
		processus.put(nom, proc);
		etatGlobalInitial.ajouteEtatProcessus(proc);
		assert invariant();
	}
}
