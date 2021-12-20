package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import ccplugins.curseduhc.UHCGame.Countdown.FinishTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class UHCEvent extends BukkitRunnable {

    private String name;
    private long duration;
    Plugin plugin;

    public UHCEvent(Plugin plugin){this.plugin = plugin;}

    public void startEvent(){
        Countdown countdown = new Countdown(duration,plugin);
        countdown.addLastTask(new Stopper(this));
        this.runTaskTimer(plugin,0,10);
    }

    @Override
    public void run() {update();}

    public abstract void end();
    public abstract void update();

    public String getName() {return name;}
    void setName(String name) {this.name = name;}
    void setDuration(long duration){this.duration = duration;}


    private static class Stopper extends FinishTask {
        private final UHCEvent event;
        public Stopper(UHCEvent event){
            this.event = event;
        }
        @Override
        public void run() {
            event.cancel();
        }
    }
}
