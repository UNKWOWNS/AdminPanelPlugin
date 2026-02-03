import os

BASE = "AdminPanel"

paths = [
    f"{BASE}/src/main/java/me/tailzz/adminpanel",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/commands",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/gui",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/vanish",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/listeners",
    f"{BASE}/src/main/resources",
]

files = [
    f"{BASE}/src/main/java/me/tailzz/adminpanel/AdminPanel.java",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/commands/GuiCommand.java",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/gui/AdminGUI.java",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/vanish/VanishManager.java",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/listeners/JoinListener.java",
    f"{BASE}/src/main/java/me/tailzz/adminpanel/listeners/ChatListener.java",
    f"{BASE}/src/main/resources/plugin.yml",
]

for path in paths:
    os.makedirs(path, exist_ok=True)

for file in files:
    if not os.path.exists(file):
        open(file, "w").close()

print("✅ AdminPanel plugin structure created.")
