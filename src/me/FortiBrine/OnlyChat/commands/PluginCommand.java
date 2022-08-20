package me.FortiBrine.OnlyChat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.FortiBrine.OnlyChat.main.Main;

public class PluginCommand implements CommandExecutor {
	
	private Main plugin;
	public PluginCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		FileConfiguration config = plugin.getConfig();
				
		if (args.length < 1) {
			
			for (String message : config.getStringList("messages.command_usage")) {
				sender.sendMessage(message.replace("&", "\u00a7"));
			}
			
			return true;
		}
		
		if (args[0].equals("reload")) {
			
			if (!sender.hasPermission(plugin.getDescription().getPermissions().get(2))) {
				sender.sendMessage(config.getString("messages.non_permission").replace("&", "\u00a7"));
				return true;
			}
			
			plugin.reloadConfig();
			sender.sendMessage(config.getString("messages.reload").replace("&", "\u00a7"));
			
		}
		
		if (args[0].equals("clear")) {
			
			if (!sender.hasPermission(plugin.getDescription().getPermissions().get(4))) {
				sender.sendMessage(config.getString("messages.non_permission").replace("&", "\u00a7"));
				return true;
			}
			
			for (Player player : Bukkit.getOnlinePlayers()) {
				for (int i = 0; i < 100; i++) {
					player.sendMessage(" ");
				}
				
				String message = config.getString("messages.clear");
				
				message = message.replace("%player", sender.getName());
				message = message.replace("%user", sender.getName());
				message = message.replace("&", "\u00a7");
				
				player.sendMessage(message);
			}
			
		}
		
		if (args[0].equals("pause")) {
			if (!sender.hasPermission(plugin.getDescription().getPermissions().get(5))) {
				sender.sendMessage(config.getString("messages.non_permission").replace("&", "\u00a7"));
				return true;
			}
			
			plugin.setPause(true);
			sender.sendMessage(config.getString("messages.pause").replace("&", "\u00a7"));			
			
		}
		
		if (args[0].equals("resume")) {
			if (!sender.hasPermission(plugin.getDescription().getPermissions().get(6))) {
				sender.sendMessage(config.getString("messages.non_permission").replace("&", "\u00a7"));
				return true;
			}
			
			plugin.setPause(false);
			sender.sendMessage(config.getString("messages.resume").replace("&", "\u00a7"));			
			
		}
		
		return true;
	}
	
}
