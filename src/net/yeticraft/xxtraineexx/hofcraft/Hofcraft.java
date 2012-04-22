package net.yeticraft.xxtraineexx.hofcraft;

import java.util.ArrayList;
import java.util.List;
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
	public List<String> classes;
	
	public void onEnable() {
		
		PluginDescriptionFile pdffile = this.getDescription();
		loadMainConfig();
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
    	
    	// Read the classes from the config file
    	classes = config.getStringList("classes");
    	
    	// Check to see if config
    	if ((classes == null) || (classes.size() == 0))
    	{
    		// If no list was supplied, use the default classes
    		classes = new ArrayList<String>();
    		classes.add("Warrior");
    		classes.add("Claric");
    		config.set("classes", classes);
    		saveConfig();
    	}
	}
	
}
