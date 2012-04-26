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
		
		String pName = e.getPlayer().getName().toLowerCase();
		
		// Check if they are in the player config
		if (!plugin.playerConfig.hasPlayer(e.getPlayer())){
			
			// Ruh roh... looks like they arent in the config... lets load them some defaults.
			HofPlayer newPlayer = new HofPlayer(pName, plugin.defaultClass, plugin.defaultInt, plugin.defaultRes);
			activePlayers.put(pName, newPlayer);
			plugin.playerConfig.savePlayer(newPlayer);
			plugin.log.info(plugin.prefix + "New player added to playerconfig: " + pName);
			return;
			
		}

		// Loading up some player attributes hoss
		String pClass = plugin.playerConfig.getPlayerClass(e.getPlayer());
		int pInt = plugin.playerConfig.getPlayerInt(e.getPlayer());
		int pRes = plugin.playerConfig.getPlayerRes(e.getPlayer());
				
		// Creating objects like a boss... this one will hold all them player attributes.
		HofPlayer existingPlayer = new HofPlayer(pName, pClass,pInt,pRes);

		// Lets get this object stored in a hashtable.. you know we going to call this later...
		activePlayers.put(pName, existingPlayer);
		
		// Player loaded and ready to rock... lets rock out of here.
		return;
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		// I don't like them bosses lingering...
		String pName = e.getPlayer().getName().toLowerCase();
		activePlayers.remove(pName);
		
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
	
		Entity wounded = e.getEntity();
		Entity attacker = e.getDamager();
		if (attacker instanceof Projectile) attacker = ((Projectile)attacker).getShooter();
	
		if (e.isCancelled()) return;
		plugin.log.info("attack not cancelled");
		
		
		if (attacker.equals(wounded)) return;
		plugin.log.info("attacker was not also the wounded person");
		
		if (!(wounded instanceof Player)) return;
		plugin.log.info("wounded person was a player");
		
		if (!(attacker instanceof Player)) return;
		plugin.log.info("attacker person was a player");
	
		// 	We should be working with players... Lets cast things
		Player attackPlayer = (Player)attacker;
		Player woundedPlayer = (Player)wounded;
		
		attackPlayer.sendMessage("You are the attacker");
		woundedPlayer.sendMessage("You are the wounded");
		
		attackPlayer.sendMessage("Initial Damage" + e.getDamage());
		
		// Pulling their data from the hashmap
		HofPlayer hofAttacker = activePlayers.get(attackPlayer.getName().toLowerCase());
		HofPlayer hofWounded = activePlayers.get(woundedPlayer.getName().toLowerCase());
	
		
		// Warrior Modifier
		if (hofAttacker.getpClass().equalsIgnoreCase("warrior")){
			attackPlayer.sendMessage("you are a warrior");
			int warriorDamage = e.getDamage() * 4;
			attackPlayer.sendMessage("enhanced damage" + warriorDamage);
			e.setDamage(warriorDamage);
		}
		
		
	
	
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
