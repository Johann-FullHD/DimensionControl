package com.example.dimensioncontrol;

import com.example.dimensioncontrol.command.DimensionAccessCommand;
import com.example.dimensioncontrol.gui.DimensionAccessGUI;
import com.example.dimensioncontrol.listener.*;
import com.example.dimensioncontrol.service.AccessManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DimensionControlPlugin extends JavaPlugin {

    private AccessManager accessManager;
    private DimensionAccessGUI gui;

    @Override
    public void onEnable() {
        // Load configuration
        saveDefaultConfig();
        accessManager = new AccessManager(this);

        // Register commands
        DimensionAccessCommand cmd = new DimensionAccessCommand(accessManager, this);
        getCommand("dimensionaccess").setExecutor(cmd);
        getCommand("dimensionaccess").setTabCompleter(cmd);

        // Register GUI
        gui = new DimensionAccessGUI(this, accessManager); // registriert sich selbst

        // Register event listeners
        getServer().getPluginManager().registerEvents(new EndPortalListener(accessManager), this);
        getServer().getPluginManager().registerEvents(new PortalListener(accessManager), this);
        getServer().getPluginManager().registerEvents(new TeleportListener(accessManager), this);
        getServer().getPluginManager().registerEvents(new CommandPreprocessListener(accessManager), this);
    }

    @Override
    public void onDisable() {
        accessManager.saveToConfigAsync();
    }

    public AccessManager getAccessManager() {
        return accessManager;
    }
}