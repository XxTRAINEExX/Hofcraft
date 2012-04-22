package net.yeticraft.xxtraineexx.hofcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class HofcraftCommand implements CommandExecutor{

	
	private final Hofcraft plugin;
	
	public HofcraftCommand(Hofcraft plugin) {
		this.plugin = plugin;
	}

	enum SubCommand {
		HELP,
		CLASSES,
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
	    		break;
	    	case CLASSES:
	    		List<String> classes = plugin.classes;
	    		sender.sendMessage(plugin.prefix + "Available classes: ");
	    		for (int i = 0; i < classes.size(); i++)
	    		{
	    			sender.sendMessage(plugin.prefix + classes.get(i));
	    		}
	    		break;

	    	case UNKNOWN:
				sender.sendMessage("Unknown command. Use /hc HELP to list available commands.");
    	}
    	
		return true;
	}
	
	
}
