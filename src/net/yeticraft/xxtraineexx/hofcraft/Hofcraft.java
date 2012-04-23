package net.yeticraft.xxtraineexx.hofcraft;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Hofcraft extends JavaPlugin{

	public final Logger log = Logger.getLogger("Minecraft");
	public String prefix = ChatColor.AQUA + "[HofCraft] " + ChatColor.WHITE;
	public FileConfiguration config;
	public final HofPlayerConfigHandler playerConfig = new HofPlayerConfigHandler(this);
	
	
	
	public void onEnable() {
		
		new HofListener(this);
		PluginDescriptionFile pdffile = this.getDescription();
		loadMainConfig();
		playerConfig.loadPlayerConfig();
		CommandExecutor HofcraftCommandExecutor = new HofcraftCommand(this);
		getCommand("hofcraft").setExecutor(HofcraftCommandExecutor);
    	getCommand("hc").setExecutor(HofcraftCommandExecutor);  	
    	log.info(prefix + " " + pdffile.getVersion() + " Enabled"); 	
    	
    	
	}
	
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription(); 
		log.info(prefix + " " + pdffile.getVersion() + " Disabled"); 
	}
	
	
	public void loadMainConfig(){
		// Read the config file
    	config = getConfig();
    	// Future config checking goes here.
	}

}
