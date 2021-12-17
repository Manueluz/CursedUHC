package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Timer;
import java.util.TimerTask;

public abstract class UHCEvent extends BukkitRunnable {
    private String name;
    private long duration;
    Plugin plugin;

    public UHCEvent(Plugin plugin){
        this.plugin = plugin;
    }
    public void startEvent(long duration){
        Timer timer = new Timer();
        timer.schedule(new Stopper(this),duration);
        this.runTaskTimer(plugin,0,10);
    }

    @Override
    public void run() {
        update();
    }

    public abstract void update();

    public String getName() {return name;}
    void setName(String name) {this.name = name;}
    void setDuration(long duration){this.duration = duration;}
    public long getDuration() {return duration;}

    private class Stopper extends TimerTask{
        private UHCEvent event;
        public Stopper(UHCEvent event){
            this.event = event;
        }
        @Override
        public void run() {
            event.cancel();
        }
    }
}
