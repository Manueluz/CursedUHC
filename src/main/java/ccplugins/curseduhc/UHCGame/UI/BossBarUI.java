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
        bar = Bukkit.createBossBar(new NamespacedKey(plugin,"Time"),"Time remaining", BarColor.WHITE, BarStyle.SEGMENTED_20);
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
        String remainingTime = GameControler.getControler().getFinalCountDown().toString();
        Countdown finalCountdown = GameControler.getControler().getFinalCountDown();
        long totalSecs = finalCountdown.getRemainingSeconds();

        String g = ChatColor.of(new Color(121, 121, 121)) + "" + ChatColor.BOLD;
        String b = ChatColor.of(new Color(189, 188, 188)) + "";
        String B = ChatColor.of(new Color(255, 255, 255)) + "";
        String r = ChatColor.of(new Color(255, 20, 90)) + "";

        if(totalSecs >= 0) {
            bar.setTitle(g+"<"+b+ ChatColor.BOLD+"Time remaining: "+B+ ChatColor.BOLD+remainingTime+g+">");
            bar.setProgress(1 - ((double) (finalCountdown.getStartTime()) - finalCountdown.getRemainingSeconds()) / finalCountdown.getStartTime());
        }else {
            bar.setColor(BarColor.RED);
            bar.setProgress(1);
            bar.setTitle(g+"</"+r+ChatColor.BOLD+"UHC deadmatch"+g+">");
        }
        for(Player player : plugin.getServer().getOnlinePlayers()){
            bar.addPlayer(player);
        }
    }
}
