// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.ModelCheckerAleatoire;
import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.TypeInstruction;

public class TestSimIntValiderLeSystemeAleatoire {
	
	private SimInt simint;
	
	@Before
	public void setUp() throws Exception {
		simint = new SimInt();
		simint.setModelChecker(new ModelCheckerAleatoire());
	}

	@After
	public void tearDown() throws Exception {
		simint = null;
	}

	@Test
	public void testSimIntValiderLeSysteme1() throws Exception {
		simint.creerSemaphore("cabines", 2);
		simint.creerProgramme("prog");
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.V);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		Assert.assertFalse(simint.validerSysteme());
	}
	
	@Test
	public void testSimIntValiderLeSysteme2() throws Exception {
		simint.creerSemaphore("cabines", 1);
		simint.creerSemaphore("paniers", 1);
		simint.creerProgramme("prog");
		simint.ajouterInstruction("prog", "cabines", TypeInstruction.P);
		simint.ajouterInstruction("prog", "paniers", TypeInstruction.P);
		simint.creerProcessus("p1", "prog");
		simint.creerProcessus("p2", "prog");
		Assert.assertFalse(simint.validerSysteme());
	}
	
	@Test
	public void testSimIntValiderLeSysteme3() throws Exception {
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
		Assert.assertFalse(simint.validerSysteme());
	}
	
	@Test
	public void testSimIntValiderLeSysteme4() throws Exception {
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
		Assert.assertTrue(simint.validerSysteme());
	}
}
