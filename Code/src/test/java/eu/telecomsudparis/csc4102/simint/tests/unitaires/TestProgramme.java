package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Programme;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;

public class TestProgramme {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProgrammeTest1Jeu1() throws Exception {
		new Programme(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurProgrammeTest1Jeu2() throws Exception {
		new Programme("");
	}
	
	@Test
	public void constructeurProgrammeTest2() throws Exception {
		Programme programme = new Programme("prog");
		Assert.assertNotNull(programme);
		Assert.assertEquals("prog", programme.getNom());
		Assert.assertTrue(programme.invariant());
	}
	
}
