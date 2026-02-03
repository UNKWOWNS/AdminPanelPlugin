package me.tailzz.adminpanel.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.kyori.adventure.text.Component;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.joinMessage(Component.text("§8[§a+§8] §7" + e.getPlayer().getName()));
    }
}