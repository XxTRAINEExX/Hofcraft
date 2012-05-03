package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.entity.Player;

public class HofCleric extends HofPlayerClassBase implements IHofPlayerClass {
	
	public HofCleric(HofPlayer player)
	{
		this.player = player;
		this.chanceModifier = 30; //Eventually this will be set based upon teh player object and the cleric object stats
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

	@Override
	public int getBeneficialAdjustment(HofListener listener, Player helper) {
		// TODO Create content here for determining the size of the cleric's heal.  This way this can scale 
		// with level, presence of items, level of spells, etc.
		return 2;
	}	
}
