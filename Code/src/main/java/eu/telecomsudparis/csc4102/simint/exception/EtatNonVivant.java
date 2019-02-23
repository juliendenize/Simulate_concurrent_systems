package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer que l'état processus ne
 * peut pas avancer car l'état du processus n'est pas vivant.
*/
public class EtatNonVivant extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public EtatNonVivant(final String message) {
		super(message);
	}
}
