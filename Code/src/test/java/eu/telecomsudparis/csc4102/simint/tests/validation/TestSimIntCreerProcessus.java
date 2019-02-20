// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.simint.tests.validation;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ProcessusDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.ProgrammeNonExistant;

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

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest1Jeu1() throws Exception {
		simInt.creerProcessus(null, "prog");
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest1Jeu2() throws Exception {
		simInt.creerProcessus("", "prog");
	}
	
	@Test(expected = ExecutionADejaDebute.class)
	public void creerUnProcesussTest2() throws Exception {
		simInt.debuterExecution();
		simInt.creerProcessus("p1", "prog");
	}

	@Test(expected = ProcessusDejaPresent.class)
	public void creerUnProcessusTest3 () throws Exception {
		simInt.creerProgramme("prog");
		simInt.creerProcessus("p1", "prog");
		simInt.creerProcessus("p1", "prog");
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest4Jeu1() throws Exception {
		simInt.creerProcessus("p1", null);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProcessusTest4Jeu2() throws Exception {
		simInt.creerProcessus("p1", "");
	}
	
	@Test(expected = ProgrammeNonExistant.class)
	public void creerUnProcessusTest5() throws Exception {
		simInt.creerProcessus("p1", "prog");
	}
	
	@Test
	public void creerUnProcessusTest6() throws Exception {
		simInt.creerProgramme("prog");
		simInt.creerProcessus("p1", "prog");
		Assert.assertNotNull(simInt.chercherProcessus("p1"));
	}
}
