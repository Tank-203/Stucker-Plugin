package me.Tank203.Stucker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements CommandExecutor, Listener {

	static Main plugin;
    public Menu(Main instance) {
    	plugin = instance;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("menu")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Join the minecraft server if you want to do that!");
				return true;
			}
			Player player = (Player) sender;
			openGUI(player);
		}
		return false;
	}
	
	private void openGUI(Player player) {
		Inventory menu = Bukkit.createInventory(null, 27, "Menu");
		ItemStack lotteryItems = new ItemStack(Material.CHEST);
		ItemStack playLottery = new ItemStack(Material.NETHER_STAR);
		ItemStack openEnderchest = new ItemStack(Material.ENDER_CHEST);
		
		ItemMeta lotteryItemsMeta = lotteryItems.getItemMeta();
		ItemMeta playLotteryMeta = playLottery.getItemMeta();
		ItemMeta openEnderchestMeta = openEnderchest.getItemMeta();
		
		lotteryItemsMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Lottery Items");
		playLotteryMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "Lottery");
		openEnderchestMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + "EnderChest");
		
		lotteryItems.setItemMeta(lotteryItemsMeta);
		playLottery.setItemMeta(playLotteryMeta);
		openEnderchest.setItemMeta(openEnderchestMeta);
		
		menu.setItem(11, lotteryItems);
		menu.setItem(13, playLottery);
		menu.setItem(15, openEnderchest);
		player.openInventory(menu);
	}
	
	private void lotteryItems(Player player) {
		Inventory items = Bukkit.createInventory(null, 27, "Lottery Items");
		ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);
		ItemStack log = new ItemStack(Material.OAK_LOG, 16);
		ItemStack dirt = new ItemStack(Material.DIRT, 1);
		ItemStack arrow = new ItemStack(Material.ARROW, 16);
		ItemStack coal = new ItemStack(Material.COAL_ORE, 32);
		ItemStack apple = new ItemStack(Material.APPLE, 32);
		ItemStack black = new ItemStack(Material.BLACK_CONCRETE, 64);
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 8);
		ItemStack andesite = new ItemStack(Material.ANDESITE, 64);
		ItemStack dblock = new ItemStack(Material.DIAMOND_BLOCK, 15);
		ItemStack beacon = new ItemStack(Material.BEACON, 1);
		ItemStack villager = new ItemStack(Material.VILLAGER_SPAWN_EGG, 1);
		ItemStack air = new ItemStack(Material.PLAYER_HEAD, 1);
		ItemStack book = new ItemStack(Material.BOOK, 16);
		ItemStack wither = new ItemStack(Material.WITHER_SKELETON_SKULL, 1);
		
		ItemMeta cobbleM = cobble.getItemMeta();
		ItemMeta logM = log.getItemMeta();
		ItemMeta dirtM = dirt.getItemMeta();
		ItemMeta arrowM = arrow.getItemMeta();
		ItemMeta coalM = coal.getItemMeta();
		ItemMeta appleM = apple.getItemMeta();
		ItemMeta blackM = black.getItemMeta();
		ItemMeta pearlM = pearl.getItemMeta();
		ItemMeta andesiteM = andesite.getItemMeta();
		ItemMeta dblockM = dblock.getItemMeta();
		ItemMeta beaconM = beacon.getItemMeta();
		ItemMeta villagerM = villager.getItemMeta();
		ItemMeta airM = air.getItemMeta();
		ItemMeta bookM = book.getItemMeta();
		ItemMeta witherM = wither.getItemMeta();
		
		airM.setDisplayName(ChatColor.DARK_RED + "AIR");
		
		cobble.setItemMeta(cobbleM);
		log.setItemMeta(logM);
		dirt.setItemMeta(dirtM);
		arrow.setItemMeta(arrowM);
		coal.setItemMeta(coalM);
		apple.setItemMeta(appleM);
		black.setItemMeta(blackM);
		pearl.setItemMeta(pearlM);
		andesite.setItemMeta(andesiteM);
		dblock.setItemMeta(dblockM);
		beacon.setItemMeta(beaconM);
		villager.setItemMeta(villagerM);
		air.setItemMeta(airM);
		book.setItemMeta(bookM);
		wither.setItemMeta(witherM);
		
		items.setItem(0, cobble);
		items.setItem(1, log);
		items.setItem(2, dirt);
		items.setItem(3, arrow);
		items.setItem(4, coal);
		items.setItem(5, apple);
		items.setItem(6, black);
		items.setItem(7, pearl);
		items.setItem(8, andesite);
		items.setItem(9, dblock);
		items.setItem(10, beacon);
		items.setItem(11, villager);
		items.setItem(12, air);
		items.setItem(13, book);
		items.setItem(14, wither);
		
		player.openInventory(items);
	}
	
	private void lotteryCommand(Player player) {
		Bukkit.dispatchCommand(player, "lottery");
	}
	
	@EventHandler
	public void onPlayerClickLottery(InventoryClickEvent event) {
		if (!(event.getView().getTitle().equalsIgnoreCase("Lottery Items"))) {
			return;
		}
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerClickMenu(InventoryClickEvent event) {
		if (!(event.getView().getTitle().equalsIgnoreCase("Menu"))) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta() ) {
			event.setCancelled(true);
			return;
		}
		switch(event.getCurrentItem().getType()) {
		case CHEST:
			lotteryItems(player);
			break;
		case NETHER_STAR:
			player.closeInventory();
			lotteryCommand(player);
			break;
		case ENDER_CHEST:
			event.setCancelled(true);
			break;
		default:
			event.setCancelled(true);
			break;
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		ItemStack item = event.getItem();
		
		if (action == Action.PHYSICAL || item == null || item.getType() == Material.AIR) {
			return;
		}
		
		if (item.getType() == Material.NETHER_STAR) {
			openGUI(event.getPlayer());
		}
	}
}
