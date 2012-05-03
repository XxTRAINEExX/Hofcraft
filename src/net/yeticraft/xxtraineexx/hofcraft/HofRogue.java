package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.entity.Player;

public class HofRogue extends HofPlayerClassBase implements IHofPlayerClass {
	
	public HofRogue(HofPlayer player) 
	{
		this.player = player;
		this.chanceModifier = 30;
	}
	
public int getDamage(HofListener listener, Player attacker){
	int additionalDamage = 0;
				
	if (this.performAction(listener)) {
		additionalDamage = additionalDamage + 2;
		attacker.sendMessage("Your rogue training paid off... damage increased by:" + additionalDamage);
	}
	return additionalDamage; 			
}

@Override
public int getMitigation(HofListener listener, Player wounded) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getBeneficialAdjustment(HofListener listener, Player helper) {
	// TODO Auto-generated method stub
	return 0;
}

	
}
