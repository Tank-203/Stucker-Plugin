****** NOTE: Not Done! ******
package me.Tank203.Stucker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Enderchest implements Listener{
	
	public static Map<String, List<Inventory>> enderchest = new HashMap<String, List<Inventory>>();
	
	//Inventory inv2 = Bukkit.createInventory(null, 36, ChatColor.GREEN + "EnderChest! " + ChatColor.DARK_GRAY + "(Lvl: 2)");
	//Inventory inv3 = Bukkit.createInventory(null, 54, ChatColor.GREEN + "EnderChest! " + ChatColor.DARK_GRAY + "(MAX)");
	CommandSender sender;
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST))
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("EnderChest"))
				if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) 
					if (event.getAction() == Action.RIGHT_CLICK_AIR) {
						player.openInventory(Enderchest.einv());
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:block.ender_chest.open block " + player);
					}
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
//		Player player = (Player) event.getPlayer();
		Inventory inv = event.getInventory();
		
		if (inv == Enderchest.einv()) {
			Enderchest.einv().getContents();
			
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("EnderChest"))
			if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				event.setCancelled(true);
				player.openInventory(Enderchest.einv());
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:block.ender_chest.open block " + player);
			}
	}

	public static Inventory einv() {
		Inventory einv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "EnderChest! " + ChatColor.DARK_GRAY + "(Lvl: 1)");
		return einv;
	}
}
