package ccplugins.curseduhc.DeathService.DeathEnforcer;

import ccplugins.curseduhc.ChatService.ChatModes.ChatMode;
import ccplugins.curseduhc.DeathService.DeathListener;
import ccplugins.curseduhc.DeathService.DeathService;
import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RejoinEnforcer implements Listener {
    private final DeathService service;
    public RejoinEnforcer(DeathService service){
        this.service = service;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        //TODO: Make this work with players that join after the game starts

        if(service.isDead(event.getPlayer().getUniqueId())){
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            service.setChatMode(event.getPlayer().getUniqueId(), ChatMode.SPECTATOR_CHAT);
            return;
        }

        service.setChatMode(event.getPlayer().getUniqueId(), ChatMode.GLOBAL_CHAT);
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
    }
}
