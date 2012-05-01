package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HofCleric {

public int getHeal(HofListener  listener, Player wounded){
		
	int healedAmount = 0;
		
	for (Player nearby : Bukkit.getServer().getOnlinePlayers()) {
			
		if (nearby.equals(wounded)) continue;
		
		if (nearby.getLocation().distance(wounded.getLocation()) > 7) continue;
			
		HofPlayer possibleCleric = listener.activePlayers.get(nearby.getName().toLowerCase());
			
		if (!possibleCleric.getpClass().equalsIgnoreCase("cleric")) continue;
			
		int healChance = 30; //Later we will modify this chance based on attributes pulled from the possibleCleric object
			
		if (getRandom() > healChance) continue;
			
		healedAmount = healedAmount + 2;
		wounded.sendMessage(nearby.getName() + " healed you for: "  + healedAmount);
		nearby.sendMessage("You healed: " + wounded.getName() + "for: " + healedAmount);
		break;	
	}
		
	return healedAmount;
		
}
	
	
// This method returns a random int from 0-99. I will use this to determine successful use of skills.
public int getRandom(){
	 Random randomGenerator = new Random();
	 int randomInt = randomGenerator.nextInt(100);
	 return randomInt;
}
	
}
