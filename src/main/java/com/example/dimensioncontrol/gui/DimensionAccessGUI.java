package com.example.dimensioncontrol.gui;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class DimensionAccessGUI implements Listener {

    private static final String TITLE = "§8Dimensionen";
    private final Plugin plugin;
    private final AccessManager accessManager;

    public DimensionAccessGUI(Plugin plugin, AccessManager accessManager) {
        this.plugin = plugin;
        this.accessManager = accessManager;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void open(Player player, AccessManager accessManager) {
        Inventory inv = Bukkit.createInventory(player, 9, TITLE);
        // Nether Item
        inv.setItem(3, create(accessManager.isNetherAccessEnabled() ? Material.MAGMA_BLOCK : Material.NETHERRACK,
                accessManager.isNetherAccessEnabled() ? "§aNether aktiv" : "§cNether gesperrt"));
        // End Item
        inv.setItem(5, create(accessManager.isEndAccessEnabled() ? Material.END_STONE : Material.CHORUS_FLOWER,
                accessManager.isEndAccessEnabled() ? "§aEnd aktiv" : "§cEnd gesperrt"));
        player.openInventory(inv);
    }

    private static ItemStack create(Material mat, String name) {
        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        if (meta != null) { meta.setDisplayName(name); is.setItemMeta(meta); }
        return is;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(TITLE)) {
            e.setCancelled(true);
            if (!(e.getWhoClicked() instanceof Player)) return;
            Player p = (Player) e.getWhoClicked();
            if (!p.hasPermission("dimensioncontrol.admin")) {
                MessageUtil.send(p, "no-permission");
                return;
            }
            int slot = e.getRawSlot();
            if (slot == 3) {
                accessManager.setNetherAccess(!accessManager.isNetherAccessEnabled());
                accessManager.saveToConfigAsync();
                open(p, accessManager);
                MessageUtil.send(p, "toggled", java.util.Map.of("dimension", "Nether", "value", accessManager.isNetherAccessEnabled()?"aktiviert":"gesperrt"));
            } else if (slot == 5) {
                accessManager.setEndAccess(!accessManager.isEndAccessEnabled());
                accessManager.saveToConfigAsync();
                open(p, accessManager);
                MessageUtil.send(p, "toggled", java.util.Map.of("dimension", "The End", "value", accessManager.isEndAccessEnabled()?"aktiviert":"gesperrt"));
            }
        }
    }
}
