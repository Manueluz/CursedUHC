package ccplugins.curseduhc.Helpers.Timer;

import ccplugins.curseduhc.CursedUHC;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timer {
    private final long startTime;
    private long remainingTime;
    private BukkitTask updater;
    private final Map<TaskScope,List<Task>> tasks;

    public Timer(long duration){
        startTime = duration;
        remainingTime = duration;
        tasks = new HashMap<>();
    }

    public void addTask(Task task, TaskScope scope){
        if(!tasks.containsKey(scope))
            tasks.put(scope, new ArrayList<>());
        tasks.get(scope).add(task);
    }

    public void tick() {
        remainingTime--;

        if(remainingTime <= 0){
            remainingTime = 0;
            tickTasks(TaskScope.END);
            updater.cancel();
            return;
        }

        tickTasks(TaskScope.REPEATING);
    }

    public void pause(){
        if(updater!=null)
            updater.cancel();
    }

    public void start(){
        updater = createUpdater();
        tickTasks(TaskScope.START);
    }

    public long getRemainingSeconds(){
        return remainingTime;
    }

    public long getStartTime(){
        return startTime;
    }
    public void setRemainingSeconds(long seconds){
        this.remainingTime = seconds;
    }

    public String toString(){
        long totalSecs = getRemainingSeconds();

        int hours = (int) (totalSecs / 3600);
        int minutes = (int) ((totalSecs % 3600) / 60);
        int seconds = (int) (totalSecs % 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private BukkitTask createUpdater() {
        return new TimerUpdater(this).runTaskTimer(CursedUHC.plugin,0,20);
    }
    private void tickTasks(TaskScope scope){
        tasks.getOrDefault(scope, new ArrayList<>())
                .forEach(Task::run);
    }
}
