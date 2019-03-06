package eu.telecomsudparis.csc4102.simint;

/**
 * Cette classe peut être utilisée pour écrire des appels de méthodes de la
 * façade de façon complémentaire aux tests de validation, c'est-à-dire par
 * exemple en attendant la programmation des tests de validation.
 * 
 * @author Denis Conan
 */
public class Main {
	/**
	 * point d'entrée du scénario.
	 * 
	 * @param args
	 *            arguments de la ligne de commande.
	 * @throws Exception
	 *             tous les problèmes.
	 */
	public static void main(final String[] args) throws Exception {
		SimInt simInt = new SimInt();
		/*
		 * simInt.creerProgramme("Programme1"); simInt.creerSemaphore("Sem1", 2);
		 * simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.P);
		 * simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.P);
		 * simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.V);
		 * simInt.ajouterInstruction("Programme1", "Sem1", TypeInstruction.V);
		 * simInt.creerProcessus("P1", "Programme1"); simInt.creerProcessus("P2",
		 * "Programme1"); simInt.debuteExecution();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 */

		/*
		 * simInt.creerProgramme("Programme3"); simInt.creerSemaphore("Cabine", 2);
		 * simInt.creerSemaphore("Panier", 2); simInt.ajouterInstruction("Programme3",
		 * "Cabine", TypeInstruction.P); simInt.ajouterInstruction("Programme3",
		 * "Panier", TypeInstruction.P); simInt.ajouterInstruction("Programme3",
		 * "Cabine", TypeInstruction.V); simInt.ajouterInstruction("Programme3",
		 * "Cabine", TypeInstruction.P); simInt.ajouterInstruction("Programme3",
		 * "Panier", TypeInstruction.V); simInt.ajouterInstruction("Programme3",
		 * "Cabine", TypeInstruction.V); simInt.creerProcessus("P1", "Programme3");
		 * simInt.creerProcessus("P2", "Programme3"); simInt.creerProcessus("P3",
		 * "Programme3"); simInt.creerProcessus("P4", "Programme3");
		 * simInt.creerProcessus("P5", "Programme3"); simInt.debuterExecution();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P3");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P4");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P5");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P3");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P4");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P1");
		 * simInt.etablirSystemeEnInterbloquage();
		 * simInt.avancerExecutionProcessus("P2");
		 * simInt.etablirSystemeEnInterbloquage();
		 */

		simInt.creerProgramme("Programme4");
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

	}

}
