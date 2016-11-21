package me.boops.randomcrate.events;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import me.boops.randomcrate.Main;
import me.boops.randomcrate.config.Config;

public class PlayerMovment implements Listener {
	
	Config config = new Config();
	
	@EventHandler
	public void Crate(PlayerMoveEvent event){
		
		int time = (int) (System.currentTimeMillis() / 1000);
		Random random = new Random();
		
		//Check if their is a time
		if((int) config.Get("crate.time.next") < time && !Boolean.parseBoolean(config.Get("crate.out").toString())){
			
			//Time to drop!
			
			//Get Max/Min Cords
			int MinX = (int) config.Get("crate.loc.min.x");
			int MinZ = (int) config.Get("crate.loc.min.z");
			
			int MaxX = (int) config.Get("crate.loc.max.z");
			int MaxZ = (int) config.Get("crate.loc.max.z");
			
			//Generate random cords
			int cordX = random.nextInt(Math.abs(MaxX) + Math.abs(MinX)) - Math.abs(MinX);
			int cordZ = random.nextInt(Math.abs(MaxZ) + Math.abs(MinZ)) - Math.abs(MinZ);
			
			World world = Main.plugin.getServer().getWorld("world");
			
			int cordY = world.getHighestBlockAt(cordX, cordZ).getY();
			
			Main.plugin.getServer().broadcastMessage("A crate has been placed at X:" + Integer.toString(cordX) + " Y:" + Integer.toString(cordY) + " Z:" + Integer.toString(cordZ));
			
			//Save current Location of the crate
			config.Set("crate.loc.current.x", cordX);
			config.Set("crate.loc.current.y", cordY);
			config.Set("crate.loc.current.z", cordZ);
			
			//Get The Block
			Location loc = world.getHighestBlockAt(cordX, cordZ).getLocation();
			
			//Place the block
			world.getBlockAt(loc).setType(Material.SKULL);
			
			Skull skull = (Skull) world.getBlockAt(loc).getState();
			skull.setOwner("MHF_Question");
			skull.update(true);
			
			//Now Set current block status
			config.Set("crate.out", true);
			
		}
	}
}
