package me.tailzz.adminpanel.commands;

import me.tailzz.adminpanel.gui.AdminGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        if (!player.hasPermission("adminpanel.gui")) {
            player.sendMessage("§cYou don't have permission to use this.");
            return true;
        }

        AdminGUI.open(player);
        return true;
    }
}