package ccplugins.curseduhc.DeathService.DeathEnforcer;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEnforcer implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }
}
