package org.example.task.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
	
	@Test
	public void calculateScore_ReturnZero_IfNoRolls() {		
		BowlingGame bg = new BowlingGame();
		
		assertEquals(0, bg.calculateScore());		
	}
	
	@Test
	public void calculateScore_ReturnScore_AtFirstRollZero() {		
		BowlingGame bg = new BowlingGame();
		
		bg.roll(0);
		assertEquals(0, bg.calculateScore());		
	}
	
	@Test
	public void calculateScore_ReturnScore_AtFirstRoll() {		
		BowlingGame bg = new BowlingGame();
		
		bg.roll(5);
		assertEquals(5, bg.calculateScore());		
	}
	
	@Test
	public void calculateScore_ReturnScore_AtFirstRollTen() {		
		BowlingGame bg = new BowlingGame();
		
		bg.roll(10);
		assertEquals(10, bg.calculateScore());		
	}

}
