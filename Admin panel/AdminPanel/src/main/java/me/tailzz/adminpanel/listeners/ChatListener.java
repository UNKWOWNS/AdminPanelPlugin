package me.tailzz.adminpanel.listeners;

import me.tailzz.adminpanel.vanish.VanishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final VanishManager vanish;

    public ChatListener(VanishManager vanish) {
        this.vanish = vanish;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (vanish.isVanished(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cYou cannot chat while vanished!");
        }
    }
}