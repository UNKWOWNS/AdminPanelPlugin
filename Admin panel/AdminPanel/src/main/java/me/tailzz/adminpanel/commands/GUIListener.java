package me.tailzz.adminpanel.listeners;

import me.tailzz.adminpanel.gui.AdminGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        
        if (e.getView().getTitle().equals("Admin Panel")) {
            e.setCancelled(true);
            
            ItemStack clicked = e.getCurrentItem();
            if (clicked == null) return;
            
            AdminGUI.handleClick(player, e.getSlot());
        }
    }
}