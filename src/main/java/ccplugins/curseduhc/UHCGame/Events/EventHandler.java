package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import ccplugins.curseduhc.UHCGame.Events.CustomEvents.*;
import ccplugins.curseduhc.UHCGame.Events.EventCommands.EventCommandTabCompleter;
import ccplugins.curseduhc.UHCGame.Events.EventCommands.EventCommands;
import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class EventHandler {

    private static final long TIME_BETWEEN_EVENTS = 1200/ GameControler.GLOBAL_SPEED_MULT;

    private final Stack<UHCEvent> eventQueue;
    private static EventHandler handler;
    private final Plugin plugin;
    private Countdown currentCountdown;

    private EventHandler(Plugin plugin){
        eventQueue = new Stack<>();
        this.plugin = plugin;
    }

    public static void init(JavaPlugin plugin){
        if(handler!=null){return;}
        handler = new EventHandler(plugin);
        plugin.getCommand("eventQueue").setExecutor(new EventCommands());
        plugin.getCommand("eventQueue").setTabCompleter(new EventCommandTabCompleter());
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
    public Countdown getEventCountdown(){
        return currentCountdown;
    }

    public void start(){
        currentCountdown = new Countdown(TIME_BETWEEN_EVENTS,plugin);
        currentCountdown.addLastTask(new EventQueueUpdate());
    }
    public void update(boolean resetCountdown){
        if(resetCountdown && !eventQueue.isEmpty()) {
            currentCountdown = new Countdown(TIME_BETWEEN_EVENTS, plugin);
            currentCountdown.addLastTask(new EventQueueUpdate());
        }
        eventQueue.pop().startEvent();
    }
    public void stop(){
        if(currentCountdown!=null)currentCountdown.pauseCountdown();
    }

    public void loadEvents(){
        ArrayList<UHCEvent> events = new ArrayList<>();
        events.add(new BoltStorm(plugin));
        events.add(new SolarStorm(plugin));
        //events.add(new SuddenDeath(plugin)); -> Discarded
        events.add(new SilverfishParty(plugin));
        events.add(new TouchGrass(plugin));
        events.add(new WeebParty(plugin));
        events.add(new HungerPlague(plugin));
        events.add(new SolarEclipse(plugin));
        events.add(new Stonks(plugin));
        Random rand = new Random();
        getEventQueue().clear();
        while(!events.isEmpty()){
            UHCEvent event = events.get(rand.nextInt(events.size()));
            addEvent(event);
            events.remove(event);
        }
    }
}
