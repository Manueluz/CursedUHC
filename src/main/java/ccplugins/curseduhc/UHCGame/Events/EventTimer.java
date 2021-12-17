package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Events.CustomEvents.UHCEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class EventTimer extends BukkitRunnable {
    @Override
    public void run() {
        EventHandler handler = EventHandler.getHandler();
        UHCEvent event = handler.getEventQueue().pop();
        if(event != null){
            event.startEvent(event.getDuration());
        }
    }
}
