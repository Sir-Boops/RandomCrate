package me.boops.randomcrate.events;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.boops.randomcrate.Main;
import me.boops.randomcrate.config.Config;

public class PlayerInteract implements Listener {

	@EventHandler
	public void Interact(PlayerInteractEvent event) {

		// Check if it's the crate
		Block block = event.getClickedBlock();
		Config config = new Config();
		Action action = event.getAction();
		Random random = new Random();
		int time = (int) (System.currentTimeMillis() / 1000);

		if (!action.equals(Action.LEFT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_AIR)) {
			if (block.getType().equals(Material.SKULL)) {
				if ((int) config.Get("crate.loc.current.x") == block.getX()) {
					if ((int) config.Get("crate.loc.current.y") == block.getY()) {
						if ((int) config.Get("crate.loc.current.z") == block.getZ()) {

							// It's the crate
							Player player = event.getPlayer();

							block.setType(Material.AIR);

							// Give the player the loot
							List<String> items = (List<String>) config.Get("crate.contents");
							List<Integer> amount = (List<Integer>) config.Get("crate.amount");

							for (int i = 0; items.size() > i; i++) {
								player.getInventory()
										.addItem(new ItemStack(Material.getMaterial(items.get(i)), amount.get(i)));
							}
							
							//Get random time
							//Get Min And Max amounts
							//of time
							int MinTime = (int) config.Get("crate.time.min");
							int MaxTime = (int) config.Get("crate.time.max");
							
							//Generate a random amount of time
							int NextTime = (time + (random.nextInt(MaxTime - MinTime) + MinTime));
							
							//Save to config!
							config.Set("crate.time.next", NextTime);
							

							// Set the crate as found
							config.Set("crate.out", false);
							
							//Announce it!
							Main.plugin.getServer().broadcastMessage(player.getDisplayName() + " Has found the crate!");
						}
					}
				}
			}

		}
	}
}
