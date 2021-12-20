package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class SolarStorm extends UHCEvent{

    public SolarStorm(Plugin plugin) {
        super(plugin);
        setName("Solar Storm");
        setDuration(360);
    }

    @Override
    public void update() {
        for(UUID id : GameControler.getControler().getAlivePlayers()){
            Player player = Bukkit.getPlayer(id);
            if(player.getLocation().getBlock().getLightFromSky() == 15 && player.getGameMode() == GameMode.SURVIVAL){
                player.setFireTicks(20);
            }
        }
    }
    @Override public void end() {}
}
