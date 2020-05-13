package me.tank203.stucker2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.util.internal.ThreadLocalRandom;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    public static Economy econ = null;
    public Inventory inv;
    List<Inventory> invs = new ArrayList<Inventory>();
    public ItemStack[] contents;
    private int itemIndex = 0;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.addRecipe(getAdvancedCompass());
        Bukkit.addRecipe(getEB());
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new AC(), this);
        this.getServer().getPluginManager().registerEvents(new Menu(this), this);
        this.getServer().getPluginManager().registerEvents(new me.tank203.stucker2.NPC(), this);
        this.getCommand("Launch").setExecutor(new Launch());
        this.getCommand("Chad").setExecutor(new Chad());
        this.getCommand("Shelp").setExecutor(new Help());
        this.getCommand("Why").setExecutor(new Why());
        this.getCommand("Menu").setExecutor(new Menu(this));
        this.getCommand("spawnshop").setExecutor(new me.tank203.stucker2.NPC());
        this.saveDefaultConfig();
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
        if (label.equalsIgnoreCase("st")) {
            if (!sender.hasPermission("stucker.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Try doing /st reload");
                return true;
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    for (String msg : getConfig().getStringList("reload.message")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                    reloadConfig();
                    return true;
                }
            }
        }
        else if (label.equalsIgnoreCase("lottery")) {
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

    public void getPlayer(final Player player) {
        spin(player);
    }

    public void shuffle(Inventory inv) {
        if (contents == null) {
            ItemStack[] items = new ItemStack[15];
            items[0] = new ItemStack(Material.COBBLESTONE, 64);
            items[1] = new ItemStack(Material.OAK_LOG, 16);
            items[2] = new ItemStack(Material.DIRT, 1);
            items[3] = new ItemStack(Material.ARROW, 16);
            items[4] = new ItemStack(Material.COAL_ORE, 32);
            items[5] = new ItemStack(Material.APPLE, 32);
            items[6] = new ItemStack(Material.BLACK_CONCRETE, 64);
            items[7] = new ItemStack(Material.ENDER_PEARL, 8);
            items[8] = new ItemStack(Material.ANDESITE, 64);
            items[9] = new ItemStack(Material.DIAMOND_BLOCK, 15);
            items[10] = new ItemStack(Material.BEACON, 1);
            items[11] = new ItemStack(Material.VILLAGER_SPAWN_EGG, 1);
            items[12] = new ItemStack(Material.AIR, 1);
            items[13] = new ItemStack(Material.BOOK, 16);
            items[14] = new ItemStack(Material.WITHER_SKELETON_SKULL, 1);

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
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Powered By Stucker & Vault");
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        this.getConfig().getConfigurationSection("blocks").getKeys(false).forEach(key -> {
            if (key.equalsIgnoreCase(event.getBlock().getType().toString())) {
                ItemStack[] items = new ItemStack[this.getConfig().getStringList("blocks." + key).size()];
                ItemStack item = null;
                int position = 0;
                Random r = new Random();
                for (String i : this.getConfig().getStringList("blocks." + key)) {
                    try {
                        item = new ItemStack(Material.matchMaterial(i), r.nextInt(3) + 1);
                    } catch(Exception e) {
                        item = new ItemStack(Material.matchMaterial(key));
                        event.getPlayer().sendMessage("You mispelled somthing in the cofig!");
                    }
                    items[position] = item;
                    position++;
                }
                int num = r.nextInt(items.length);
                event.setDropItems(false);
                World world = event.getPlayer().getWorld();
                world.dropItemNaturally(event.getBlock().getLocation(), items[num]);
            }
        });
    }

    public ShapedRecipe getEB() {
        ItemStack eBlade = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = eBlade.getItemMeta();
        meta.setDisplayName("Emerald Blade");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.GOLD + "LEGENDARY");
        lore.add("");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        eBlade.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "emerald_blade");
        ShapedRecipe recipe = new ShapedRecipe(key, eBlade);

        recipe.shape(" E ", " E ", " S ");
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getAdvancedCompass() {
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

        NamespacedKey key = new NamespacedKey(this, "advanced_compass");
        ShapedRecipe recipe = new ShapedRecipe(key, compass);

        recipe.shape(" D ", "DCD", " D ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('C', Material.COMPASS);

        return recipe;
    }
}
