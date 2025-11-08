package com.example.dimensioncontrol.listener;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalListener implements Listener {

    private final AccessManager accessManager;

    public PortalListener(AccessManager accessManager) {
        this.accessManager = accessManager;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getTo() == null || event.getTo().getWorld() == null) return;
        Player p = event.getPlayer();
        World.Environment env = event.getTo().getWorld().getEnvironment();
        if (!accessManager.canAccess(p, env)) {
            event.setCancelled(true);
            String key = accessManager.getBlockedKey(env);
            if (!key.isEmpty()) MessageUtil.send(p, key);
        }
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getTo() == null || event.getTo().getWorld() == null) return;
        Player p = (Player) event.getEntity();
        World.Environment env = event.getTo().getWorld().getEnvironment();
        if (!accessManager.canAccess(p, env)) {
            event.setCancelled(true);
            String key = accessManager.getBlockedKey(env);
            if (!key.isEmpty()) MessageUtil.send(p, key);
        }
    }
}