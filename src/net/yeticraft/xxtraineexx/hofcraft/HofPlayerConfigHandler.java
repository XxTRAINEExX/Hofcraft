package net.yeticraft.xxtraineexx.hofcraft;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HofPlayerConfigHandler {
	
	private FileConfiguration playerConfig = null;
	private File playerConfigFile = null;
	private static Hofcraft plugin;
	
	
	public HofPlayerConfigHandler(Hofcraft plugin) {
        HofPlayerConfigHandler.plugin = plugin;
    }
	
	
	public void loadPlayerConfig() {
	    
		playerConfigFile = new File(plugin.getDataFolder(), "playerConfig.yml");
	    playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);

	    
	   // This code pulls in the default yaml file. 
	    InputStream defConfigStream = plugin.getResource("playerConfig.yml");
	    if (defConfigStream != null) {
	    	plugin.log.info(plugin.prefix + "New installation detected: adding default player config file.");
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
		
		/*
		 * Saving out that playerConfig playah... making sweet love to the file system. 
		 */
		
	    if (playerConfig == null || playerConfigFile == null) {
	    return;
	    }

	    try {
	        playerConfig.save(playerConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save playerConfig to " + playerConfigFile, ex);
	    }
	}
	
	public List<String> getPlayers(){
		
		/*
		 * Returns a list<string> of all players currently in the playerConfig.
		 */
		
		List<String> players = playerConfig.getStringList("players");
		return players;
	
	}
	
	
	
	public int getPlayerInt(Player player){
	/*
	 * Returns short value of the players current Intelligence level. If squatingyeti calls this method it will always return 0... due to his intelligence level.
	 */
		
		int playerInt = playerConfig.getInt("players." + player.getName().toLowerCase() + ".int");
		return playerInt;
		
		
	}
	
	public int getPlayerRes(Player player){
		/*
		 * Returns short value of the players current Resilience level.
		 */
		
		int playerRes = playerConfig.getInt("players." + player.getName().toLowerCase() + ".res");
		return playerRes;
		
	}
	
	public String getPlayerClass(Player player){
		/*
		 * Returns string value of the players current class. If shimmerdark calls this method it returns "none" ... because he has no class.
		 */
		
		String playerClass = playerConfig.getString("players." + player.getName().toLowerCase() + ".class");
		return playerClass;
		
	}
	
	public boolean hasPlayer (Player player){
		
		/*
		 * Returns TRUE if the player is already in the playerConfig.
		 */
		
		if (playerConfig.getString("players." + player.toString().toLowerCase()) != null){
			return true;
		}
		return false;
		
	}
	
	public void savePlayer(HofPlayer player){
		
		playerConfig.set("players", player.pName);
		playerConfig.set("players." + player.pName + ".class", player.pClass);
		playerConfig.set("players." + player.pName + ".int", player.pInt);
		playerConfig.set("players." + player.pName + ".res", player.pRes);
		savePlayerConfig();
		
	}
	
	
	
}
