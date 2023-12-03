package ccplugins.curseduhc.EventService.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;

public class BoltStorm implements UHCEvent {

    private final Random rand;

    public BoltStorm(){
        rand = new Random();
    }

    @Override
    public void update() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
                .map(Player::getLocation)
                .forEach(l -> {
                    if(l.getWorld()==null) return;

                    int x = l.getBlockX() + rand.nextInt(50)-25;
                    int z = l.getBlockZ() + rand.nextInt(50)-25;
                    int y = l.getWorld().getHighestBlockYAt(x,z);

                    l.setX(x);
                    l.setY(y+1);
                    l.setZ(z);

                    l.getWorld().strikeLightning(l);
                });
    }

    @Override
    public String getName() {
        return "Bolt Storm";
    }

    @Override
    public int getDuration() {
        return 120;
    }

    @Override
    public void end() {

    }

    @Override
    public void start() {

    }
}
