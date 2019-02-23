package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Etat;
import eu.telecomsudparis.csc4102.simint.EtatGlobal;
import eu.telecomsudparis.csc4102.simint.EtatProcessus;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.EtatNonVivant;

public class TestEtatGlobal {
	
	private EtatGlobal origine;
	
	@Before
	public void setUp() throws Exception {
		origine = new EtatGlobal();
	}
	
	@After
	public void tearDown() throws Exception {
		origine = null;
	}

	@Test()
	public void constructeurEtatGlobalTest1() throws Exception {
		int compteurInstanciation = EtatGlobal.getCompteurInstanciation();
		EtatGlobal etat = new EtatGlobal();
		Assert.assertNotNull(etat.getEtatsProcessus());
		Assert.assertNotNull(etat.getEtatsSemaphores());
		Assert.assertFalse(etat.getSituationInterbloquage());
		Assert.assertEquals(++compteurInstanciation, EtatGlobal.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etat.getCompteurInstance());
		Assert.assertTrue(etat.invariant());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructeurParCopieEtatGlobalTest1() throws Exception {
		origine = null;
		new EtatGlobal(origine);
	}
	
	@Test
	public void constructeurParCopieEtatGlobalTest2() throws Exception {
		int compteurInstanciation = EtatGlobal.getCompteurInstanciation();
		EtatGlobal etat = new EtatGlobal();
		Assert.assertNotNull(etat.getEtatsProcessus());
		Assert.assertNotNull(etat.getEtatsSemaphores());
		Assert.assertNotNull(etat.getSituationInterbloquage());
		Assert.assertEquals(++compteurInstanciation, EtatGlobal.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etat.getCompteurInstance());
		Assert.assertTrue(etat.invariant());
	}

}
