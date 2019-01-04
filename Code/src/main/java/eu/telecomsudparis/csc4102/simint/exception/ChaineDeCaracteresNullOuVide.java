package eu.telecomsudparis.csc4102.simint.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer qu'une chaîne de
 * caractères est par erreur soit null soit vide.
 * 
 * @author Denis Conan
 */
public class ChaineDeCaracteresNullOuVide extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public ChaineDeCaracteresNullOuVide(final String message) {
		super(message);
	}
}
