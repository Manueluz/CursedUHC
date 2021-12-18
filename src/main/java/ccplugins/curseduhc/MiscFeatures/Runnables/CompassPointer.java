package ccplugins.curseduhc.MiscFeatures.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CompassPointer extends BukkitRunnable {
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setCompassTarget(new Location(player.getWorld(),0,0,0));
        }
    }
}
