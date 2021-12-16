package ccplugins.curseduhc.DeathHandler.DeathEnforcer;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RejoinEnforcer implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if(DeathListener.getDeadPlayers().contains(event.getPlayer().getUniqueId())){
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }else {
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
