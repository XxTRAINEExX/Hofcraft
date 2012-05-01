package net.yeticraft.xxtraineexx.hofcraft;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HofPlayer {

	String pName;
	String pClass;
	int pInt;
	int pRes;
	
	public HofPlayer (String incPlayer, String incClass, int incInt, int incRes){
		pName = incPlayer;
		pClass = incClass;
		pInt = incInt;
		pRes = incRes;
		
	}
	
	public String getpName(){
		return pName;
	}
	
	public String getpClass(){
		return pClass;
	}
	
	public int getInt(){
		return pInt;
	}
	
	public int getRes(){
		return pRes;
	}
	
	public boolean setpName(String incName){
		pName = incName.toLowerCase();
		return true;
	}
	
	public boolean setpClass(String incClass){
		pClass = incClass.toLowerCase();
		return true;
	}
	
	public boolean setInt(short incInt){
		pInt = incInt;
		return true;
	}
	
	public boolean setRes(short incRes){
		pRes = incRes;
		return true;
	}


	// Primary mitigation method. Right now this only impacts warriors and clerics.
	public int getMitigation(Player wounded, HofListener  listener){
		
		int modifiedMitigation = 0;
		
		// Warrior damage code
		if (pClass.equalsIgnoreCase("warrior")){
			// 30% of the time we will fire the warrior damage mitigation code
			if (getRandom() < 30){ 
				modifiedMitigation = modifiedMitigation + 2;
				wounded.sendMessage("Your warrior training paid off... damage avoided:" + modifiedMitigation);
			}		
		}
		
		// cleric healing Code
		for (Player nearby : Bukkit.getServer().getOnlinePlayers()) {
			
			if (modifiedMitigation > 0) continue;
			
			if (nearby.equals(wounded)) continue;
			
			if (nearby.getLocation().distance(wounded.getLocation()) > 7) continue;
			
			if (!listener.activePlayers.containsKey(nearby.getName().toLowerCase())) continue;
			
			HofPlayer possibleCleric = listener.activePlayers.get(nearby.getName().toLowerCase());
			
			if (!possibleCleric.getpClass().equalsIgnoreCase("cleric")) continue;
			
			if (getRandom() > 30) continue;
			
			modifiedMitigation = modifiedMitigation + 2;
			wounded.sendMessage(nearby.getName() + " healed you for: "  + modifiedMitigation);
			nearby.sendMessage("You healed: " + wounded.getName() + "for: " + modifiedMitigation);
			break;	
			
		}
		
	return modifiedMitigation;
		
	}
	
	
	// Primary damage method. Right now this only impacts rogues
	public int getDamage(int initialDamage, HofListener listener, Player attacker){
		
		int modifiedDamage = initialDamage;
		
		if (pClass.equalsIgnoreCase("rogue")){
		
			// 30% of the time we will fire the rogue damage code
			if (getRandom() < 30){
				modifiedDamage = (int)(Math.ceil(((double) initialDamage) * 2.0));
				attacker.sendMessage("Your rogue training paid off... damage increased by:" + (modifiedDamage-initialDamage));
			}
			
		}
		
		return modifiedDamage; 
		
	}
	
	
	
	// This method returns a random int from 0-99. I will use this to determine successful use of skills.
	public int getRandom(){
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(100);
		 return randomInt;
	}
	
	
}
