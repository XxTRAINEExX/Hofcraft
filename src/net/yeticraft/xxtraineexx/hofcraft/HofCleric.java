package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;
import org.bukkit.entity.Player;

public class HofCleric implements IHofPlayerClass {
	
	// This method returns a random int from 0-99. I will use this to determine successful use of skills.
	public int getRandom()
	{
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(100);
		 return randomInt;
	}
	
	@Override
	public int getDamage(HofListener listener, Player attacker) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int getMitigation(HofListener listener, Player wounded) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}
