package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SolarStorm extends UHCEvent{

    public SolarStorm(Plugin plugin) {
        super(plugin);
        setName("Solar Storm");
        setDuration(360000);
    }

    @Override
    public void update() {
        for(Player player : plugin.getServer().getOnlinePlayers()){
            if(player.getEyeLocation().getBlockY() > player.getWorld().getHighestBlockYAt(player.getLocation())){
                player.setFireTicks(20);
            }
        }
    }
}
