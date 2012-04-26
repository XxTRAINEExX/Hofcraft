package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HofcraftCommand implements CommandExecutor{

	
	private final Hofcraft plugin;
	
	public HofcraftCommand(Hofcraft plugin) {
		this.plugin = plugin;
	}

	enum SubCommand {
		HELP,
		CLASSES,
		JOIN,
		LEAVE,
		UNKNOWN;
		
		private static SubCommand toSubCommand(String str) {
			try {
				return valueOf(str.toUpperCase());
			} catch (Exception ex) {
				return UNKNOWN;
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
				
  	if (args.length == 0) {
  			sender.sendMessage(plugin.prefix + "Try /" + command.getName() + " HELP");
			return true;
    	}
  	if (args.length > 2) {
  		sender.sendMessage(plugin.prefix + "Looks like you typed too many parameters.");
		return true;
	}
	 	
  	
    	switch (SubCommand.toSubCommand(args[0].toUpperCase())) {
	    	case HELP:
	    		sender.sendMessage(ChatColor.DARK_AQUA + "HofCraft Help Menu");
	    		sender.sendMessage(ChatColor.DARK_AQUA + "==================");
	    		sender.sendMessage(plugin.prefix + ChatColor.DARK_AQUA + " /" + command.getName() + " HELP: Shows this help page");
	    		sender.sendMessage(plugin.prefix + ChatColor.DARK_AQUA +  " /" + command.getName() + " CLASSES: Lists all current classes.");
	    		sender.sendMessage(plugin.prefix + ChatColor.DARK_AQUA +  " /" + command.getName() + " JOIN <class>: Joins a given class.");
	    		sender.sendMessage(plugin.prefix + ChatColor.DARK_AQUA +  " /" + command.getName() + " LEAVE <class>: Leaves a given class.");
	    		break;
	    	case CLASSES:
	    		
	    		sender.sendMessage(plugin.prefix + "Available classes: ");
	    		
	    		for (int i = 0; i < plugin.validClasses.size(); i++){
	    			sender.sendMessage(plugin.prefix + plugin.validClasses.get(i));
	    		}
	    		
	    		break;

	    	case JOIN:
	    		
	    		if (args.length == 1)
	    		{
	    			sender.sendMessage("Please specify a class to join.");
	    			return true;
	    		}
	    		if (joinClass(sender, args[1]))
	    		{
	    			sender.sendMessage("You are now a " + args[1] + ".");
	    		}
	    		
	    		break;
	    	case LEAVE:
	    		
	    		if (args.length == 1)
	    		{
	    			sender.sendMessage("Please specify a class to leave.");
	    			return true;
	    		}
	    		if (leaveClass(sender, args[1]))
	    		{
	    			sender.sendMessage("You are no longer a " + args[1] + ".");
	    		}
	    		
	    		break;
	    		
	    	case UNKNOWN:
				sender.sendMessage("Unknown command. Use /hc HELP to list available commands.");
    	}
    	
		return true;
	
		
	
	}
	
	public boolean joinClass(CommandSender sender, String pClass){
		
		// Did they enter a valid class?
		if (!plugin.validClasses.contains(pClass.toLowerCase())){
			sender.sendMessage("You didn't enter a valid class.");
			return false;
		}
		
		// Setting their class in the listener hashmap and saving out to the yaml file.
		HofPlayer hofPlayer = plugin.myListener.activePlayers.get(sender.getName().toLowerCase());
		hofPlayer.setpClass(pClass);
		plugin.playerConfig.savePlayer(hofPlayer);
		return true;
		
	}
	
	public boolean leaveClass(CommandSender sender, String pClass){
		
		// Did they enter a valid class?
		if (!plugin.validClasses.contains(pClass.toLowerCase())){
			sender.sendMessage("You didn't enter a valid class.");
			return false;
		}
		
		// Setting their class in the listener hashmap and saving out to the yaml file.
		HofPlayer hofPlayer = plugin.myListener.activePlayers.get(sender.getName().toLowerCase());
		hofPlayer.setpClass("undecided");
		plugin.playerConfig.savePlayer(hofPlayer);
		return true;
		
	}
	
}
