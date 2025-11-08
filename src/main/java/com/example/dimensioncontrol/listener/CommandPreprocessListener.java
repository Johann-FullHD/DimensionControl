package com.example.dimensioncontrol.listener;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreprocessListener implements Listener {

    private final AccessManager accessManager;

    public CommandPreprocessListener(AccessManager accessManager) {
        this.accessManager = accessManager;
    }

    @EventHandler
    public void onPreprocess(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage().toLowerCase();
        Player p = event.getPlayer();
        // Heuristische Erkennung: /warp nether, /warp end, /tpa nether, /home end etc.
        if (msg.matches("/(warp|home|tpa|tp|teleport) .*")) {
            // Prüfe letzte Teilzeichenkette
            String[] parts = msg.split(" ");
            String target = parts[parts.length - 1];
            World.Environment env = null;
            if (target.contains("nether")) env = World.Environment.NETHER; else if (target.contains("end")) env = World.Environment.THE_END;
            if (env != null && !accessManager.canAccess(p, env)) {
                event.setCancelled(true);
                MessageUtil.send(p, accessManager.getBlockedKey(env));
            }
        }
    }
}