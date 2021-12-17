package ccplugins.curseduhc.DeathHandler.DeathEnforcer;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEnforcer implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        if(GameControler.getControler().isGameStarted()) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }else {
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
