// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Processus;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;

public class TestProcessus {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() {
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProcessusTest1Jeu1() throws Exception {
		new Processus(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProcessusTest1Jeu2() throws Exception {
		new Processus("");
	}

	@Test
	public void constructeurProcessusTest2() throws Exception {
		Processus processus = new Processus("p1");
		Assert.assertNotNull(processus);
		Assert.assertEquals("p1", processus.getNom());
	}
}
