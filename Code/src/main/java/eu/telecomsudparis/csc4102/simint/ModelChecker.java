package eu.telecomsudparis.csc4102.simint;

/**
 * Interface pour implémenter des algorithmes de ModelChecker
 * 
 * @author julien
 *
 */
public interface ModelChecker {
	public EtatGlobal validerSysteme(SimInt simint);
}
