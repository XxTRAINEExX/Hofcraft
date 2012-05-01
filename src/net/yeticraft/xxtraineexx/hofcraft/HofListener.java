package net.yeticraft.xxtraineexx.hofcraft;


import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class HofListener implements Listener{

	public static Hofcraft plugin;
	HashMap<String, HofPlayer> activePlayers = new HashMap<String, HofPlayer>();

	public HofListener(Hofcraft plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		HofListener.plugin = plugin;
	}
	
	
	public void onPluginEnable (PluginEnableEvent event) {
		plugin.log.info(("Plugin detected: " + event.getPlugin().toString()));
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		// Loading the player.
		HofPlayer hofPlayer = plugin.playerConfig.loadPlayer(e.getPlayer());

		// Lets get this object stored in a hashtable.. you know we going to call this later...
		activePlayers.put(hofPlayer.pName, hofPlayer);
		plugin.log.info(plugin.prefix + "Player added to hashmap: " + hofPlayer.pName);
		
		// Player loaded and ready to rock... lets rock out of here.
		return;
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		// I don't like them bosses lingering...
		String pName = e.getPlayer().getName().toLowerCase();
		activePlayers.remove(pName);
		plugin.log.info(plugin.prefix + "Player removed from hashmap: " + pName);
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent e) {
		
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent e) {	
		
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerToggleSprint(PlayerToggleSprintEvent e) {	
		
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {	
		
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityCombustEvent(EntityCombustEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {	
	
		//defining wounded and attacker
		Entity wounded = e.getEntity();
		Entity attacker = e.getDamager();
		
		// Making sure the attacker is a player
		if (!(attacker instanceof Player)) return;
		
		// Making sure the wounded is a player
		if (!(wounded instanceof Player)) return;

		// Finding the attacker if this event was triggered by a projectile.
		if (attacker instanceof Projectile) attacker = ((Projectile)attacker).getShooter();
	
		// Making sure the event hasnt been cancelled already
		if (e.isCancelled()) return;
		
		// Making sure the attacker didnt wound him/herself (like shooting an arrow up in the air and landing on their own head)
		if (attacker.equals(wounded)) return;
		
		// 	I believe we've eliminated all irrelevant scenarios, this must be PVP. Let's cast the entities to players.
		Player attackPlayer = (Player)attacker;
		Player woundedPlayer = (Player)wounded;
		
		// Pulling their data from the hashmap
		HofPlayer hofAttacker = activePlayers.get(attackPlayer.getName().toLowerCase());
		HofPlayer hofWounded = activePlayers.get(woundedPlayer.getName().toLowerCase());
	
		boolean healAllowed = true;
		int warriorMitigation = 0;
		int rogueDamage = 0;
		int clericHeal = 0;
				
		// Determining if rogue damage should be applied
		if (hofAttacker.getpClass().equalsIgnoreCase("rogue")){

			HofRogue rogue = new HofRogue();
			rogueDamage = rogue.getDamage(this, attackPlayer);

		}
		
		// Determining if warrior mitigation should be applied
		if (hofWounded.getpClass().equalsIgnoreCase("warrior")){

			HofWarrior warrior = new HofWarrior();
			warriorMitigation = warrior.getMitigation(this, woundedPlayer);
			healAllowed=false;

		}
		
		// Determining if cleric healing should be applied
		if (hofWounded.getpClass().equalsIgnoreCase("cleric") && healAllowed){

			HofCleric cleric = new HofCleric();
			clericHeal = cleric.getHeal(this, woundedPlayer);
					
		}
		
		// applying all of the modifiers to get the final damage. If we end up less than 0 we correct it.
		int finalDamage = (e.getDamage() + rogueDamage) - warriorMitigation - clericHeal;
		if (finalDamage < 0) finalDamage = 0;  
		
		// set the final damage and let it rain
		e.setDamage(finalDamage);
	
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageEvent(EntityDamageEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeathEvent(EntityDeathEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityExplodeEvent(EntityExplodeEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityInteractEvent(EntityInteractEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerRespawnEvent(PlayerRespawnEvent e) {	
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerTeleportEvent(PlayerTeleportEvent e) {	
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	
	
	
	
	
	
	
	
}
