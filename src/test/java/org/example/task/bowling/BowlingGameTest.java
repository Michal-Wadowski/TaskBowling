package org.example.task.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BowlingGameTest {

	@Test()
	public void roll_TrimsValueToZero_IfRollNegativePins() {
		BowlingGame bg = new BowlingGame();

		bg.roll(-1);
		assertEquals(0, bg.calculateScore());
	}

	@Test()
	public void roll_TrimsValueToTen_IfRollOverTenPins() {
		BowlingGame bg = new BowlingGame();

		bg.roll(11);
		assertEquals(10, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_ReturnsZero_IfNoRolls() {
		BowlingGame bg = new BowlingGame();

		assertEquals(0, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesZeroScore_AtFirstRollZeroPins() {
		BowlingGame bg = new BowlingGame();

		bg.roll(0);
		assertEquals(0, bg.calculateScore());
	}

	@Test
	public void calculateScore_CalculatesFiveScore_AtFirstRollFivePins() {
		BowlingGame bg = new BowlingGame();

		bg.roll(5);
		assertEquals(5, bg.calculateScore());
	}

	@Test
	public void calculateScore_CalculatesTenScore_AtFirstRollTenPins() {
		BowlingGame bg = new BowlingGame();

		bg.roll(10);
		assertEquals(10, bg.calculateScore());
	}

	@Test
	public void calculateScore_CalculatesScore_AtSecondRollInFirstFrame() {
		BowlingGame bg = new BowlingGame();

		bg.roll(5);
		bg.roll(3);
		assertEquals(8, bg.calculateScore());
	}

	@Test
	public void calculateScore_DontExceedTen_AtSingleFrame() {
		BowlingGame bg = new BowlingGame();

		bg.roll(9);
		bg.roll(9);
		assertEquals(10, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_InSecondFrame() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(4);
		bg.roll(5);
		assertEquals(9, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(8);
		assertEquals(17, bg.calculateScore());
		
		bg.roll(1);
		assertEquals(18, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsStrike() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 (strike) */
		bg.roll(10);
		assertEquals(10, bg.calculateScore());
		
		
		/* frame 2 */
		bg.roll(3);
		
		/* frame 1: 10 + (3)
		 * frame 2: 3
		 * total: 16
		 */
		assertEquals(16, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(6);
		
		/* frame 1: 10 + (3 + 6)
		 * frame 2: 3 + 6
		 * total: 28
		 */
		assertEquals(28, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(2);
		assertEquals(30, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(3);
		assertEquals(33, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsTwoStrikesConsecutively() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 (strike) */
		bg.roll(10);
		assertEquals(10, bg.calculateScore());
		
		/* frame 2 (strike) */
		bg.roll(10);
		
		/* frame 1: 10 + (10)
		 * frame 2: 10
		 * total: 30
		 */
		assertEquals(30, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(4);
		
		/* frame 1: 10 + (10 + 4)
		 * frame 2: 10 + (4)
		 * frame 3: 4
		 */
		assertEquals(42, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(2);
		
		/* frame 1: 10 + (10 + 4)
		 * frame 2: 10 + (4 + 2)
		 * frame 3: 4 + 2
		 */
		assertEquals(46, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(2);
		assertEquals(48, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(3);
		assertEquals(51, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsThreeStrikesConsecutively() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 (strike) */
		bg.roll(10);
		assertEquals(10, bg.calculateScore());
		
		/* frame 2 (strike) */
		bg.roll(10);
		
		/* frame 1: 10 + (10)
		 * frame 2: 10
		 * total: 30
		 */
		assertEquals(30, bg.calculateScore());
		
		/* frame 3 (strike) */
		bg.roll(10);
		
		/* frame 1: 10 + (10 + 10)
		 * frame 2: 10 + (10)
		 * frame 3: 10
		 * total: 60
		 */
		assertEquals(60, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(2);
		
		/* frame 1: 10 + (10 + 10)
		 * frame 2: 10 + (10 + 2)
		 * frame 3: 10 + (2)
		 * frame 4: 2
		 * total: 66
		 */
		assertEquals(66, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(3);
		
		/* frame 1: 10 + (10 + 10)
		 * frame 2: 10 + (10 + 2)
		 * frame 3: 10 + (2 + 3)
		 * frame 4: 2 + 3
		 * total: 72
		 */
		assertEquals(72, bg.calculateScore());
		
		/* frame 5 */
		bg.roll(2);
		assertEquals(74, bg.calculateScore());
		
		/* frame 5 */
		bg.roll(3);
		assertEquals(77, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsSpare() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(7);
		assertEquals(7, bg.calculateScore());
		
		
		/* frame 2 (spare)*/
		bg.roll(3);
		assertEquals(10, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(4);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4
		 * total: 18
		 */
		assertEquals(18, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(2);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4 + 2
		 * total: 20
		 */
		assertEquals(20, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(2);
		assertEquals(22, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(3);
		assertEquals(25, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsTwoSparesConsecutively() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(7);
		assertEquals(7, bg.calculateScore());
		
		
		/* frame 2 (spare)*/
		bg.roll(3);
		assertEquals(10, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(4);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4
		 * total: 18
		 */
		assertEquals(18, bg.calculateScore());
		
		/* frame 2 (spare)*/
		bg.roll(6);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4 + 6
		 * total: 24
		 */
		assertEquals(24, bg.calculateScore());
		
		/* frame 3*/
		bg.roll(3);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4 + 6 + (3)
		 * frame 3: 3
		 * total: 30
		 */
		assertEquals(30, bg.calculateScore());
		
		/* frame 3*/
		bg.roll(5);
		
		/* frame 1: 7 + 3 + (4)
		 * frame 2: 4 + 6 + (3)
		 * frame 3: 3 + 5
		 * total: 35
		 */
		assertEquals(35, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(2);
		assertEquals(37, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(3);
		assertEquals(40, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScore_IfPlayerHitsSrikesAndSpares() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 (strike) */
		bg.roll(10);
		assertEquals(10, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(3);
		
		/* frame 2 (spare) */
		bg.roll(7);
		
		/* frame 1: 10 + (3 + 7)
		 * frame 2: 3 + 7
		 * total: 30
		 */
		assertEquals(30, bg.calculateScore());
		
		/* frame 3 (strike) */
		bg.roll(10);
		
		/* frame 1: 10 + (3 + 7)
		 * frame 2: 3 + 7 + (10)
		 * frame 3: 10
		 * total: 50
		 */
		assertEquals(50, bg.calculateScore());
		
		/* frame 4*/
		bg.roll(3);
		
		/* frame 4 (spare)*/
		bg.roll(7);
		
		/* frame 1: 10 + (3 + 7)
		 * frame 2: 3 + 7 + (10)
		 * frame 3: 10 + (3 + 7)
		 * frame 4: 3 + 7
		 * total: 70
		 */
		assertEquals(70, bg.calculateScore());
		
		/* frame 5*/
		bg.roll(5);
		
		/* frame 5*/
		bg.roll(3);
		
		/* frame 1: 10 + (3 + 7)
		 * frame 2: 3 + 7 + (10)
		 * frame 3: 10 + (3 + 7)
		 * frame 4: 3 + 7 + (5)
		 * frame 5: 5 + 3
		 * total: 83
		 */
		assertEquals(83, bg.calculateScore());
		
	}
	
	@Test
	public void calculateScore_CalculatesScores_WithinTenFrames() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(5, bg.calculateScore());
		
		/* frame 2 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(10, bg.calculateScore());
		
		/* frame 3 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(15, bg.calculateScore());
		
		/* frame 4 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(20, bg.calculateScore());
		
		/* frame 5 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(25, bg.calculateScore());
		
		/* frame 6 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(30, bg.calculateScore());
		
		/* frame 7 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(35, bg.calculateScore());
		
		/* frame 8 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(40, bg.calculateScore());
		
		/* frame 9 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(45, bg.calculateScore());
		
		/* frame 10 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(50, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_IgnoreRollValues_AfterTenFrames() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 2 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 3 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 4 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 5 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 6 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 7 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 8 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 9 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 10 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(50, bg.calculateScore());
		
		/* frame "11" */
		bg.roll(3);
		bg.roll(2);
		assertEquals(50, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScoreOfThirdRoll_IfPlayerHitsSpareAtTenFrame() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 2 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 3 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 4 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 5 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 6 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 7 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 8 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 9 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(45, bg.calculateScore());
		
		/* frame 10 (spare) */
		bg.roll(3);
		bg.roll(7);
		assertEquals(55, bg.calculateScore());
		
		/* frame 10 - third bonus roll */
		bg.roll(5);
		assertEquals(60, bg.calculateScore());
		
		/* next rolls are ignored */
		bg.roll(5);
		assertEquals(60, bg.calculateScore());
	}
	
	@Test
	public void calculateScore_CalculatesScoreOfSecondAndThirdRoll_IfPlayerHitsStrikeAtTenFrame() {
		BowlingGame bg = new BowlingGame();

		/* frame 1 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 2 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 3 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 4 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 5 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 6 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 7 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 8 */
		bg.roll(3);
		bg.roll(2);
		
		/* frame 9 */
		bg.roll(3);
		bg.roll(2);
		assertEquals(45, bg.calculateScore());
		
		/* frame 10 (strike) */
		bg.roll(10);
		assertEquals(55, bg.calculateScore());
		
		/* frame 10 - second roll */
		bg.roll(5);
		assertEquals(60, bg.calculateScore());
		
		/* frame 10 - third roll */
		bg.roll(5);
		assertEquals(65, bg.calculateScore());
		
		/* next rolls are ignored */
		bg.roll(5);
		assertEquals(65, bg.calculateScore());
	}

}
