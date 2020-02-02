package org.example.task.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
	
	private List<Integer> scores = new ArrayList<>();

	public void roll(int pins) {
		scores.add(pins);
	}
	
	public int calculateScore() {
		return scores.stream().reduce(0, (p, c) -> (p + c));
	}
}
