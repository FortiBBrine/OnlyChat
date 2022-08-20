package me.FortiBrine.OnlyChat.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.FortiBrine.OnlyChat.main.Main;

import me.clip.placeholderapi.PlaceholderAPI;

public class ChatListener implements Listener {

	private Main plugin;
	public ChatListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		if (event.isCancelled()) return;
		if (plugin.getPause()) {
			event.setCancelled(true);
			return;
		}
		
		Player player = event.getPlayer();
		
		FileConfiguration config = plugin.getConfig();
		String chatFormatter = config.getString("chat");
		String message = event.getMessage();
		
		if (config.getBoolean("caps")) {
			int caps = 0;
			
			for (char c : message.toCharArray()) {
				if (Character.isUpperCase(c)) caps ++;
				else caps --;
			}
			
			if (caps > 0 && !(player.hasPermission(plugin.getDescription().getPermissions().get(1)))) {
				message = message.toLowerCase();
			}
		}
			
		if (player.hasPermission(plugin.getDescription().getPermissions().get(3))) {
			message = message.replace("&", "\u00a7");
		}
		
		chatFormatter = PlaceholderAPI.setPlaceholders(player, chatFormatter);
		chatFormatter = chatFormatter.replace("%player", player.getDisplayName());
		chatFormatter = chatFormatter.replace("%user", player.getName());
		chatFormatter = chatFormatter.replace("%message", message);

		event.setFormat(chatFormatter);
	}
}
