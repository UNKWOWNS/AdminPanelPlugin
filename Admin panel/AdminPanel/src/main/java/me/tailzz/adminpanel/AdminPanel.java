package me.tailzz.adminpanel;

import me.tailzz.adminpanel.commands.GuiCommand;
import me.tailzz.adminpanel.listeners.ChatListener;
import me.tailzz.adminpanel.listeners.GUIListener;
import me.tailzz.adminpanel.listeners.JoinListener;
import me.tailzz.adminpanel.vanish.VanishManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminPanel extends JavaPlugin {

    private static AdminPanel instance;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        instance = this;
        vanishManager = new VanishManager(this);

        getCommand("gui").setExecutor(new GuiCommand());
        
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(vanishManager), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);

        getLogger().info("§aAdminPanel enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cAdminPanel disabled!");
    }

    public static AdminPanel get() {
        return instance;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }
}