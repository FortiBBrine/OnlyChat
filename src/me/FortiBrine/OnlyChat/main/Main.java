package me.FortiBrine.OnlyChat.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.FortiBrine.OnlyChat.commands.PluginCommand;
import me.FortiBrine.OnlyChat.listeners.ChatListener;

public class Main extends JavaPlugin {

	private boolean pause = false;
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public boolean getPause() {
		return this.pause;
	}
	
	@Override
	public void onEnable() {
		File config = new File(this.getDataFolder()+File.separator+"config.yml");
		if (!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			this.saveDefaultConfig();
		}
		
		Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
	
		this.getCommand("onlychat").setExecutor(new PluginCommand(this));
	}
}
