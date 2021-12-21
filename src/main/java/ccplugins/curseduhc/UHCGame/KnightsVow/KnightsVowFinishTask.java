package ccplugins.curseduhc.UHCGame.KnightsVow;

import ccplugins.curseduhc.UHCGame.Countdown.FinishTask;

public class KnightsVowFinishTask extends FinishTask {
    @Override
    public void run() {
        KnightsVow.disable();
    }
}
