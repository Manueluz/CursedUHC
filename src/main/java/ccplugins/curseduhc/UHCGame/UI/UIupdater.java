package ccplugins.curseduhc.UHCGame.UI;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class UIupdater extends BukkitRunnable {
    public UIupdater(Plugin plugin){
        BossBarUI.init(plugin);
    }
    @Override
    public void run() {
        BossBarUI.getBossBarUI().update();
    }
}
