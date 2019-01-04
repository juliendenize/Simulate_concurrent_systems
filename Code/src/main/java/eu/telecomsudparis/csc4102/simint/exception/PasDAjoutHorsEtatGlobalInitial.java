package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour la tentative d'ajout d'état de
 * processus ou de sémaphore hors état global initial.
 * 
 * @author Denis Conan
 */
public class PasDAjoutHorsEtatGlobalInitial extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public PasDAjoutHorsEtatGlobalInitial(final String message) {
		super(message);
	}
}
