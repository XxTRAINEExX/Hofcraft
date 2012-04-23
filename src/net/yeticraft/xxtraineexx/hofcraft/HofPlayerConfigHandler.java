package net.yeticraft.xxtraineexx.hofcraft;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class HofPlayerConfigHandler {
	
	private FileConfiguration playerConfig = null;
	private File playerConfigFile = null;
	private static Hofcraft plugin;
	public FileConfiguration config;
	
	
	public HofPlayerConfigHandler(Hofcraft plugin) {
        HofPlayerConfigHandler.plugin = plugin;
    }
	
	
	public void loadPlayerConfig() {
	    if (playerConfigFile == null) {
	    playerConfigFile = new File(plugin.getDataFolder(), "customConfig.yml");
	    }
	    playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("customConfig.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        playerConfig.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getPlayerConfig() {
	    if (playerConfig == null) {
	        loadPlayerConfig();
	    }
	    return playerConfig;
	}
	
	public void savePlayerConfig() {
	    if (playerConfig == null || playerConfigFile == null) {
	    return;
	    }
	    try {
	        playerConfig.save(playerConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + playerConfigFile, ex);
	    }
	}
	
}
