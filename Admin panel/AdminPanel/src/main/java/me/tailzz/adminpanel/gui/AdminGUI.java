package me.tailzz.adminpanel.gui;

import me.tailzz.adminpanel.AdminPanel;
import me.tailzz.adminpanel.vanish.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AdminGUI {

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "Admin Panel");

        // Vanish toggle button
        ItemStack vanishItem = new ItemStack(Material.ENDER_EYE);
        ItemMeta vanishMeta = vanishItem.getItemMeta();
        vanishMeta.setDisplayName("§b§lToggle Vanish");
        vanishMeta.setLore(Arrays.asList(
                "§7Click to vanish/unvanish",
                "§7Status: §f" + (AdminPanel.get().getVanishManager().isVanished(player) ? "§aON" : "§cOFF")));
        vanishItem.setItemMeta(vanishMeta);
        inv.setItem(10, vanishItem);

        // Freeze Player button
        ItemStack freezeItem = new ItemStack(Material.ICE);
        ItemMeta freezeMeta = freezeItem.getItemMeta();
        freezeMeta.setDisplayName("§b§lFreeze Player");
        freezeMeta.setLore(Arrays.asList("§7Click to select player to freeze"));
        freezeItem.setItemMeta(freezeMeta);
        inv.setItem(12, freezeItem);

        // Teleport to Player button
        ItemStack tpItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta tpMeta = tpItem.getItemMeta();
        tpMeta.setDisplayName("§d§lTeleport to Player");
        tpMeta.setLore(Arrays.asList("§7Click to select player to teleport"));
        tpItem.setItemMeta(tpMeta);
        inv.setItem(14, freezeItem);

        // View Inventory button
        ItemStack invItem = new ItemStack(Material.CHEST);
        ItemMeta invMeta = invItem.getItemMeta();
        invMeta.setDisplayName("§6§lView Player Inventory");
        invMeta.setLore(Arrays.asList("§7Click to view player's inventory"));
        invItem.setItemMeta(invMeta);
        inv.setItem(16, invItem);

        // Close button
        ItemStack closeItem = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeItem.getItemMeta();
        closeMeta.setDisplayName("§c§lClose Menu");
        closeItem.setItemMeta(closeMeta);
        inv.setItem(22, closeItem);

        player.openInventory(inv);
    }

    public static void handleClick(Player player, int slot) {
        VanishManager vanishManager = AdminPanel.get().getVanishManager();

        switch (slot) {
            case 10: // Vanish toggle
                vanishManager.toggle(player);
                player.sendMessage("§eVanish " + (vanishManager.isVanished(player) ? "§aenabled" : "§cdisabled"));
                open(player); // Refresh GUI
                break;

            case 12: // Freeze Player
                player.sendMessage("§e[Coming Soon] Freeze feature");
                player.closeInventory();
                break;

            case 14: // Teleport
                player.sendMessage("§e[Coming Soon] Teleport feature");
                player.closeInventory();
                break;

            case 16: // View Inventory
                player.sendMessage("§e[Coming Soon] Inventory viewer");
                player.closeInventory();
                break;

            case 22: // Close
                player.closeInventory();
                break;
        }
    }
}