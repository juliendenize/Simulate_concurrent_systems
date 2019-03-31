package eu.telecomsudparis.csc4102.simint;

/**
 * Cette classe peut être utilisée pour écrire des appels de méthodes de la
 * façade de façon complémentaire aux tests de validation, c'est-à-dire par
 * exemple en attendant la programmation des tests de validation.
 * 
 * @author Denis Conan
 */
public final class Main {
	/**
	 * point d'entrée du scénario.
	 * 
	 * @param args
	 *            arguments de la ligne de commande.
	 * @throws Exception
	 *             tous les problèmes.
	 */
	public static void main(final String[] args) throws Exception {
		ModelChecker modelChecker = new ModelCheckerForceBrute();
		SimInt simInt = new SimInt(modelChecker);
		
		simInt.creerProgramme("Programme1"); simInt.creerSemaphore("Sem1", 2);
		simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.P);
		simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.P);
		simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.V);
		simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.V);
		simInt.creerProcessus("P1", "Programme1");
		simInt.creerProcessus("P2", "Programme1");
		simInt.validerSysteme();
		
		simInt = new SimInt(modelChecker);
		simInt.creerSemaphore("cabines", 1);
		simInt.creerSemaphore("paniers", 1);
		simInt.creerProgramme("prog");
		simInt.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simInt.ajouterInstruction("prog", "paniers", TypeInstruction.P);
		simInt.creerProcessus("p1", "prog");
		simInt.creerProcessus("p2", "prog");
		simInt.validerSysteme();
		
		simInt = new SimInt(modelChecker);		
		simInt.creerProgramme("Programme3"); 
		simInt.creerSemaphore("Cabine", 2);
		simInt.creerSemaphore("Panier", 2);
		simInt.ajouterInstruction("Programme3", "Cabine", TypeInstruction.P);
		simInt.ajouterInstruction("Programme3", "Panier", TypeInstruction.P);
		simInt.ajouterInstruction("Programme3", "Cabine", TypeInstruction.V);
		simInt.ajouterInstruction("Programme3", "Cabine", TypeInstruction.P);
		simInt.ajouterInstruction("Programme3", "Panier", TypeInstruction.V);
		simInt.ajouterInstruction("Programme3", "Cabine", TypeInstruction.V);
		simInt.creerProcessus("P1", "Programme3");
		simInt.creerProcessus("P2", "Programme3");
		simInt.creerProcessus("P3", "Programme3");
		simInt.creerProcessus("P4", "Programme3");
		simInt.creerProcessus("P5", "Programme3");
		simInt.validerSysteme();

/*		simInt.creerProgramme("Programme4");
		simInt.creerSemaphore("Cabine", 2);
		simInt.creerSemaphore("Panier", 2);
		simInt.ajouterInstruction("Programme4", "Panier", TypeInstruction.P);
		simInt.ajouterInstruction("Programme4", "Cabine", TypeInstruction.P);
		simInt.ajouterInstruction("Programme4", "Cabine", TypeInstruction.V);
		simInt.ajouterInstruction("Programme4", "Cabine", TypeInstruction.P);
		simInt.ajouterInstruction("Programme4", "Panier", TypeInstruction.V);
		simInt.ajouterInstruction("Programme4", "Cabine", TypeInstruction.V);
		simInt.creerProcessus("P1", "Programme4");
		simInt.creerProcessus("P2", "Programme4");
		simInt.creerProcessus("P3", "Programme4");
		simInt.creerProcessus("P4", "Programme4");
		simInt.creerProcessus("P5", "Programme4");
		simInt.debuterExecution();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P1");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P2");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P3");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P4");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		simInt.avancerExecution("P5");
		simInt.etablirSystemeEnInterbloquage();
		
		System.out.println("Main terminé."); */
	}

}
