package ccplugins.curseduhc.UHCGame.Countdown;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Countdown {

    final long startTime;
    long remainingTime;
    private BukkitTask updater;
    private final Plugin plugin;
    private ArrayList<FinishTask> tasks;

    public Countdown(long duration, Plugin plugin){
        startTime = duration;
        remainingTime = duration;
        tasks = new ArrayList<>();
        updater = new CountUpdater(this).runTaskTimer(plugin,0,20);
        this.plugin = plugin;
    }

    public void pauseCountdown(){
        updater.cancel();
    }

    public void resumeCountDown(){
        updater = new CountUpdater(this).runTaskTimer(plugin,0,20);
    }

    public void addLastTask(FinishTask task){
        tasks.add(task);
    }

    public long getRemainingSeconds(){
        return remainingTime;
    }
    public long getStartTime(){
        return startTime;
    }

    private static class CountUpdater extends BukkitRunnable {
        private final Countdown countdown;
        public CountUpdater(Countdown countdown){
            this.countdown = countdown;
        }
        @Override
        public void run() {
            countdown.remainingTime -= 1;
            if(countdown.remainingTime == 0){
                for(FinishTask task : countdown.tasks){
                    task.run();
                }
            }
        }
    }
}
