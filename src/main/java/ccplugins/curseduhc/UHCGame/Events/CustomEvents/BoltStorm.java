package ccplugins.curseduhc.UHCGame.Events.CustomEvents;


import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;
import java.util.UUID;

public class BoltStorm extends UHCEvent {

    Random rand;

    public BoltStorm(Plugin plugin){
        super(plugin);
        setName("Bolt Storm");
        setDuration(120);
        rand = new Random();
    }

    @Override
    public void update() {
        for(UUID id : GameControler.getControler().getAlivePlayers()){
            Player player = Bukkit.getPlayer(id);
            Location location = player.getLocation();

            int x = location.getBlockX() + rand.nextInt(50)-25;
            int z = location.getBlockZ() + rand.nextInt(50)-25;
            int y = location.getWorld().getHighestBlockYAt(x,z);

            location.setX(x);
            location.setY(y+1);
            location.setZ(z);

            location.getWorld().strikeLightning(location);
        }
    }
    @Override public void end() {}
}
