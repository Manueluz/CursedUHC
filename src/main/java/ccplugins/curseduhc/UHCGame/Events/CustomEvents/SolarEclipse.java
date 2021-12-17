package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class SolarEclipse extends UHCEvent {
    public SolarEclipse(Plugin plugin) {
        super(plugin);
        setDuration(840000);
        setName("Solar Eclipse");
    }

    @Override
    public void update() {
        for(World world : plugin.getServer().getWorlds()){
            world.setTime(16000);
        }
    }
}
