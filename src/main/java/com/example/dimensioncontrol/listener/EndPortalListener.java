package com.example.dimensioncontrol.listener;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class EndPortalListener implements Listener {

    private final AccessManager accessManager;
    public EndPortalListener(AccessManager accessManager) { this.accessManager = accessManager; }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return; // nur Mainhand
        if (e.getClickedBlock() == null) return;
        if (e.getClickedBlock().getType() != Material.END_PORTAL_FRAME) return;
        if (e.getItem() == null || e.getItem().getType() != Material.ENDER_EYE) return;
        if (accessManager.isEndAccessEnabled()) return;
        if (e.getPlayer().hasPermission("dimensioncontrol.bypass.end")) return;
        // Blockieren des Einsetzens
        e.setCancelled(true);
        MessageUtil.send(e.getPlayer(), "end-blocked-place-eye");
    }
}
