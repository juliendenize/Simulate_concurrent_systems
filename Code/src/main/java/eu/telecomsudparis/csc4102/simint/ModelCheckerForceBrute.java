package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.Optional;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonVivant;

/**
 * Modèle checker brute.
 * 
 * @author julien
 *
 */
public class ModelCheckerForceBrute implements ModelChecker {

	@Override
	public Optional<EtatGlobal> validerSysteme(final SimInt simint, final EtatGlobal etatGlobalInitial) {
		ArrayList<EtatGlobal> etatsGlobauxAtteignables = new ArrayList<>();
		simint.debuterExecution();
		etatsGlobauxAtteignables.add(simint.getEtatGlobalInitial());
		for (int i = 0; i < etatsGlobauxAtteignables.size(); i++) {
			simint.setDernierEtatGlobal(etatsGlobauxAtteignables.get(i));
			if (simint.getDernierEtatGlobal().getEtatExecution().equals(EtatExecution.enCours)) {
				for (EtatProcessus etatProc: simint.getDernierEtatGlobal().getEtatsProcessus()) {
					if (etatProc.getEtat().equals(Etat.vivant)) {
						try {
							EtatGlobal nouveau = simint.avancerExecutionProcessus(etatProc.getProcessus().getNom());
							if (!etatsGlobauxAtteignables.contains(nouveau)) {
								etatsGlobauxAtteignables.add(nouveau);
							}
						} catch (ExecutionNonDebutee | ChaineDeCaracteresNullOuVide | ProcessusNonExistant
								| ProcessusNonVivant e) {
							System.out.println(etatProc.getEtat());
							e.printStackTrace();
						}
						simint.setDernierEtatGlobal(etatsGlobauxAtteignables.get(i));
					}
				}
			}
		}

		return etatsGlobauxAtteignables.stream()
								.filter(etatGlobal -> etatGlobal.getEtatExecution().equals(EtatExecution.interbloque)).findAny();
	}

}
