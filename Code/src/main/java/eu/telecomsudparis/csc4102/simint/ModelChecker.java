package eu.telecomsudparis.csc4102.simint;

import java.util.Optional;

/**
 * Interface pour implémenter des algorithmes de ModelChecker.
 * 
 * @author julien
 *
 */
public interface ModelChecker {
	
	/**
	 * Valide le système de la façade à partir d'un état donné.
	 * 
	 * @param simint
	 * 			le système à valider.
	 * @param etatGlobalInitial
	 * 			l'état global initiale pour la génération des états globaux.
	 * @return un état bloqué interbloqué s'il existe.
	 */
	Optional<EtatGlobal> validerSysteme(SimInt simint, EtatGlobal etatGlobalInitial);
}
