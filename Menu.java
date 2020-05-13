package me.tank203.stucker2;

import java.util.ArrayList;
import java.util.List;

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
        ItemStack tpHome = new ItemStack(Material.RED_BED);
        ItemStack trash = new ItemStack(Material.LAVA_BUCKET);
        ItemStack space = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ItemMeta spaceMeta = space.getItemMeta();
        ItemMeta trashMeta = trash.getItemMeta();
        ItemMeta lotteryItemsMeta = lotteryItems.getItemMeta();
        ItemMeta playLotteryMeta = playLottery.getItemMeta();
        ItemMeta openEnderchestMeta = openEnderchest.getItemMeta();
        ItemMeta tpHomeMeta = tpHome.getItemMeta();

        List<String> lore = new ArrayList<String>();
        List<String> lore2 = new ArrayList<String>();
        List<String> lore3 = new ArrayList<String>();
        List<String> lore4 = new ArrayList<String>();
        List<String> lore5 = new ArrayList<String>();

        double money = 25000 - Main.econ.getBalance(player);

        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Allows you to see the items in the lottery.");
        lore2.add(ChatColor.GREEN + "" + "Allows you to play the lottery for $25,000");
        lore2.add(ChatColor.BLUE + "Current Bal: $" + Main.econ.getBalance(player));
        lore2.add(ChatColor.AQUA + "You need $" + money);
        lore3.add(ChatColor.BLUE + "Open your enderchest");
        lore4.add(ChatColor.YELLOW + "Teleports you to your bed spawn location");
        lore5.add("A trashcan to throw away trash (like Chad)");

        lotteryItemsMeta.setLore(lore);
        playLotteryMeta.setLore(lore2);
        openEnderchestMeta.setLore(lore3);
        tpHomeMeta.setLore(lore4);
        trashMeta.setLore(lore5);

        spaceMeta.setDisplayName(ChatColor.GRAY + " ");
        lotteryItemsMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Lottery Items");
        playLotteryMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "Lottery");
        openEnderchestMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + "EnderChest");
        tpHomeMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.ITALIC + "Home");
        trashMeta.setDisplayName(ChatColor.RED + "Trashcan");

        space.setItemMeta(spaceMeta);
        lotteryItems.setItemMeta(lotteryItemsMeta);
        playLottery.setItemMeta(playLotteryMeta);
        openEnderchest.setItemMeta(openEnderchestMeta);
        tpHome.setItemMeta(tpHomeMeta);
        trash.setItemMeta(trashMeta);

        menu.setItem(0, space);
        menu.setItem(1, space);
        menu.setItem(2, space);
        menu.setItem(3, space);
        menu.setItem(4, tpHome);
        menu.setItem(5, space);
        menu.setItem(6, space);
        menu.setItem(7, space);
        menu.setItem(8, space);
        menu.setItem(9, space);
        menu.setItem(10, space);
        menu.setItem(11, lotteryItems);
        menu.setItem(12, space);
        menu.setItem(13, playLottery);
        menu.setItem(14, space);
        menu.setItem(15, openEnderchest);
        menu.setItem(16, space);
        menu.setItem(17, space);
        menu.setItem(18, space);
        menu.setItem(19, space);
        menu.setItem(20, space);
        menu.setItem(21, space);
        menu.setItem(22, trash);
        menu.setItem(23, space);
        menu.setItem(24, space);
        menu.setItem(25, space);
        menu.setItem(26, space);
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
        ItemStack back = new ItemStack(Material.BARRIER);
        ItemStack space = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ItemMeta spaceMeta = space.getItemMeta();
        ItemMeta backMeta = back.getItemMeta();
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
        backMeta.setDisplayName(ChatColor.DARK_RED + "BACK");
        spaceMeta.setDisplayName(ChatColor.GRAY + " ");

        space.setItemMeta(spaceMeta);
        back.setItemMeta(backMeta);
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
        items.setItem(15, space);
        items.setItem(16, space);
        items.setItem(17, space);
        items.setItem(18, space);
        items.setItem(19, space);
        items.setItem(20, space);
        items.setItem(21, space);
        items.setItem(22, back);
        items.setItem(23, space);
        items.setItem(24, space);
        items.setItem(25, space);
        items.setItem(26, space);

        player.openInventory(items);
    }

    private void lotteryCommand(Player player) {
        Bukkit.dispatchCommand(player, "lottery");
    }

    private void enderchestCommand(Player player) {
        Bukkit.dispatchCommand(player, "ec open");
    }

    private void trashCan(Player player) {
        Inventory trash = Bukkit.createInventory(null, 54, "Trashcan");

        ItemStack back = new ItemStack(Material.BARRIER);

        ItemMeta backMeta = back.getItemMeta();

        backMeta.setDisplayName(ChatColor.DARK_RED + "BACK");

        back.setItemMeta(backMeta);

        trash.setItem(49, back);

        player.openInventory(trash);
    }

    private void tpHome(Player player) {
        double x = player.getBedSpawnLocation().getBlockX();
        double y = player.getBedSpawnLocation().getBlockY();
        double z = player.getBedSpawnLocation().getBlockZ();

        String name = player.getName();

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + name + " " + x + " " + y + " " + z);
    }

    @EventHandler
    public void onPlayerClickTrash(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getView().getTitle().equalsIgnoreCase("Trashcan"))) {
            return;
        }
        else if (event.getCurrentItem().getType() == Material.BARRIER) {
            openGUI(player);
        }
        event.setCancelled(false);
    }

    @EventHandler
    public void onPlayerClickLottery(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getView().getTitle().equalsIgnoreCase("Lottery Items"))) {
            return;
        }
        else if (event.getCurrentItem().getType() == Material.BARRIER) {
            openGUI(player);
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
                enderchestCommand(player);
                break;
            case RED_BED:
                tpHome(player);
                break;
            case LAVA_BUCKET:
                trashCan(player);
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
