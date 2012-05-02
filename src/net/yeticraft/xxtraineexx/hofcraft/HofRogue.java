package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;
import org.bukkit.entity.Player;

public class HofRogue implements IHofPlayerClass {

	
public int getDamage(HofListener listener, Player attacker){
		
	// HofPlayer hofRogue = listener.activePlayers.get(attacker.getName().toLowerCase());
	int additionalDamage = 0;
	int damageChance = 30; //Later we will incorporate the stats of the rogue to increase this chance.
				
	if (getRandom() < damageChance){
		additionalDamage = additionalDamage + 2;
		attacker.sendMessage("Your rogue training paid off... damage increased by:" + additionalDamage);
	}
	
	return additionalDamage; 
			
}

// This method returns a random int from 0-99. I will use this to determine successful use of skills.
public int getRandom(){
	Random randomGenerator = new Random();
	 int randomInt = randomGenerator.nextInt(100);
	 return randomInt;
}

@Override
public int getMitigation(HofListener listener, Player wounded) {
	// TODO Auto-generated method stub
	return 0;
}
	
}
