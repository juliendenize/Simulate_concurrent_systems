package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Etat;
import eu.telecomsudparis.csc4102.simint.EtatProcessus;
import eu.telecomsudparis.csc4102.simint.Processus;
import eu.telecomsudparis.csc4102.simint.Programme;

public class TestEtatProcessus {
	
	private EtatProcessus origine;
	private Programme prog;
	private Processus proc;

	@Before
	public void setUp() throws Exception {
		prog = new Programme("prog");
		proc = new Processus("proc", prog);
		origine = new EtatProcessus(proc);
	}

	@After
	public void tearDown() throws Exception {
		prog = null;
		proc = null;
		origine = null;
	}
	

	@Test(expected = NullPointerException.class)
	public void constructeurEtatProcessusTest1() throws Exception {
		proc = null;
		new EtatProcessus(proc);
	}
	
	@Test
	public void constructeurEtatProcessusTest2() throws Exception {
		int compteurInstanciation = EtatProcessus.getCompteurInstanciation();
		EtatProcessus etat = new EtatProcessus(proc);
		Assert.assertEquals(etat.getProcessus(), proc);
		Assert.assertEquals(++compteurInstanciation, EtatProcessus.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etat.getCompteurInstance());
		Assert.assertTrue(etat.invariant());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructeurParCopieEtatProcessusTest1() throws Exception {
		origine = null;
		new EtatProcessus(origine);
	}
	
	@Test
	public void constructeurParCopieEtatProcessusTest2() throws Exception {
		int compteurInstanciation = EtatProcessus.getCompteurInstanciation();
		EtatProcessus etatProc = new EtatProcessus(origine);
		Assert.assertEquals(etatProc.getProcessus(), origine.getProcessus());
		Assert.assertEquals(++compteurInstanciation, EtatProcessus.getCompteurInstanciation());
		Assert.assertEquals(compteurInstanciation, etatProc.getCompteurInstance());
		Assert.assertTrue(etatProc.invariant());
	}
	
	@Test(expected = IllegalStateException.class)
	public void avancerExecutionTest1() throws Exception {
		EtatProcessus etatProc = new EtatProcessus(proc);
		etatProc.setEtat(Etat.termine);
		etatProc.avancerExecution();
	}
	
	@Test
	public void avancerExecutionTest2() throws Exception {
		EtatProcessus etatProc = new EtatProcessus(proc);
		etatProc.avancerExecution();
		Assert.assertTrue(etatProc.invariant());
	}
}
