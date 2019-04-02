// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.EtatSemaphore;
import eu.telecomsudparis.csc4102.simint.Semaphore;

import org.junit.Assert;

public class TestEtatSemaphore {
	private EtatSemaphore origine;
	private Semaphore sem;

	@Before
	public void setUp() throws Exception {
		sem = new Semaphore("sem", 0);
		origine = new EtatSemaphore(sem);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void constructeurEtatSemaphoreTest1() throws Exception {
		sem = null;
		new EtatSemaphore(sem);
	}
	
	@Test
	public void constructeurEtatSemaphoreTest2() throws Exception {
		int compteurInstanciation = EtatSemaphore.getCompteurInstanciation();
		EtatSemaphore etatSem = new EtatSemaphore(sem);
		Assert.assertEquals(etatSem.getValeurCompteur(), sem.getValeurInitiale());
		Assert.assertEquals(etatSem.getSemaphore(), sem);
		Assert.assertEquals(++compteurInstanciation, EtatSemaphore.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etatSem.getCompteurInstance());
		Assert.assertTrue(etatSem.invariant());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructeurParCopieEtatSemaphoreTest1() throws Exception {
		origine = null;
		new EtatSemaphore(origine);
	}
	
	@Test
	public void constructeurParCopieEtatSemaphoreTest2() throws Exception {
		int compteurInstanciation = EtatSemaphore.getCompteurInstanciation();
		EtatSemaphore etatSem = new EtatSemaphore(origine);
		Assert.assertEquals(etatSem.getSemaphore(), origine.getSemaphore());
		Assert.assertEquals(etatSem.getValeurCompteur(), origine.getValeurCompteur());
		Assert.assertEquals(++compteurInstanciation, EtatSemaphore.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etatSem.getCompteurInstance());
		Assert.assertTrue(etatSem.invariant());
	}
	
}
