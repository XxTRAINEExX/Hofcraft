package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.entity.Player;

public interface IHofPlayerClass {
	//Pass in the person who is doing damage and this will return how much they do.
	public int getDamage(HofListener listener, Player attacker);
	//Pass in the person being attacked and this will return how much they reduce incoming damage by.
	public int getMitigation(HofListener listener, Player wounded);
	//Less obvious, pass in the player who is available to assist and the modification to the check is returned.
	//	Ex: Cleric - heal will reduce damage
	//      Warrior - could return a 0 or 1 for whether they jump in and take all the damage.
	//      Rogue - could assist in detecting a trap, reducing a mob noticing other players, etc
	public int getBeneficialAdjustment(HofListener listener, Player helper);
	
	public boolean performAction(HofListener listener);
}
