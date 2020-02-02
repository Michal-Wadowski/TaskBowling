package org.example.task.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

	private int frameNumber;
	
	/* Rolls pool */
	private List<Integer> rolls = new ArrayList<>(3);

	Frame(int pins, int frameNumber) {
		this.frameNumber = frameNumber;
		
		if( canRoll() )
			addPins(pins);
	}
	
	protected void addPins(int pins) {
		/* Trim pins to 0 - 10 range */
		if (pins < 0)
			pins = 0;

		if (pins > 10)
			pins = 10;
		
		rolls.add(pins);
	}
	
	/**
	 * Check that can roll in current frame. It checks frame number and pins that remain.
	 * 
	 * @return boolean
	 */
	public boolean canRoll() {
		/* Ignore invalid frame numbers */
		if( frameNumber > 10 || frameNumber < 1 )
			return false;
		
		/* Check to bonus points allow */
		if( frameNumber == 10 ) {
			if( this.hasStrike() || this.hasSpare() ) {
				/* Allow to maximum 3 rolls in 10th frame */
				if( rolls.size() < 3 )
					return true;
			}
		}else if( this.hasStrike() )
			return false;
				
		/* Standard rules */
		if( rolls.size() < 2 )
			return true;
		else
			return false;
	}

	/**
	 * Add pins to roll pool.
	 * 
	 * @param pins
	 */
	public void roll(int pins) {
		if( canRoll() ) {
			if( frameNumber < 10  ) {
				/* Don't exceed 10 pins in one frame */
				if( getScore() + pins > 10 )
					pins = 10 - getScore();
			}else { /* In last frame allow have more than 10 pins */
				if( hasStrike() ) {
					/* Trim total score to 30 if strike */
					if( getScore() + pins > 30 )
						pins = 30 - getScore();
				}else if( hasSpare()  ) {
					/* Trim total score to 20 if spare */
					if( getScore() + pins > 20 )
						pins = 30 - getScore();
				}
			}
			addPins(pins);
		}
	}
	
	/**
	 * Check that frame has roll at exact index in pool.
	 * 
	 * @param index		roll number (0 indexed).
	 * @return boolean
	 */
	public boolean hasRoll(int index) {
		return index < rolls.size();
	}
	
	/**
	 * Get roll pins from pool at index.
	 * 
	 * @param index		roll number (0 indexed).
	 * @return			number of pins.
	 */
	public int getRoll(int index) {
		if( hasRoll(index) )
			return rolls.get(index);
		return 0;
	}
	
	public boolean hasStrike() {
		return rolls.size() >= 1 && rolls.get(0) == 10;
	}

	public boolean hasSpare() {
		if( rolls.size() < 2 )
			return false;
		return rolls.stream().limit(2).reduce(0, (p, c) -> (p + c)) == 10;
	}
	
	public int getScore() {
		return rolls.stream().reduce(0, (p, c) -> (p + c));
	}
}
