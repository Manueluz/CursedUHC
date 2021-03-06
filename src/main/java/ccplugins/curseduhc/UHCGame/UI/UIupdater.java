package ccplugins.curseduhc.UHCGame.UI;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class UIupdater extends BukkitRunnable {
    public UIupdater(Plugin plugin){
        BossBarUI.init(plugin);
        ScoreboardUI.init(plugin);
    }
    @Override
    public void run() {
        BossBarUI.getBossBarUI().update();
        ScoreboardUI.getScoreboardUI().update();
        if (!GameControler.getControler().isGameStarted()){
            cancel();
        }
    }
}
