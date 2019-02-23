package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Etat;
import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.TypeInstruction;

public class TestSimIntAvancerExecutionUnProcessusUnPas {
	
	private SimInt simint;
	
	@Before
	public void setUp() throws Exception {
		simint = new SimInt();
	}

	@After
	public void tearDown() throws Exception {
		simint = null;
	}

	@Test
	public void testSimIntAvancerProcessus1() throws Exception {
		simint.creerSemaphore("cabines", 2);
		simint.creerProgramme("prog");
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		simint.debuterExecution();
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p1").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p2").getEtat());
		Assert.assertTrue(simint.etablirSystemeEnInterbloquage());
	}
	
	@Test
	public void testSimIntAvancerProcessus2() throws Exception {
		simint.creerSemaphore("cabines", 1);
		simint.creerSemaphore("paniers", 1);
		simint.creerProgramme("prog");
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.P);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		simint.debuterExecution();
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p2").getEtat());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p1").getEtat());
		Assert.assertTrue(simint.etablirSystemeEnInterbloquage());
	}
	
	@Test
	public void testSimIntAvancerProcessus3() throws Exception {
		simint.creerSemaphore("cabines", 2);
		simint.creerSemaphore("paniers", 2);
		simint.creerProgramme("prog");
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		simint.creerProcessus("p3", "prog");
		simint.creerProcessus("p4", "prog");
		simint.creerProcessus("p5", "prog");
		simint.debuterExecution();
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p5").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p1").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p2").getEtat());
		Assert.assertTrue(simint.etablirSystemeEnInterbloquage());
	}
	
	@Test
	public void testSimIntAvancerProcessus4() throws Exception {
		simint.creerProgramme("prog");
		simint.creerSemaphore("cabines", 2);
		simint.creerSemaphore("paniers", 2);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		simint.creerProcessus("p3", "prog");
		simint.creerProcessus("p4", "prog");
		simint.creerProcessus("p5", "prog");
		simint.debuterExecution();
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p5").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p1");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p1").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p2");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p2").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p5").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p3");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p3").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p4");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p4").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
		
		simint.avancerExecution("p5");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherUnEtatProcessus("p5").getEtat());
		Assert.assertFalse(simint.etablirSystemeEnInterbloquage());
	}
}
