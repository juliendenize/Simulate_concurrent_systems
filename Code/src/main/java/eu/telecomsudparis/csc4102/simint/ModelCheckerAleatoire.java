package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonVivant;

/**
 * Modèle checker aléatoire.
 * 
 * @author julien
 *
 */
public class ModelCheckerAleatoire implements ModelChecker {
	@Override
	public Optional<EtatGlobal> validerSysteme(final SimInt simint, final EtatGlobal etatGlobalInitial) {
		Random generator = new Random();
		int nombreAleatoire = 0;
		EtatGlobal etatGlobalCourant = etatGlobalInitial;
		simint.debuterExecution();
		simint.setDernierEtatGlobal(etatGlobalInitial);
		while (etatGlobalCourant.getEtatExecution() == EtatExecution.enCours) {
			for (EtatProcessus etatProc: etatGlobalCourant.getEtatsProcessus()) {
				if (etatProc.getEtat().equals(Etat.vivant)) {
					try {
						simint.avancerExecutionProcessus(etatProc.getProcessus().getNom());
						simint.setDernierEtatGlobal(etatGlobalCourant);
					} catch (ExecutionNonDebutee | ChaineDeCaracteresNullOuVide | ProcessusNonExistant
							| ProcessusNonVivant e) {
						System.out.println(etatProc.getEtat());
						e.printStackTrace();
					}
				}
			}
			nombreAleatoire = generator.nextInt(etatGlobalCourant.getEtatsGlobauxAtteignables().size());
			etatGlobalCourant = etatGlobalCourant.getEtatsGlobauxAtteignables().get(nombreAleatoire);
			simint.setDernierEtatGlobal(etatGlobalCourant);
			
		}
		if (etatGlobalCourant.getEtatExecution() == EtatExecution.interbloque) {
			return Optional.of(etatGlobalCourant);
		}
		return Optional.empty();
	}

}
