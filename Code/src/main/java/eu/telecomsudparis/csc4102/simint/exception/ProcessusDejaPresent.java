package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un processus déjà présent dans
 * le système.
 * 
 * @author Denis Conan
 */
public class ProcessusDejaPresent extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public ProcessusDejaPresent(final String message) {
		super(message);
	}
}
