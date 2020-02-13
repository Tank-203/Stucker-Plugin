package me.Tank203.Stucker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.util.internal.ThreadLocalRandom;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Main extends JavaPlugin implements Listener{

	public static Economy econ = null;
	public Inventory inv;
	List<Inventory> invs = new ArrayList<Inventory>();
	public static ItemStack[] contents;
	private int itemIndex = 0;
	
	@Override
	public void onEnable() {
		if (!setupEconomy()) {
			getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new AC(), this);
		this.getCommand("Launch").setExecutor(new Launch());
		this.getCommand("Chad").setExecutor(new Chad());
		this.getCommand("Shelp").setExecutor(new Help());
		this.getCommand("Ac").setExecutor(new AC());
	}
	
	private boolean setupEconomy() {
		if(getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	@Override
	public void onDisable() {
		
	}	
	
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (label.equalsIgnoreCase("lottery")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "You cannot do this");
					return true;
				}
				Player player = (Player) sender;
				@SuppressWarnings("deprecation")
				EconomyResponse r = econ.withdrawPlayer(player.getName(), 25000);
				if (r.transactionSuccess()) {
					spin(player);
					return true;
				}
				else {
					player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You don't have enough money!");
					return true;
				}
			}
			return false;
		}
		
		public void shuffle(Inventory inv) {
			if (contents == null) {
				ItemStack[] items = new ItemStack[10];
				items[0] = new ItemStack(Material.COBBLESTONE, 64);
				items[1] = new ItemStack(Material.OAK_LOG, 16);
				items[2] = new ItemStack(Material.DIRT, 1);
				items[3] = new ItemStack(Material.ARROW, 16);
				items[4] = new ItemStack(Material.COAL_ORE, 32);
				items[5] = new ItemStack(Material.APPLE, 32);
				items[6] = new ItemStack(Material.BLACK_CONCRETE, 64);
				items[7] = new ItemStack(Material.ENDER_PEARL, 8);
				items[8] = new ItemStack(Material.ANDESITE, 64);
				items[9] = new ItemStack(Material.DIAMOND_BLOCK, 5);
				
				contents = items;
			}
			
			int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);
			for (int index = 0; index < startingIndex; index++) {
				for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
					inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
				}
				itemIndex++;
			}
			
			ItemStack item = new ItemStack(Material.HOPPER);
			ItemMeta meta =item.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_AQUA + "|");
			item.setItemMeta(meta);
			inv.setItem(4, item);
		}
		
		public void spin(final Player player) {
			Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Sponsored By Stucker & Vault Pluggin");
			shuffle(inv);
			invs.add(inv);
			player.openInventory(inv);
			
			Random r = new Random();
			double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();
			
			new BukkitRunnable() {
				double delay = 0;
				int ticks = 0;
				boolean done = false;
				
				public void run() {
					if (done)
						return;
					ticks++;
					delay += 1 / (20 * seconds);
					if (ticks > delay * 10) {
						ticks = 0;
						
						for (int itemstacks = 9; itemstacks < 18; itemstacks++)
							inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
						
						itemIndex++;
						
						if (delay >= .5) {
							done = true;
							new BukkitRunnable() {
								public void run() {
									ItemStack item = inv.getItem(13);
									player.getInventory().addItem(item);
									player.updateInventory();
									player.closeInventory();
									cancel();
								}
							}.runTaskLater(Main.getPlugin(Main.class), 50);
							cancel();
						}
					}
				}
			}.runTaskTimer(this, 0, 1);
		}
		
		@EventHandler
		public void onClick(InventoryClickEvent event) {
			if (!invs.contains(event.getInventory()))
				return;
			
			event.setCancelled(true);
			return;
		}
}
