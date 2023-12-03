package ccplugins.curseduhc.EventService.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class SolarEclipse implements UHCEvent {
    @Override
    public void start() {

    }

    @Override
    public void update() {
        for(World world : Bukkit.getServer().getWorlds())
            world.setTime(16000);
    }

    @Override public void end() {

    }

    @Override
    public String getName() {
        return "Solar Eclipse";
    }

    @Override
    public int getDuration() {
        return 840;
    }
}
