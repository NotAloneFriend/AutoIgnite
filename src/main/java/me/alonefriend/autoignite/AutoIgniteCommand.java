package me.alonefriend.autoignite;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class AutoIgniteCommand implements CommandExecutor {
    private Plugin plugin;

    public AutoIgniteCommand(AutoIgnite pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            if (!(sender.hasPermission("autoignite.help") || sender.hasPermission("autoignite.*") || sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                return true;
            }

            sender.sendMessage(ChatColor.YELLOW + "--------------- AutoIgniteCommand ---------------");
            sender.sendMessage(ChatColor.YELLOW + "/autoignite help: " + ChatColor.WHITE + "Display help for " + plugin.getDescription().getName() + " commands.");
            sender.sendMessage(ChatColor.YELLOW + "/autoignite reload: " + ChatColor.WHITE + "Reload plugin configuration.");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!(sender.hasPermission("autoignite.reload") || sender.hasPermission("autoignite.*") || sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getName() + "'s configuration has been reloaded.");
            return true;
        }
        return false;
    }
}