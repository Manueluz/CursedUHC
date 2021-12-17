package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Events.CustomEvents.UHCEvent;
import ccplugins.curseduhc.UHCGame.Events.EventCommands.EventCommands;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Stack;
import java.util.Timer;

public class EventHandler {

    private static final long TIME_BETWEEN_EVENTS = 1200000;
    private final Stack<UHCEvent> eventQueue;
    private static EventHandler handler;
    private final Plugin plugin;
    private Timer timer;

    private EventHandler(Plugin plugin){
        eventQueue = new Stack<>();
        this.plugin = plugin;
    }

    public static void init(JavaPlugin plugin){
        if(handler!=null){return;}
        handler = new EventHandler(plugin);
        plugin.getCommand("eventQueue").setExecutor(new EventCommands());
    }

    public Stack<UHCEvent> getEventQueue() {
        return eventQueue;
    }
    public static EventHandler getHandler(){
        return handler;
    }

    public void addEvent(UHCEvent event){
        eventQueue.push(event);
    }


    public UHCEvent nextEvent(){
        return eventQueue.peek();
    }

    public void start(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new EventTimer(),TIME_BETWEEN_EVENTS,TIME_BETWEEN_EVENTS);
    }

    public void stop(){
        if(timer!=null)timer.cancel();
    }
}
