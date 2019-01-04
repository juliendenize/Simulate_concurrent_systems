package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.PasDAjoutHorsEtatGlobalInitial;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;

/**
 * Cette classe définit le concept d'état global.
 * 
 * @author Denis Conan
 */
public class EtatGlobal {
	/**
	 * l'ensemble des états des processus participant à la simulation.
	 */
	private SortedSet<EtatProcessus> etatsProcessus;
	/**
	 * true si c'est l'état initial.
	 */
	private boolean estEtatGlobalInitial;
	/**
	 * collection d'états atteignables à partir de cet état.
	 */
	private List<EtatGlobal> etatsGlobauxAtteignables;
	/**
	 * compteur d'instanciation.
	 */
	private static int compteurInstanciation = 0;
	/**
	 * compteur d'instance.
	 */
	private int compteurInstance;

	/**
	 * construit l'état global initial.
	 */
	public EtatGlobal() {
		etatsProcessus = new TreeSet<>();
		estEtatGlobalInitial = true;
		etatsGlobauxAtteignables = new ArrayList<>();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	/**
	 * construit un état global comme une copie profonde d'un état global de départ.
	 * Cet état global n'est pas un état global initial.
	 * 
	 * <p>
	 * L'ensemble des états des processus est une collection d'états de processus
	 * obtenus par copie légère des états des processus de l'état de départ. Par
	 * copie légère, nous signifions que les programmes ne sont pas des copies.
	 * 
	 * @param origine l'état global de départ.
	 */
	public EtatGlobal(final EtatGlobal origine) {
		Objects.requireNonNull(origine, "fournir un état global origine");
		estEtatGlobalInitial = false;
		etatsProcessus = new TreeSet<>();
		for (EtatProcessus etatProcessus : etatsProcessus) {
			etatsProcessus.add(new EtatProcessus(etatProcessus));
		}
		etatsGlobauxAtteignables = new ArrayList<>();
		compteurInstanciation++;
		compteurInstance = compteurInstanciation;
		assert invariant();
	}

	/**
	 * teste l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return etatsProcessus != null && etatsGlobauxAtteignables != null;
	}

	/**
	 * ajoute un état de processus à l'état global initial.
	 * 
	 * @param proc le processus concerné.
	 * @throws PasDAjoutHorsEtatGlobalInitial ajout non autorisé.
	 * @throws ChaineDeCaracteresNullOuVide   identifiant null ou vide.
	 * @throws ProcessusDejaPresent           processus avec cet identifiant déjà
	 *                                        présent.
	 */
	public void ajouteEtatProcessus(final Processus proc)
			throws PasDAjoutHorsEtatGlobalInitial, ChaineDeCaracteresNullOuVide, ProcessusDejaPresent {
		if (!estEtatGlobalInitial) {
			throw new PasDAjoutHorsEtatGlobalInitial("ajout d'un processus non possible");
		}
		Objects.requireNonNull(proc, "proc ne peut pas être null");
		EtatProcessus etat = new EtatProcessus(proc);
		if (!etatsProcessus.add(etat)) {
			throw new ProcessusDejaPresent("état de processus '" + proc.getNom() + "' déjà présent dans l'état global");
		}
		assert invariant();
	}

	/**
	 * cherche l'état d'un processus de nom donné.
	 * 
	 * @param nom le nom du processus.
	 * @return l'état du processus trouvé.
	 */
	public EtatProcessus chercherUnEtatProcessus(final String nom) {
		for (EtatProcessus etatProcessus : etatsProcessus) {
			if (etatProcessus.getProcessus().getNom().equals(nom)) {
				return etatProcessus;
			}
		}
		throw new IllegalStateException("pas d'état pour le processus '" + nom + "'");
	}

	/**
	 * obtenir la liste des états des processus.
	 * 
	 * @return la collection.
	 */
	public SortedSet<EtatProcessus> getProcessus() {
		return etatsProcessus;
	}

	@Override
	public String toString() {
		return "EtatGlobal [#=" + compteurInstance + ", proc=" + etatsProcessus + ", estInitial=" + estEtatGlobalInitial
				+ ", etatsAtteignables=" + etatsGlobauxAtteignables + "]";
	}
}
