package net.yeticraft.xxtraineexx.hofcraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HofPlayerConfigHandler {
	

	private static Hofcraft plugin;
	
	
	public HofPlayerConfigHandler(Hofcraft plugin) {
        HofPlayerConfigHandler.plugin = plugin;
    }
	
	public HofPlayer loadPlayer(Player incPlayer){
		
		String player = incPlayer.getName().toLowerCase();
		
		// Attaching to the file on disk.
		File playerConfigFile = new File(plugin.getDataFolder() + "/players", player + ".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
		
		
		// Lets see if the player config exists. If not we need to create it.
		if (!playerConfigFile.exists()){
			HofPlayer newPlayer = new HofPlayer(player, plugin.defaultClass, plugin.defaultInt, plugin.defaultRes);
			savePlayer(newPlayer);
			plugin.log.info(plugin.prefix + "New player yaml file created: " + player);
			return newPlayer;
		}
		
		plugin.log.info(plugin.prefix + "Existing yaml file found: " + player);
		// Extracting their info from the config file.
		String playerClass = playerConfig.getString("class");
		int playerInt = playerConfig.getInt("int");
		int playerRes = playerConfig.getInt("res");
		
		// Creating new object and returning it.
		HofPlayer existingPlayer = new HofPlayer(player, playerClass, playerInt, playerRes);
		return existingPlayer;
	}
	
	public void savePlayer(HofPlayer player){
		
		File playerConfigFile = new File(plugin.getDataFolder() + "/players", player.pName + ".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
		
		playerConfig.set("class", player.pClass);
		playerConfig.set("int", player.pInt);
		playerConfig.set("res", player.pRes);
		
		try {
	        playerConfig.save(playerConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save playerConfig to " + playerConfigFile, ex);
	    }
		
		plugin.log.info(plugin.prefix + "Succesfully saved player to yaml file: " + player.pName);
		
	}
	
	
	
}
