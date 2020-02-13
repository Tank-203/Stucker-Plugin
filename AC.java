package me.Tank203.Stucker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AC implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("ac")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Join the minecraft server if you want to do that!");
				return true;
			}
			Player player = (Player) sender;
			ItemStack fee1 = new ItemStack(Material.DIAMOND);
			ItemStack fee2 = new ItemStack(Material.COMPASS);
			ItemStack air = new ItemStack(Material.AIR);
			fee1.setAmount(4);
			fee2.setAmount(1);
			if(((player.getInventory().getItemInMainHand().isSimilar(fee1)) && (player.getInventory().getItemInOffHand().isSimilar(fee2))) || 
					player.getInventory().getItemInMainHand().isSimilar(fee2) && (player.getInventory().getItemInOffHand().isSimilar(fee1))) {
				
				player.getInventory().removeItem(fee1);
				player.getInventory().removeItem(fee2);
				player.getInventory().setItemInOffHand(air);				
				if (player.getInventory().firstEmpty() == -1) {
					Location loc = player.getLocation();
					World world = player.getWorld();
					world.dropItemNaturally(loc, getItem());
					player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You used your extra compass and diamonds to make something advanced.");
					return true;
				}
				
				player.getInventory().addItem(getItem());
				player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You used your extra compass and diamonds to make something advanced.");
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getItem() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta meta = compass.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Advanced Compass");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Some say this advanced item was forged by witches...");
		lore.add("");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		compass.setItemMeta(meta);
		
		return compass;
	}
	
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
