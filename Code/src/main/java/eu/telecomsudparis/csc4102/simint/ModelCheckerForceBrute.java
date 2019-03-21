package eu.telecomsudparis.csc4102.simint;

import java.util.ArrayList;
import java.util.Optional;

import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionNonDebutee;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusNonVivant;

public class ModelCheckerForceBrute implements ModelChecker {

	@Override
	public EtatGlobal validerSysteme(SimInt simint) {
		long startTime = System.nanoTime();
		ArrayList<EtatGlobal> etatsGlobauxAtteignables = new ArrayList<>();
		Optional<EtatGlobal> etatGlobalInterbloque;
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
							System.out.print(etatProc);
							System.out.println(etatProc.getEtat());
							e.printStackTrace();
						}
						simint.setDernierEtatGlobal(etatsGlobauxAtteignables.get(i));
					}
				}
			}
		}

		etatGlobalInterbloque = etatsGlobauxAtteignables.stream()
								.filter(etatGlobal -> etatGlobal.getEtatExecution().equals(EtatExecution.interbloque)).findAny();
		final int oneMillion = 1000000;
		long tempsExecution = (System.nanoTime() - startTime) / oneMillion;
		if (!etatGlobalInterbloque.isPresent()) {
			System.out.println("Validation du système = ok: " + EtatGlobal.getCompteurInstanciation() + " états globaux différents ont été générés en" + tempsExecution 
								+ "ms. Pas d'interbloquage trouvé.");
			return null;
		} else {
			System.out.println("Validation du système: " + etatsGlobauxAtteignables.size() + " états globaux différents ont été générés en " + tempsExecution 
								+ "ms.");
			System.out.println("interbloquage trouvé dans état: " + etatGlobalInterbloque.get());
			simint.chercherChemin(etatGlobalInterbloque.get());
			return etatGlobalInterbloque.get();
		}
	}

}
