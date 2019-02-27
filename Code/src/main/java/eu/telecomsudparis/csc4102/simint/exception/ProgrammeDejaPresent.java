package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un programme déjà présent dans
 * le système.
 */
public class ProgrammeDejaPresent extends OperationImpossible {
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
	public ProgrammeDejaPresent(final String message) {
		super(message);
	}
}
