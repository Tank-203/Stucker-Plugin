package me.Tank203.Stucker;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AC implements Listener{

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS))
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Advanced Compass"))
				if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) 
					if (event.getAction() == Action.RIGHT_CLICK_AIR) {
						int xcoord = player.getLocation().getBlockX();
					 	int ycoord = player.getLocation().getBlockY();
					 	int zcoord = player.getLocation().getBlockZ();
					 	String name = player.getName();
					 	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "--- " + name + " Coordinates ---");
					 	player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "X: " + xcoord);
					 	player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Y: " + ycoord);
					 	player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Z: " + zcoord);
					}
	}
	
}
