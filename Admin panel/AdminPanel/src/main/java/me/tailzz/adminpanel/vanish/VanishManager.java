package me.tailzz.adminpanel.vanish;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishManager {

    private final Set<UUID> vanished = new HashSet<>();
    private final Plugin plugin;

    public VanishManager(Plugin plugin) {
        this.plugin = plugin;

        // Action bar indicator
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (UUID uuid : vanished) {
                Player p = Bukkit.getPlayer(uuid);
                if (p != null && p.isOnline()) {
                    p.sendActionBar(Component.text("§7[§bVANISHED§7]"));
                }
            }
        }, 0L, 20L); // Every second
    }

    public boolean isVanished(Player p) {
        return vanished.contains(p.getUniqueId());
    }

    public void vanish(Player p) {
        if (vanished.contains(p.getUniqueId())) return;

        vanished.add(p.getUniqueId());

        // Fake quit message
        Bukkit.broadcast(Component.text("§8[§c-§8] §7" + p.getName()));

        // Hide player from all other players
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.equals(p)) continue;
            online.hidePlayer(plugin, p);
        }

        // Remove from tab list
        p.playerListName(Component.text("§7[V] " + p.getName()));
        
        p.sendMessage("§aYou are now vanished!");
    }

    public void unvanish(Player p) {
        if (!vanished.contains(p.getUniqueId())) return;

        vanished.remove(p.getUniqueId());

        // Show player to everyone
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.showPlayer(plugin, p);
        }

        // Restore tab name
        p.playerListName(Component.text(p.getName()));

        // Fake join message
        Bukkit.broadcast(Component.text("§8[§a+§8] §7" + p.getName()));
        
        p.sendMessage("§cYou are no longer vanished!");
    }

    public void toggle(Player p) {
        if (isVanished(p)) {
            unvanish(p);
        } else {
            vanish(p);
        }
    }
}