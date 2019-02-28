package me.alonefriend.autoignite;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    private Main plugin;

    public Commands(Main main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("autoignite")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("autoignite.help") || sender.hasPermission("autoignite.*") || sender.isOp()) {
                    sender.sendMessage(ChatColor.YELLOW + "--------------- Commands ---------------");
                    sender.sendMessage(ChatColor.YELLOW + "/autoignite help: " + ChatColor.WHITE + "Display help for " + plugin.getDescription().getName() + " commands.");
                    sender.sendMessage(ChatColor.YELLOW + "/autoignite reload: " + ChatColor.WHITE + "Reload plugin configuration.");
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("autoignite.reload") || sender.hasPermission("autoignite.*") || sender.isOp()) {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getName() + "'s configuration has been reloaded.");
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                }
                return true;
            }
        }
        return false;
    }
}