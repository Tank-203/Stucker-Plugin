package me.Tank203.Stucker;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Launch implements CommandExecutor {
	
	Map<String, Long> cooldowns = new HashMap<String, Long>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*console goes flying*");
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
			cooldowns.put(player.getName(), System.currentTimeMillis() + (60 * 1000));
			
			// /launch
			if(args.length == 0) {
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Zoooom!");
				player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
				return true;
			}
			
			// /launch <number>
			if(isNum(args[0])) {
				if(Integer.parseInt(args[0]) > 600) {
					player.sendMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + "I'm sorry you can't do more than 600!");
					player.setVelocity(player.getLocation().getDirection().multiply(0).setY(0));
					return true;
				}
				else {
					player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Zoooom!");
					player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
				}
			}
			else {
				player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Usage: /launch <number-value>");
			}
			return true;
		}
		return false;
	}
	
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
