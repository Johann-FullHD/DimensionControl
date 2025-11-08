# DimensionControl

Ein einfaches, aber leistungsstarkes Minecraft-Plugin zur Verwaltung des Spielerzugangs zu den Dimensionen Nether und The End.

## ✨ Funktionen

- **Dimensions-Zugangskontrolle**: Aktivieren oder deaktivieren Sie den Zugang zum Nether und zum End unabhängig voneinander.
- **Befehlsintegration**: Einfache Befehle für Administratoren zur dynamischen Verwaltung des Zugangs.
- **Umfassende Blockierung**: Fängt Portal-, Teleport- und Befehlsversuche ab, um den Zugang zu gesperrten Dimensionen zu verhindern.
- **Bypass-Berechtigungen**: Erlauben Sie bestimmten Spielern oder Rängen (z.B. Admins), gesperrte Dimensionen zu betreten.
- **GUI-Verwaltung**: Eine einfache In-Game-GUI zum schnellen Umschalten des Dimensionszugangs.
- **Konfigurierbar**: Einstellungen und Nachrichten können über Konfigurationsdateien angepasst werden.
- **Leichtgewicht & Effizient**: Entwickelt, um minimale Auswirkungen auf die Serverleistung zu haben.

## 🚀 Installation

1.  Laden Sie die neueste `DimensionControl-X.Y.Z.jar` von der [Releases-Seite](https://github.com/example/dimension-access-control/releases) herunter.
2.  Platzieren Sie die JAR-Datei in Ihrem `plugins`-Verzeichnis.
3.  Starten Sie Ihren Server neu oder laden Sie das Plugin.
4.  Passen Sie die `config.yml` und `messages_de.properties` nach Ihren Wünschen an.

## ⚙️ Befehle

Der Hauptbefehl ist `/dimensionaccess` (Aliase: `/dac`, `/dimaccess`).

- `/dac <nether|end> <enable|disable|toggle>` - Ändert den Zugang für eine Dimension.
- `/dac status` - Zeigt den aktuellen Zugangsstatus für beide Dimensionen an.
- `/dac reload` - Lädt die Konfiguration neu.
- `/dac gui` - Öffnet die Verwaltungs-GUI.

## 🔐 Berechtigungen

- `dimensioncontrol.admin`: Gewährt vollen Zugriff auf alle Befehle, einschließlich Umschalten, Neuladen und GUI. (Standard: op)
- `dimensioncontrol.status`: Erlaubt die Verwendung von `/dac status`. (Standard: true)
- `dimensioncontrol.bypass.nether`: Erlaubt das Betreten des Nethers, auch wenn er gesperrt ist. (Standard: op)
- `dimensioncontrol.bypass.end`: Erlaubt das Betreten des Ends, auch wenn es gesperrt ist. (Standard: op)

## 🔧 Konfiguration

Die Konfiguration erfolgt hauptsächlich über die `config.yml` im Plugin-Verzeichnis.

```yaml
# config.yml
access:
  nether: true  # true = erlaubt, false = gesperrt
  end: true     # true = erlaubt, false = gesperrt

messages:
  # Diese Nachrichten sind derzeit hardcoded, werden aber in Zukunft hier konfigurierbar sein.
  no_access: "Zugriff auf diese Dimension ist derzeit eingeschränkt."
  # ...
```

Alle Spielernachrichten können in der Datei `messages_de.properties` angepasst werden.