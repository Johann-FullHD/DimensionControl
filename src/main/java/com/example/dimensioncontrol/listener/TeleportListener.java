package com.example.dimensioncontrol.listener;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    private final AccessManager accessManager;

    public TeleportListener(AccessManager accessManager) {
        this.accessManager = accessManager;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getTo() == null) return;
        Player p = event.getPlayer();
        World.Environment env = event.getTo().getWorld().getEnvironment();
        if (!accessManager.canAccess(p, env)) {
            event.setCancelled(true);
            String key = accessManager.getBlockedKey(env);
            if (!key.isEmpty()) MessageUtil.send(p, key);
        }
    }
}