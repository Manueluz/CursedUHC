package ccplugins.curseduhc.EventService.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;

public class SolarStorm implements UHCEvent {
    @Override
    public void update() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
                .filter(p -> p.getLocation().getBlock().getLightFromSky() == 15)
                .forEach(p -> p.setFireTicks(20));
    }


    @Override
    public String getName() {
        return "Solar Storm";
    }

    @Override
    public int getDuration() {
        return 360;
    }

    @Override
    public void start() {

    }

    @Override public void end() {

    }
}
