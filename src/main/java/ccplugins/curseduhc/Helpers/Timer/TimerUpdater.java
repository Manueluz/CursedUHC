package ccplugins.curseduhc.Helpers.Timer;

import org.bukkit.scheduler.BukkitRunnable;

public class TimerUpdater extends BukkitRunnable {

    private final Timer timer;

    public TimerUpdater(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        timer.tick();
    }
}
