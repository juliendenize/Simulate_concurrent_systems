package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une valeur initiale inférieur à
 * 0 pour un sémaphore.
 * 
 * @author Julien Denize - Pierre Chaffardon
 * 
 */
public class ValeurInitialeHorsBorne extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message
	 *            le message de l'exception.
	 */
	public ValeurInitialeHorsBorne(final String message) {
		super(message);
	}
}
