***Thanks Cmaaxx or CodedRed for most of this Code!****

package me.Tank203.Stucker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Enderchest implements Listener{
	
    public static Map<UUID, Inventory> menus = new HashMap<UUID, Inventory>();
    
	@EventHandler
    public void onOpenEnderChest(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST))
		if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "" + ChatColor.BOLD + "EnderChest"))
			if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
				if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (menus.containsKey(player.getUniqueId())) {
                    // already has a saved inventory
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 0.8F);
                    player.openInventory(menus.get(player.getUniqueId()));
                    return;
                }
                // doesnt have a saved inventory - so create one
                Inventory inv = Bukkit.createInventory(player, 54, player.getName() + "'s " + ChatColor.GREEN + "" + ChatColor.BOLD + "EnderChest!");
                player.openInventory(inv);
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 0.8F);
                return;
            }
    }
	
	@EventHandler
	public void onOpenEnderChest(BlockPlaceEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "" + ChatColor.BOLD + "EnderChest"))
			if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				event.setCancelled(true);
				if (menus.containsKey(player.getUniqueId())) {
                    // already has a saved inventory
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 0.8F);
                    player.openInventory(menus.get(player.getUniqueId()));
                    return;
                }
                // doesnt have a saved inventory - so create one
                Inventory inv = Bukkit.createInventory(player, 54, player.getName() + "'s " + ChatColor.GREEN + "" + ChatColor.BOLD + "EnderChest!");
                player.openInventory(inv);
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 0.8F);
                return;
			}
	}
	
   
    @EventHandler
    public void onCloseEnderChest(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains(event.getPlayer().getName() + "'s " + ChatColor.GREEN + "" + ChatColor.BOLD + "EnderChest!")) {
            // save inventory to hashmap
            menus.put(event.getPlayer().getUniqueId(), event.getInventory());
        }
    }
}
