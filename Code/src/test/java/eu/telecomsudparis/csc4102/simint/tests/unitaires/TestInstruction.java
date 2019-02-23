package eu.telecomsudparis.csc4102.simint.tests.unitaires;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.simint.Instruction;
import eu.telecomsudparis.csc4102.simint.Semaphore;
import eu.telecomsudparis.csc4102.simint.TypeInstruction;

import org.junit.Assert;

public class TestInstruction {
	TypeInstruction type;
	Semaphore sem;

	@Before
	public void setUp() throws Exception {
		type = TypeInstruction.P;
		sem = new Semaphore("sem", 0);
	}

	@After
	public void tearDown() throws Exception {
		type = null;
		sem = null;
	}

	@Test(expected = NullPointerException.class)
	public void constructeurInstructionTest1() throws Exception {
		new Instruction(null, sem);
	}
	
	@Test(expected = NullPointerException.class)
	public void constructeurInstructionTest2() throws Exception {
		new Instruction(type, null);
	}
	
	@Test()
	public void constructeurInstructionTest3() throws Exception {
		Instruction instruction = new Instruction(type, sem);
		Assert.assertEquals(instruction.getTypeInstruction(), type);
		Assert.assertEquals(instruction.getSemaphore(), sem);
		Assert.assertTrue(instruction.invariant());
	}

}
