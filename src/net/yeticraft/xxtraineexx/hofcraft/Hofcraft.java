package net.yeticraft.xxtraineexx.hofcraft;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Hofcraft extends JavaPlugin{

	public final Logger log = Logger.getLogger("Minecraft");
	public String prefix = "[HofCraft] ";
	public FileConfiguration config;
	public HofPlayerConfigHandler playerConfig;
	public List<String> validClasses;
	public String defaultClass;
	public int defaultInt;
	public int defaultRes;
	public HofListener myListener;
	
	
	public void onEnable() {
		
		myListener = new HofListener(this);
		playerConfig = new HofPlayerConfigHandler(this);
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
    	validClasses = config.getStringList("classes");
    	
    	if ((validClasses == null) || (validClasses.size() == 0))
    	{
    		// Config file must be empty... lets generate a new one.
    		log.info(prefix + "Configuration File not found. Generating default.");
    		validClasses = new ArrayList<String>();
    		validClasses.add("warrior");
    		validClasses.add("cleric");
    		validClasses.add("rogue");
    		config.set("defaultclass", "undecided");
    		config.set("defaultint", (short)100);
    		config.set("defaultres", (short)90);
    		config.set("classes", validClasses);
    		saveConfig();
    	}
    	else{
    		log.info(prefix + "Existing Configuration file found, loading."); 
    	}
    	
    	defaultClass = config.getString("defaultclass");
    	defaultInt = config.getInt("defaultint");
    	defaultRes = config.getInt("defaultres");
    	
	}

}
