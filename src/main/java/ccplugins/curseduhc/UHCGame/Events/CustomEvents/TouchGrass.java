package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class TouchGrass extends UHCEvent {
    boolean isDone;
    public TouchGrass(Plugin plugin) {
        super(plugin);
        setDuration(100);
        setName("Touch Grass");
        isDone = false;
    }

    @Override
    public void update() {
        if (!isDone){
            isDone = true;
            for(Player player : plugin.getServer().getOnlinePlayers()){
                player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0,2,0));
            }

        }
    }
}
