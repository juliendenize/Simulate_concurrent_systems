package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Semaphore;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ValeurInitialeHorsBorne;

public class TestSemaphore {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurSemaphoreTest1Jeu1() throws Exception {
		new Semaphore(null, 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurSemaphoreTest1Jeu2() throws Exception {
		new Semaphore("", 0);
	}
	
	@Test(expected = ValeurInitialeHorsBorne.class)
	public void constructeurSemaphoreTest2() throws Exception {
		new Semaphore("sem", -1);
	}
	
	@Test
	public void constructeurSemaphoreTest3() throws Exception {
		Semaphore sem = new Semaphore("sem", 0);
		Assert.assertNotNull(sem);
		Assert.assertEquals("sem", sem.getNom());
		Assert.assertEquals(0, sem.getValeurInitiale());
		Assert.assertTrue(sem.invariant());
	}
	
}
