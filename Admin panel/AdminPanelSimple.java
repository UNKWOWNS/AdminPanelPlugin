package me.tailzz.adminpanel;

import org.bukkit.plugin.java.JavaPlugin;

public class AdminPanelSimple extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("§aAdminPanel v1.0 Enabled!");
        getLogger().info("§eUse /gui to open admin panel!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cAdminPanel Disabled!");
    }
}