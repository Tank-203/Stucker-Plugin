package me.Tank203.Stucker;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Enderchest implements Listener{
	
	public static Map<String, ItemStack[]> enderchest = new HashMap<String, ItemStack[]>();
	Inventory einv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "EnderChest!");
	CommandSender sender;
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST))
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("EnderChest"))
				if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) 
					if (event.getAction() == Action.RIGHT_CLICK_AIR) {
						if(player.getName().contains(player.getName())) {
							@SuppressWarnings({ "unlikely-arg-type", "unused" })
							ItemStack[] inv = enderchest.get(einv);
							player.openInventory(einv);
							player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 3.0F, 0.5F);
						}
					}
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
			enderchest.put(player.getName(), einv.getContents());
			player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 3.0F, 0.5F);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("EnderChest"))
			if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				event.setCancelled(true);
				player.openInventory(einv);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:block.ender_chest.open block " + player);
			}
	}
}
