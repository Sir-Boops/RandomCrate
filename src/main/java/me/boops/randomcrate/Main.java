package me.boops.randomcrate;

import org.bukkit.plugin.java.JavaPlugin;

import me.boops.randomcrate.config.Config;
import me.boops.randomcrate.events.PlayerInteract;
import me.boops.randomcrate.events.PlayerMovment;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	Config config = new Config();
	
	@Override
	public void onEnable() {
		
		//Set this class as the plugin
		plugin = this;
		
		//Try and load the config
		config.Load();
		
		//Register Events
		this.getServer().getPluginManager().registerEvents(new PlayerMovment(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		
		System.out.println("Hi!");

	}

	@Override
	public void onDisable() {
		
	}

}
