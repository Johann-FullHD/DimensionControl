# DimensionControl

[![Java CI with Maven](https://github.com/Johann-FullHD/DimensionControl/actions/workflows/maven.yml/badge.svg)](https://github.com/Johann-FullHD/DimensionControl/actions/workflows/maven.yml)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/Johann-FullHD/DimensionControl)](https://github.com/Johann-FullHD/DimensionControl/releases/latest)
[![GitHub license](https://img.shields.io/github/license/Johann-FullHD/DimensionControl)](https://github.com/Johann-FullHD/DimensionControl/blob/main/LICENSE)

**DimensionControl** is a lightweight and powerful Minecraft plugin for server administrators to manage player access to the Nether and The End dimensions with ease.

---

## ✨ Features

- **Granular Control**: Independently enable or disable access to the Nether and The End.
- **Comprehensive Blocking**: Intercepts all forms of travel, including portals, commands (`/tp`, `/warp`, etc.), and other plugin-driven teleports.
- **Bypass Permissions**: Grant specific players or groups the ability to bypass dimension restrictions.
- **Admin GUI**: A simple and intuitive in-game menu (`/dac gui`) for quick access management.
- **Dynamic Configuration**: Change settings on the fly without needing a server restart.
- **Persistent Settings**: Your choices are saved in `config.yml` and automatically loaded on server start.
- **Customizable Messages**: All player-facing messages can be configured.
- **Lightweight**: Designed for minimal performance impact.

---

## 🚀 Installation

1.  Download the latest `.jar` file from the [**Releases**](https://github.com/Johann-FullHD/DimensionControl/releases) page.
2.  Place the downloaded file into your server's `plugins` folder.
3.  Restart your server.
4.  (Optional) Configure the plugin by editing `plugins/DimensionControl/config.yml`.

---

## ⚙️ Usage

### Commands

The main command is `/dimensionaccess`. Aliases: `/dac`, `/dimaccess`.

| Command                                      | Description                                  | Permission                  |
| -------------------------------------------- | -------------------------------------------- | --------------------------- |
| `/dac <nether\|end> <enable\|disable\|toggle>` | Toggles access for the specified dimension.  | `dimensioncontrol.admin`    |
| `/dac status`                                | Shows the current access status of all dims. | `dimensioncontrol.status`   |
| `/dac reload`                                | Reloads the configuration from `config.yml`. | `dimensioncontrol.admin`    |
| `/dac gui`                                   | Opens the dimension management GUI.          | `dimensioncontrol.admin`    |

### Permissions

| Permission                        | Description                                       | Default |
| --------------------------------- | ------------------------------------------------- | ------- |
| `dimensioncontrol.admin`          | Grants full access to all plugin commands.        | `op`    |
| `dimensioncontrol.status`         | Allows viewing the dimension access status.       | `true`  |
| `dimensioncontrol.bypass.nether`  | Allows bypassing the Nether access restriction.   | `op`    |
| `dimensioncontrol.bypass.end`     | Allows bypassing The End access restriction.      | `op`    |

---

## 🔧 Configuration

File: `plugins/DimensionControl/config.yml`

```yaml
# Controls access to each dimension.
# true = access is allowed
# false = access is blocked
access:
  nether: true
  end: true

# All player-facing messages can be customized in 'messages_de.properties'
# In the future, this may move here.
messages:
  no_access: "Zugriff auf diese Dimension ist derzeit eingeschränkt."
  access_enabled: "Zugriff auf die Dimension wurde aktiviert."
  access_disabled: "Zugriff auf die Dimension wurde deaktiviert."
```

---

## 🛠️ Building from Source

To build the project yourself, you'll need [Git](https://git-scm.com/) and [Maven](https://maven.apache.org/).

```sh
# Clone the repository
git clone https://github.com/Johann-FullHD/DimensionControl.git
cd DimensionControl

# Build the project using Maven
mvn clean package
```

The compiled `.jar` file will be located in the `target` directory.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
