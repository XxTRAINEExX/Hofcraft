package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;
import org.bukkit.entity.Player;

public class HofWarrior {

public int getMitigation(HofListener  listener, Player wounded){
		
	// HofPlayer hofWarrior = listener.activePlayers.get(wounded.getName().toLowerCase());
	int mitigation = 0;
	int mitigationChance = 30; //Later we will modify this chance based on attributes pulled from the hofWarrior object

		if (getRandom() < mitigationChance){ 
			mitigation = mitigation + 2;
			wounded.sendMessage("Your warrior training paid off... damage avoided:" + mitigation);
		}		

		return mitigation;
}
	
	// This method returns a random int from 0-99. I will use this to determine successful use of skills.
	public int getRandom(){
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(100);
		 return randomInt;
	}
	
}
