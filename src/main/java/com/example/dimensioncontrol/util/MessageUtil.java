package com.example.dimensioncontrol.util;

import org.bukkit.command.CommandSender;
import java.util.Map;

public class MessageUtil {

    // Sehr einfache Implementierung – später durch Locale/Config ersetzen
    public static void send(CommandSender sender, String key) {
        sender.sendMessage(prefix() + translate(key));
    }

    public static void send(CommandSender sender, String key, Map<String,String> placeholders) {
        String msg = translate(key);
        for (Map.Entry<String,String> e : placeholders.entrySet()) {
            msg = msg.replace("{"+e.getKey()+"}", e.getValue());
        }
        sender.sendMessage(prefix() + msg);
    }

    private static String prefix() { return "§8[§bDimCtrl§8] §7"; }

    private static String translate(String key) {
        switch (key) {
            case "no-permission": return "Keine Berechtigung.";
            case "player-only": return "Nur Spieler können das benutzen.";
            case "invalid-dimension": return "Ungültige Dimension.";
            case "invalid-action": return "Ungültige Aktion.";
            case "reloaded": return "Konfiguration neu geladen.";
            case "toggled": return "{dimension} wurde {value}.";
            case "usage.header": return "Verwendung:";
            case "usage.line": return "{cmd}";
            case "status.header": return "Status der Dimensionen:";
            case "status.line": return "{dimension}: {value}";
            case "nether-blocked": return "Der Nether ist derzeit gesperrt.";
            case "end-blocked": return "Das End ist derzeit gesperrt.";
        }
        return key;
    }
}