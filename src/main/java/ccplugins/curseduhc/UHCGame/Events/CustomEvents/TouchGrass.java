package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;


public class TouchGrass extends UHCEvent {
    boolean isDone;
    public TouchGrass(Plugin plugin) {
        super(plugin);
        setDuration(1);
        setName("Touch Grass");
        isDone = false;
    }

    @Override
    public void update() {
        if (!isDone){
            isDone = true;
            for(UUID id : GameControler.getControler().getAlivePlayers()){
                Player player = Bukkit.getPlayer(id);
                player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0,2,0));
            }

        }
    }
    @Override public void end() {}
}
