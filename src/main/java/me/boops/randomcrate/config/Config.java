package me.boops.randomcrate.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import me.boops.randomcrate.Main;

public class Config {

	public void Load() {

		if (!Main.plugin.getDataFolder().exists()) {
			Main.plugin.getDataFolder().mkdirs();
		}

		try {

			// Try and load the default config
			File file = new File(Main.plugin.getDataFolder(), "config.yml");

			if (!file.exists()) {
				Main.plugin.saveDefaultConfig();

				// Add params to the config
				Set("crate.loc.min.x", 0);
				Set("crate.loc.min.z", 0);
				Set("crate.loc.max.x", 100);
				Set("crate.loc.max.z", 100);

				Set("crate.time.min", 100);
				Set("crate.time.max", 120);
				
				Set("crate.drop.time", 0);
				Set("crate.time.next", 0);
				
				Set("crate.out", false);
				
				List<String> items = Arrays.asList("DIRT");
				List<Integer> amount = Arrays.asList(1);
				
				Set("crate.contents", items);
				Set("crate.amount", amount);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Set Somthing Is The Config
	public void Set(String path, Object data) {
		Main.plugin.getConfig().set(path, data);
		Main.plugin.saveConfig();
	}

	public Object Get(String path) {
		return Main.plugin.getConfig().get(path);
	}

}
