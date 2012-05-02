package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.entity.Player;

public interface IHofPlayerClass {
	public int getDamage(HofListener listener, Player attacker);
	public int getMitigation(HofListener listener, Player wounded);
}
