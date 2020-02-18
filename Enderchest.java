****** NOTE: Not Done! ******
package me.Tank203.Stucker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Enderchest implements Listener{
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST))
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("EnderChest"))
				if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) 
					if (event.getAction() == Action.RIGHT_CLICK_AIR) {
						
					}
	}
}
