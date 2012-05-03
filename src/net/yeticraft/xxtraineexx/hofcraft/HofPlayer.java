package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HofPlayer {

	String pName;
	String pClass;
	int pInt;
	int pRes;
	IHofPlayerClass myClass;
	
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
	
	public int getHealedByNearbyCleric(HofListener listener, Player wounded)
	{	
		int healedAmount = 0;
		for (Player nearby : Bukkit.getServer().getOnlinePlayers()) {	
			if (nearby.equals(wounded)) continue;
			if (nearby.getLocation().distance(wounded.getLocation()) > 7) continue;
			HofPlayer possibleCleric = listener.activePlayers.get(nearby.getName().toLowerCase());
			if (!possibleCleric.getpClass().equalsIgnoreCase("cleric")) continue;
			if (!possibleCleric.myClass.performAction(listener)) continue;
			healedAmount = possibleCleric.myClass.getBeneficialAdjustment(listener, nearby);
			wounded.sendMessage(nearby.getName() + " healed you for: "  + healedAmount);
			nearby.sendMessage("You healed: " + wounded.getName() + "for: " + healedAmount);
			break;	
		}
		return healedAmount;	
	}	

	
}
