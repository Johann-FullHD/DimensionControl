package com.example.dimensioncontrol.service;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AccessManager {

    private final Plugin plugin;
    private volatile boolean netherAccess;
    private volatile boolean endAccess;

    public AccessManager(Plugin plugin) {
        this.plugin = plugin;
        reloadFromConfig();
    }

    public boolean isNetherAccessEnabled() { return netherAccess; }
    public boolean isEndAccessEnabled() { return endAccess; }

    public void setNetherAccess(boolean enabled) { this.netherAccess = enabled; }
    public void setEndAccess(boolean enabled) { this.endAccess = enabled; }

    public void reloadFromConfig() {
        plugin.reloadConfig();
        FileConfiguration c = plugin.getConfig();
        netherAccess = c.getBoolean("access.nether", true);
        endAccess = c.getBoolean("access.end", true);
    }

    public void saveToConfigAsync() {
        FileConfiguration c = plugin.getConfig();
        c.set("access.nether", netherAccess);
        c.set("access.end", endAccess);
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> plugin.saveConfig());
    }

    // Neue Utility Methoden
    public boolean canAccess(Player p, World.Environment env) {
        switch (env) {
            case NETHER:
                if (netherAccess) return true;
                return p.hasPermission("dimensioncontrol.bypass.nether");
            case THE_END:
                if (endAccess) return true;
                return p.hasPermission("dimensioncontrol.bypass.end");
            default:
                return true; // Overworld nie blockiert
        }
    }

    public String getBlockedKey(World.Environment env) {
        switch (env) {
            case NETHER: return "nether-blocked";
            case THE_END: return "end-blocked";
            default: return "";
        }
    }
}