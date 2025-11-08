package com.example.dimensioncontrol.command;

import com.example.dimensioncontrol.service.AccessManager;
import com.example.dimensioncontrol.gui.DimensionAccessGUI;
import com.example.dimensioncontrol.util.MessageUtil;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class DimensionAccessCommand implements CommandExecutor, TabCompleter {

    private final AccessManager accessManager;
    private final Plugin plugin;

    public DimensionAccessCommand(AccessManager accessManager, Plugin plugin) {
        this.accessManager = accessManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendUsage(sender, label);
            return true;
        }
        String sub = args[0].toLowerCase(Locale.ROOT);
        switch (sub) {
            case "status":
                if (!sender.hasPermission("dimensioncontrol.status") && !sender.hasPermission("dimensioncontrol.admin")) {
                    MessageUtil.send(sender, "no-permission");
                    return true;
                }
                MessageUtil.send(sender, "status.header");
                MessageUtil.send(sender, "status.line", mapOf("dimension", "Nether", "value", colored(accessManager.isNetherAccessEnabled())));
                MessageUtil.send(sender, "status.line", mapOf("dimension", "The End", "value", colored(accessManager.isEndAccessEnabled())));
                return true;
            case "reload":
                if (!sender.hasPermission("dimensioncontrol.admin")) {
                    MessageUtil.send(sender, "no-permission");
                    return true;
                }
                accessManager.reloadFromConfig();
                MessageUtil.send(sender, "reloaded");
                return true;
            case "gui":
                if (!(sender instanceof Player)) {
                    MessageUtil.send(sender, "player-only");
                    return true;
                }
                if (!sender.hasPermission("dimensioncontrol.admin")) {
                    MessageUtil.send(sender, "no-permission");
                    return true;
                }
                DimensionAccessGUI.open((Player) sender, accessManager);
                return true;
        }
        if (args.length < 2) {
            sendUsage(sender, label);
            return true;
        }
        Dimension dim = Dimension.from(sub);
        if (dim == null) {
            MessageUtil.send(sender, "invalid-dimension");
            return true;
        }
        if (!sender.hasPermission("dimensioncontrol.admin")) {
            MessageUtil.send(sender, "no-permission");
            return true;
        }
        String action = args[1].toLowerCase(Locale.ROOT);
        boolean newValue;
        switch (action) {
            case "enable":
            case "on":
                newValue = true;
                break;
            case "disable":
            case "off":
                newValue = false;
                break;
            case "toggle":
                newValue = !getCurrent(dim);
                break;
            default:
                MessageUtil.send(sender, "invalid-action");
                return true;
        }
        setAccess(dim, newValue);
        accessManager.saveToConfigAsync();
        MessageUtil.send(sender, "toggled", mapOf("dimension", dim.display(), "value", newValue ? "aktiviert" : "gesperrt"));
        return true;
    }

    private void sendUsage(CommandSender sender, String label) {
        MessageUtil.send(sender, "usage.header", mapOf("label", label));
        MessageUtil.send(sender, "usage.line", mapOf("cmd", "/" + label + " <nether|end> <enable|disable|toggle>"));
        MessageUtil.send(sender, "usage.line", mapOf("cmd", "/" + label + " status"));
        MessageUtil.send(sender, "usage.line", mapOf("cmd", "/" + label + " reload"));
        MessageUtil.send(sender, "usage.line", mapOf("cmd", "/" + label + " gui"));
    }

    private boolean getCurrent(Dimension dim) {
        switch (dim) {
            case NETHER:
                return accessManager.isNetherAccessEnabled();
            case END:
                return accessManager.isEndAccessEnabled();
        }
        return true;
    }

    private void setAccess(Dimension dim, boolean value) {
        switch (dim) {
            case NETHER:
                accessManager.setNetherAccess(value);
                break;
            case END:
                accessManager.setEndAccess(value);
                break;
        }
    }

    private String colored(boolean enabled) {
        return enabled ? "§aaktiv" : "§cgesperrt";
    }

    enum Dimension {
        NETHER(World.Environment.NETHER, "nether", "n", "netherwelt"),
        END(World.Environment.THE_END, "end", "the_end", "ende", "ender");
        final World.Environment env;
        final List<String> aliases;

        Dimension(World.Environment env, String... aliases) {
            this.env = env;
            this.aliases = Arrays.asList(aliases);
        }

        String display() {
            return this == NETHER ? "Nether" : "The End";
        }

        static Dimension from(String in) {
            String s = in.toLowerCase(Locale.ROOT);
            for (Dimension d : values()) if (d.aliases.contains(s)) return d;
            return null;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.addAll(Arrays.asList("nether", "end", "status", "reload", "gui"));
        } else if (args.length == 2 && (args[0].equalsIgnoreCase("nether") || args[0].equalsIgnoreCase("end"))) {
            list.addAll(Arrays.asList("enable", "disable", "toggle"));
        }
        return list;
    }

    private Map<String, String> mapOf(String k1, String v1) {
        Map<String, String> m = new HashMap<>();
        m.put(k1, v1);
        return m;
    }

    private Map<String, String> mapOf(String k1, String v1, String k2, String v2) {
        Map<String, String> m = new HashMap<>();
        m.put(k1, v1);
        m.put(k2, v2);
        return m;
    }
}