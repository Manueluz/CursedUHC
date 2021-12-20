package ccplugins.curseduhc.UHCGame.Events;

import ccplugins.curseduhc.UHCGame.Countdown.FinishTask;

public class EventQueueUpdate extends FinishTask {
    @Override
    public void run() {
        EventHandler.getHandler().update(true);
    }
}
