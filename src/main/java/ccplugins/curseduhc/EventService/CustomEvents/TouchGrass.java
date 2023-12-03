package ccplugins.curseduhc.EventService.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;


public class TouchGrass implements UHCEvent {

    @Override
    public void start() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(player -> player.getGameMode() == GameMode.SURVIVAL)
                .forEach(player -> player.teleport(
                        player.getWorld()
                                .getHighestBlockAt(player.getLocation())
                                .getLocation()
                                .add(0,2,0)
                ));
    }

    @Override
    public String getName() {
        return "Touch Grass";
    }

    @Override
    public int getDuration() {
        return 1;
    }

    @Override
    public void update() {

    }

    @Override
    public void end() {

    }

}
