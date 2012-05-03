package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;

public abstract class HofPlayerClassBase {
	HofPlayer player;
	int chanceModifier;
	// This method returns a random int from 0-99. I will use this to determine successful use of skills.
	public int getRandom()
	{
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(100);
		 return randomInt;
	}
	
	public boolean performAction(HofListener listener) {
		return this.getRandom() < this.chanceModifier;
	}
}
