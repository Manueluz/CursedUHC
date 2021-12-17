package ccplugins.curseduhc.DeathHandler.DeathEnforcer;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.ChatColor;
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
        if(GameControler.getControler().isGameStarted() && GameControler.getControler().getGamePlayers().contains(event.getPlayer().getUniqueId())){
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            event.getPlayer().sendMessage(ChatColor.GOLD +""+ ChatColor.BOLD +"You have joined after the game started you are now an spectator");
        }
    }
}
