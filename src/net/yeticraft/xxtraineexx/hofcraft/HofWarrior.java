package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.entity.Player;

public class HofWarrior extends HofPlayerClassBase implements IHofPlayerClass {
	
public HofWarrior(HofPlayer player)
{
	this.player = player;
	this.chanceModifier = 30;
}

public int getMitigation(HofListener listener, Player wounded){
		
	// HofPlayer hofWarrior = listener.activePlayers.get(wounded.getName().toLowerCase());
	int mitigation = 0;
		if (this.performAction(listener)) { 
			mitigation = mitigation + 2;
			wounded.sendMessage("Your warrior training paid off... damage avoided:" + mitigation);
		}		

		return mitigation;
}

	@Override
	public int getDamage(HofListener listener, Player attacker) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBeneficialAdjustment(HofListener listener, Player helper) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
