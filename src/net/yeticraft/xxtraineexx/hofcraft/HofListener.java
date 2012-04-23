package net.yeticraft.xxtraineexx.hofcraft;


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
	
	public HofListener(Hofcraft plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		HofListener.plugin = plugin;
	}
	
	
	public void onPluginEnable (PluginEnableEvent event) {
		plugin.log.info(("Plugin detected: " + event.getPlugin().toString()));
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent e) {
		
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
