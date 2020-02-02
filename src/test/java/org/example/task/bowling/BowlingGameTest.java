package org.example.task.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
	
	@Test
	public void calculateScore_ReturnZero_IfNoRolls() {
		
		BowlingGame bg = new BowlingGame();
		
		assertEquals(0, bg.calculateScore());		
	}

}
