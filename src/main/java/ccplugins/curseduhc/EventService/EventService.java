package ccplugins.curseduhc.EventService;

import ccplugins.curseduhc.CursedUHCConfig;
import ccplugins.curseduhc.EventService.CustomEvents.*;
import ccplugins.curseduhc.EventService.EventCommands.EventCommandTabCompleter;
import ccplugins.curseduhc.EventService.EventCommands.EventCommands;
import ccplugins.curseduhc.Helpers.PluginHelper;
import ccplugins.curseduhc.Helpers.Timer.TaskScope;
import ccplugins.curseduhc.Helpers.Timer.Timer;
import ccplugins.curseduhc.Service.Service;

import java.util.*;

public class EventService implements Service {
    private static final int TIME_BETWEEN_EVENTS = 900;

    private Timer eventTimer;

    private Queue<UHCEvent> eventQueue;

    @Override
    public void init(CursedUHCConfig config) {
        eventQueue = new LinkedList<>();

        PluginHelper.registerCommand("eventQueue", new EventCommands(this));
        PluginHelper.registerTabCompleter("eventQueue", new EventCommandTabCompleter());

        loadEvents();

        //TODO: Remove this
        start();
    }

    public void start() {
        eventTimer = new Timer(TIME_BETWEEN_EVENTS);
        eventTimer.addTask(this::nextEvent, TaskScope.END);
        eventTimer.start();
    }

    @Override
    public void stop() {
        if(eventTimer != null)
            eventTimer.pause();
    }


    public void nextEvent() {
        if(eventQueue.isEmpty()) return;

        EventExecutor.executeEvent(eventQueue.poll());

        start();
    }

    public void loadEvents(){
        List<UHCEvent> events = new ArrayList<>();
        events.add(new BoltStorm());
        events.add(new SolarStorm());
        //events.add(new SuddenDeath(plugin)); -> Discarded
        events.add(new SilverfishParty());
        events.add(new TouchGrass());
        events.add(new WeebParty());
        events.add(new HungerPlague());
        events.add(new SolarEclipse());
        events.add(new Stonks());

        Collections.shuffle(events);

        eventQueue.addAll(events);
    }

    public Timer getInternalTimer() {
        return eventTimer;
    }

    public List<UHCEvent> getEvents() {
        return new ArrayList<>(eventQueue);
    }

    public void skip() {
        eventQueue.poll();
    }
}
