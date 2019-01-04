// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;

public class TestSimIntCreerProcessus {

	private SimInt simInt;

	@Before
	public void setUp() throws Exception {
		simInt = new SimInt();
	}

	@After
	public void tearDown() {
		simInt = null;
	}

	@Test(expected = ExecutionADejaDebute.class)
	public void creerUnProgrammeTest1() throws Exception {
		simInt.mettreExecutionDebutee();
		simInt.creerProcessus("p1");
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest2Jeu1() throws Exception {
		simInt.creerProcessus(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest2Jeu2() throws Exception {
		simInt.creerProcessus("");
	}

	@Test(expected = ProcessusDejaPresent.class)
	public void creerUnProgrammeTest4And3() throws Exception {
		simInt.creerProcessus("p1");
		simInt.creerProcessus("p1");
	}
}
