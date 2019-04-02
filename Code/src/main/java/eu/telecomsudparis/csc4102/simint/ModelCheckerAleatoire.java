package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonVivant;

/**
 * Model checker aléatoire.
 * Les états globaux sont parcourus aléatoirement à chaque itération.
 * 
 * @author Julien Denize - Pierre Chaffardon
 *
 */
public class ModelCheckerAleatoire implements ModelChecker {
	@Override
	public Optional<EtatGlobal> validerSysteme(final SimInt simint, final EtatGlobal etatGlobalInitial) {
		Random generator = new Random();
		int nombreAleatoire = 0;
		ArrayList<EtatGlobal> etatsGlobauxTraites = new ArrayList<>();
		ArrayList<EtatGlobal> etatsGlobauxNonTraites = new ArrayList<>();
		etatsGlobauxNonTraites.add(etatGlobalInitial);
		simint.setDernierEtatGlobal(etatGlobalInitial);
		while (etatsGlobauxNonTraites.size() > 0 && !simint.getDernierEtatGlobal().getEtatExecution().equals(EtatExecution.interbloque)) {
			nombreAleatoire = generator.nextInt(etatsGlobauxNonTraites.size());
			simint.setDernierEtatGlobal(etatsGlobauxNonTraites.get(nombreAleatoire));
			etatsGlobauxNonTraites.remove(simint.getDernierEtatGlobal());
			etatsGlobauxTraites.add(simint.getDernierEtatGlobal());
			for (EtatProcessus etatProc: simint.getDernierEtatGlobal().getEtatsProcessus()) {
				if (etatProc.getEtat().equals(Etat.vivant)) {
					try {
						EtatGlobal nouveau = simint.avancerExecutionProcessus(etatProc.getProcessus().getNom());
						if (!etatsGlobauxNonTraites.contains(nouveau) && !etatsGlobauxTraites.contains(nouveau)) {
							etatsGlobauxNonTraites.add(nouveau);
						}
					} catch (ExecutionNonDebutee | ChaineDeCaracteresNullOuVide | ProcessusNonExistant
							| ProcessusNonVivant e) {
						System.out.println(etatProc.getEtat());
						e.printStackTrace();
					}
				simint.setDernierEtatGlobal(simint.getDernierEtatGlobal().getEtatGlobalPrecedent());
				}
			}
		}
		if (simint.getDernierEtatGlobal().getEtatExecution() == EtatExecution.interbloque) {
			return Optional.of(simint.getDernierEtatGlobal());
		}
		return Optional.empty();
	}

}
