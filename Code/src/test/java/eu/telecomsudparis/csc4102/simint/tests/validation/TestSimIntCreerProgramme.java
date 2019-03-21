package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.ModelCheckerForceBrute;
import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ProgrammeDejaPresent;

public class TestSimIntCreerProgramme {
	
	private SimInt simInt;
	
	@Before
	public void setUp() throws Exception {
		simInt = new SimInt(new ModelCheckerForceBrute());
	}

	@After
	public void tearDown() throws Exception {
		simInt = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProgrammeTest1Jeu1() throws Exception {
		simInt.creerProgramme(null);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnProgrammeTest1Jeu2() throws Exception {
		simInt.creerProgramme("");
	}
	
	@Test(expected = ProgrammeDejaPresent.class)
	public void creerUnProgrammeTest2() throws Exception {
		simInt.creerProgramme("prog");
		simInt.creerProgramme("prog");
	}
	
	@Test(expected = ExecutionADejaDebute.class)
	public void creerUnProgrammeTest3() throws Exception {
		simInt.debuterExecution();
		simInt.creerProgramme("prog");
	}
	
	@Test
	public void creerUnProgrammeTest4() throws Exception {
		simInt.creerProgramme("prog");
		Assert.assertNotNull(simInt.chercherProgramme("prog"));
	}
}
