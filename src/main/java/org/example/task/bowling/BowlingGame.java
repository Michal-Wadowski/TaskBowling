package org.example.task.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * BowlingGame class is used to track score of a single player, across a single
 * bowling game.
 */
public class BowlingGame {

	private List<Integer> scores = new ArrayList<>();

	/**
	 * Provide how many pins were knocked down with a single roll.
	 * 
	 * @param pins integer value that represents how many pins were knocked down
	 *             with a single roll. pin minimum value is 0, maximum is 10 and pin
	 *             value will be trimmed to these constraints.
	 */
	public void roll(int pins) {
		if (pins < 0)
			pins = 0;

		if (pins > 10)
			pins = 10;

		scores.add(pins);
	}

	/**
	 * Calculates current game score.
	 * 
	 * @return result of current game score.
	 */
	public int calculateScore() {
		return scores.stream().reduce(0, (p, c) -> (p + c));
	}
}
