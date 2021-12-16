package ccplugins.curseduhc.UHCGame;


import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Updates extends BukkitRunnable {

    private static final long UHC_DURATION = 60000;

    private final BossBar bar;
    private final Plugin plugin;

    public Updates(BossBar bar, Plugin plugin){
        this.bar = bar;
        this.plugin = plugin;
    }
    @Override
    public void run() {
        long timeLeft = UHC_DURATION - Duration.between(GameControler.getControler().getStartTime(), Instant.now()).toMillis();
        if(timeLeft > 0) {
            bar.setTitle("Time left: " + String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(timeLeft),
                    TimeUnit.MILLISECONDS.toMinutes(timeLeft) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeLeft)),
                    TimeUnit.MILLISECONDS.toSeconds(timeLeft) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeft))));

            bar.setProgress(1 - (double) (UHC_DURATION - timeLeft) / UHC_DURATION);
        }else {
            bar.setTitle("UHC Deadmatch");
            bar.setColor(BarColor.RED);
        }

    }
}
