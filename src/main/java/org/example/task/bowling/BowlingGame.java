package org.example.task.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * BowlingGame class is used to track score of a single player, across a single
 * bowling game.
 */
public class BowlingGame {

	private List<Frame> frames = new ArrayList<>(10);
	
	/**
	 * Provide how many pins were knocked down with a single roll.
	 * 
	 * @param pins integer value that represents how many pins were knocked down
	 *             with a single roll. pin minimum value is 0, maximum is 10 and pin
	 *             value will be trimmed to these constraints.
	 */
	public void roll(int pins) {		
		if( frames.size() == 0 )
			frames.add( new Frame(pins, frames.size() + 1) );
		else if( frames.size() <= 10 ) {
			Frame frame = frames.get( frames.size() - 1 );
						
			if( frame.canRoll() )
				frame.roll(pins);
			else if( frames.size() < 10 )				
				frames.add( new Frame(pins, frames.size() + 1) );
		}
	}
	
	/**
	 * Get rolls from second and/or third frame.
	 * 
	 * @param frameIndex	frame number referenced to.
	 * @param numOfRolls	number of rolls to get (1 or 2).
	 * @return				sum of pins taken by rolls.
	 */
	private int getNextRolls(int frameIndex, int numOfRolls) {
		int sum = 0;
		
		frameIndex++;
		if( frameIndex >= frames.size()  )
			return sum;
		
		Frame secondFrame = frames.get(frameIndex);
		if( !secondFrame.hasRoll(0) )
			return sum;
		
		sum += secondFrame.getRoll(0);
		numOfRolls--;
		
		if( numOfRolls > 0 ) {
			if( secondFrame.hasRoll(1) )
				sum += secondFrame.getRoll(1);
			else {
				frameIndex++;
				if( frameIndex < frames.size()  ) {
					Frame thirdFrame = frames.get(frameIndex);
					if( thirdFrame.hasRoll(0) ) {
						sum += thirdFrame.getRoll(0);
					}
				}
			}
		}
		
		return sum;
	}

	/**
	 * Calculates current game score.
	 * 
	 * @return result of current game score.
	 */
	public int calculateScore() {
		int totalScore = 0;
		
		for( int i = 0; i < frames.size(); i++ ) {
			Frame frame = frames.get(i);
			totalScore += frame.getScore();
			
			/*
			 * Append score from next rolls if it's not final frame.
			 * If it's final frame, then frame.getScore() counts spare and strike too.
			 */
			if( i < 9 )
			{
				if( frame.hasSpare() )
					totalScore += getNextRolls(i, 1);
				else if( frame.hasStrike() ) {
					totalScore += getNextRolls(i, 2);
				}
			}
		}
		
		return totalScore;
	}
}
