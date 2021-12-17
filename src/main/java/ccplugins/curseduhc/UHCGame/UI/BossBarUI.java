package ccplugins.curseduhc.UHCGame.UI;

import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import ccplugins.curseduhc.UHCGame.GameControler;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.util.Iterator;

public class BossBarUI {

    private final BossBar bar;
    private static BossBarUI bossBarUI;
    private final Plugin plugin;

    private BossBarUI(Plugin plugin){
        this.plugin = plugin;
        bar = Bukkit.createBossBar(new NamespacedKey(plugin,"Time"),"Time remaining", BarColor.BLUE, BarStyle.SOLID);
        bar.setVisible(true);
    }

    public static void init(Plugin plugin){
        if(bossBarUI != null){return;}

        for (Iterator<KeyedBossBar> it = plugin.getServer().getBossBars(); it.hasNext(); ) {
            BossBar bar = it.next();
            bar.removeAll();
            bar.setVisible(false);
        }

        bossBarUI = new BossBarUI(plugin);
    }

    public static BossBarUI getBossBarUI(){return bossBarUI;}

    public void update(){
        Countdown finalCountdown = GameControler.getControler().getFinalCountDown();

        long totalSecs = finalCountdown.getRemainingSeconds();

        int hours = (int) (totalSecs / 3600);
        int minutes = (int) ((totalSecs % 3600) / 60);
        int seconds = (int) (totalSecs % 60);

        String remainingTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);


        if(totalSecs >= 0) {
            bar.setTitle(ChatColor.of(new Color(4, 255, 225)) + "Time remaining: " + remainingTime);
            bar.setProgress(1 - ((double) (finalCountdown.getStartTime()) - finalCountdown.getRemainingSeconds()) / finalCountdown.getStartTime());
        }else {
            bar.setColor(BarColor.RED);
            bar.setProgress(1);
            bar.setTitle(ChatColor.of(new Color(255, 20, 90))+"UHC deadmatch");
        }
        for(Player player : plugin.getServer().getOnlinePlayers()){
            bar.addPlayer(player);
        }
    }
}
