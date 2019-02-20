// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Processus;
import eu.telecomsudparis.csc4102.simint.Programme;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;

public class TestProcessus {
	
	private Programme prog;
	
	@Before
	public void setUp() throws Exception {
		prog = new Programme("prog");
	}
	
	@After
	public void tearDown() {
		prog = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProcessusTest1Jeu1() throws Exception {
		new Processus(null, prog);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProcessusTest1Jeu2() throws Exception {
		new Processus("", prog);
	}
	
	@Test(expected = NullPointerException.class)
	public void constructeurProcessusTest2() throws Exception {
		new Processus("proc", null);
	}

	@Test()
	public void constructeurProcessusTest3() throws Exception {
		Processus processus = new Processus("proc", prog);
		Assert.assertNotNull(processus);
		Assert.assertEquals("proc", processus.getNom());
		Assert.assertEquals(prog, processus.getProgram());
		Assert.assertTrue(processus.invariant());
	}
}
