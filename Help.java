package me.Tank203.Stucker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("shelp")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "*****Stucker Help*****");
				sender.sendMessage(ChatColor.GRAY + "- /Lauch or /lch <number> ");
				sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Allows you to be launched into the air!" );
				sender.sendMessage(ChatColor.GRAY + "- /chad");
				sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Thanks you for using the plugin! Plus it will heal you" );
				sender.sendMessage(ChatColor.GRAY + "- /lottery ");
				sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Allows you to play the lottery! For a small price though :/" );
				return true;
			}
				Player player = (Player) sender;
				player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "*****Stucker Help*****");
				player.sendMessage(ChatColor.GRAY + "- /Lauch or /lch <number> ");
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Allows you to be laucnhed into the air!" );
				player.sendMessage(ChatColor.GRAY + "- /chad");
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Thanks you for using the plugin! Plus it will heal you" );
				player.sendMessage(ChatColor.GRAY + "- /lottery ");
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Allows you to play the lottery! For a small price though :/" ):
				return true;
		}
		return false;
	}
}
