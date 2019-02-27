package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.SemaphoreDejaPresent;
import eu.telecomsudparis.csc4102.simint.exception.ValeurInitialeHorsBorne;

public class TestSimIntCreerSemaphore {

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
	public void creerUnSemaphoreTest1Jeu1() throws Exception {
		simInt.creerSemaphore(null, 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerUnSemaphoreTest1Jeu2() throws Exception {
		simInt.creerSemaphore("", 0);
	}
	
	@Test(expected = SemaphoreDejaPresent.class)
	public void creerUnSemaphoreTest2() throws Exception {
		simInt.creerSemaphore("sem", 0);
		simInt.creerSemaphore("sem", 0);
	}
	
	@Test(expected = ValeurInitialeHorsBorne.class)
	public void creerUnSemaphoreTest3() throws Exception {
		simInt.creerSemaphore("sem", -1);
	}
	
	@Test(expected = ExecutionADejaDebute.class)
	public void creerUnSemaphoreTest4() throws Exception {
		simInt.debuterExecution();
		simInt.creerSemaphore("sem", 0);
	}
	
	@Test
	public void creerUnSemaphoreTest5() throws Exception {
		simInt.creerSemaphore("sem", 0);
		Assert.assertNotNull(simInt.chercherSemaphore("sem"));
	}
		
}
