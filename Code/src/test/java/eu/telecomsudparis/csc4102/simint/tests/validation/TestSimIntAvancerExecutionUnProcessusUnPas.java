// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Etat;
import eu.telecomsudparis.csc4102.simint.EtatExecution;
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
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p1").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p2").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.interbloque);
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
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p2").getEtat());
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p1").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.interbloque);
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
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p5").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p1").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p2").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.interbloque);
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
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(Etat.bloque, simint.getDernierEtatGlobal().chercherEtatProcessus("p5").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p1");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p1").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p2");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p2").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(Etat.vivant, simint.getDernierEtatGlobal().chercherEtatProcessus("p5").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p3");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p3").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p4");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p4").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.enCours);
		
		simint.avancerExecutionProcessus("p5");
		Assert.assertEquals(Etat.termine, simint.getDernierEtatGlobal().chercherEtatProcessus("p5").getEtat());
		Assert.assertEquals(simint.etablirSystemeEnInterblocage(), EtatExecution.termine);
	}
}
