package me.Tank203.Stucker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Why implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("why")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Join the minecraft server if you want to do that!");
				return true;
			}
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Why woaijfosiefjseofusfhseoufhuasofhsuoieshfseoufseh");
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "Thank you for this day");
		}
		return false;
	}
}
