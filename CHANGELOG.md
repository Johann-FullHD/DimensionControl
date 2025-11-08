# Changelog

Alle nennenswerten Änderungen an diesem Projekt werden in dieser Datei dokumentiert.

Das Format basiert auf [Keep a Changelog](https://keepachangelog.com/de/1.0.0/),
und dieses Projekt hält sich an [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-11-08

### Hinzugefügt
- **Grundlegende Dimensionskontrolle**: Befehl `/dimensionaccess <nether|end> <enable|disable>` zum Umschalten des Zugangs.
- **Sub-Befehle**: `status`, `reload`, `gui` und `toggle` für erweiterte Verwaltung.
- **Berechtigungen**: `dimensioncontrol.admin`, `dimensioncontrol.status`, `dimensioncontrol.bypass.nether`, `dimensioncontrol.bypass.end`.
- **GUI**: Einfaches Inventar zum Umschalten des Dimensionszugangs mit `/dac gui`.
- **Event-Listener**:
    - `EndPortalListener`: Verhindert die Aktivierung von End-Portalen.
    - `PortalListener`: Blockiert den Eintritt durch Nether- und End-Portale.
    - `TeleportListener`: Fängt Teleportationsversuche in gesperrte Dimensionen ab.
    - `CommandPreprocessListener`: Blockiert Befehle wie `/warp` oder `/tpa` zu gesperrten Dimensionen.
- **Konfiguration**: `config.yml` zum Speichern der Zugangseinstellungen.
- **Lokalisierung**: `messages_de.properties` für anpassbare Nachrichten (derzeit hardcoded in `MessageUtil`).
- **Build-System**: Maven `pom.xml` für Paper-API 1.20.6 und Java 17.
- **Dokumentation**: `README.md` mit grundlegenden Informationen.
