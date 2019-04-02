// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.simint.tests.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.SimInt;
import eu.telecomsudparis.csc4102.simint.TypeInstruction;
import eu.telecomsudparis.csc4102.simint.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.simint.exception.ExecutionADejaDebute;
import eu.telecomsudparis.csc4102.simint.exception.ProgrammeNonExistant;
import eu.telecomsudparis.csc4102.simint.exception.SemaphoreNonExistant;

public class TestSimIntAjouterUneInstructionAUnProgramme {
	
	private SimInt simint;
	
	@Before
	public void setUp() throws Exception {
		simint = new SimInt();	
		simint.creerProgramme("prog");
		simint.creerSemaphore("sem", 0);
	}

	@After
	public void tearDown() throws Exception {
		simint = null;
	}

	@Test(expected = NullPointerException.class)
	public void ajouterUneInstructionTest1() throws Exception {
		simint.ajouterInstruction("prog", "sem", null);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterUneInstructionTest2Jeu1() throws Exception {
		simint.ajouterInstruction("prog", null, TypeInstruction.P);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterUneInstructionTest2Jeu2() throws Exception {
		simint.ajouterInstruction("prog", "", TypeInstruction.P);
	}
	
	@Test(expected = SemaphoreNonExistant.class)
	public void ajouterUneInstructionTest3() throws Exception {
		simint.ajouterInstruction("prog", "yolo", TypeInstruction.P);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterUneInstructionTest4Jeu1() throws Exception {
		simint.ajouterInstruction(null, "sem", TypeInstruction.P);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterUneInstructionTest4Jeu2() throws Exception {
		simint.ajouterInstruction("", "sem", TypeInstruction.P);
	}
	
	@Test(expected = ProgrammeNonExistant.class)
	public void ajouterUneInstructionTest5() throws Exception {
		simint.ajouterInstruction("yolo", "sem", TypeInstruction.P);
	}
	
	@Test(expected = ExecutionADejaDebute.class)
	public void ajouterUneInstructionTest6() throws Exception {
		simint.debuterExecution();
		simint.ajouterInstruction("prog", "sem", TypeInstruction.P);
	}
	
	@Test
	public void ajouterUneInstructionTest7() throws Exception {
		simint.ajouterInstruction("prog", "sem", TypeInstruction.P);
		Assert.assertNotNull(simint.chercherProgramme("prog").chercherInstruction(0));
	}	
}
