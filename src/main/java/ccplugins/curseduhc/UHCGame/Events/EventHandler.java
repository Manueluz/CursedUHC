package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Events.CustomEvents.UHCEvent;
import org.bukkit.plugin.Plugin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

public class EventHandler {

    private final Queue<UHCEvent> eventQueue;
    private static EventHandler handler;
    private final Plugin plugin;
    private Timer timer;

    private EventHandler(Plugin plugin){
        eventQueue = new LinkedList<>();
        this.plugin = plugin;
    }

    public static void init(Plugin plugin){
        if(handler!=null){return;}
        handler = new EventHandler(plugin);
    }

    public Queue<UHCEvent> getEventQueue() {
        return eventQueue;
    }
    public static EventHandler getHandler(){
        return handler;
    }

    public void addEvent(UHCEvent event){
        eventQueue.add(event);
    }


    public UHCEvent nextEvent(){
        return eventQueue.peek();
    }

    public void start(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new EventTimer(),0,10);
    }

    public void stop(){
        timer.cancel();
    }
}
