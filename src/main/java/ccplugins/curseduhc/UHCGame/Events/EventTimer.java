package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Events.CustomEvents.UHCEvent;

import java.util.TimerTask;

public class EventTimer extends TimerTask {
    @Override
    public void run() {
        EventHandler handler = EventHandler.getHandler();
        UHCEvent event = handler.getEventQueue().poll();
        if(event != null){
            event.startEvent(event.getDuration());
        }
    }
}
