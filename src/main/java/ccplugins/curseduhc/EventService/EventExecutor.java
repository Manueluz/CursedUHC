package ccplugins.curseduhc.EventService;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.EventService.CustomEvents.UHCEvent;
import ccplugins.curseduhc.Helpers.Timer.TaskScope;
import ccplugins.curseduhc.Helpers.Timer.Timer;

public class EventExecutor {
    public static void executeEvent(UHCEvent event){
        Timer timer = new Timer(event.getDuration());

        CursedUHC.logger.info("Now starting event: " + event.getName());

        timer.addTask(event::start, TaskScope.START);
        timer.addTask(event::update, TaskScope.REPEATING);
        timer.addTask(event::end, TaskScope.END);

        timer.start();
    }
}
