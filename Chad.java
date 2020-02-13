package me.Tank203.Stucker;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chad implements CommandExecutor {

	Map<String, Long> cooldowns = new HashMap<String, Long>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("chad")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Thank you for using Stucker the plugin!");
				return true;
			}
				Player player = (Player) sender;
				
				if (cooldowns.containsKey(player.getName())) {
					if (cooldowns.get(player.getName()) > System.currentTimeMillis()) {
						long timeleft = (cooldowns.get(player.getName()) - System.currentTimeMillis()) / 1000;
						player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Ability will be ready in " + timeleft + " second(s)");
						return true;
					}
				}
				cooldowns.put(player.getName(), System.currentTimeMillis() + (300 * 1000));
				
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Thank you for using Stucker!");
				player.sendMessage(ChatColor.YELLOW + "You have been healed!");
				player.setHealth(20);
				player.setFoodLevel(20);
				return true;
		}
		return false;
	}

}
