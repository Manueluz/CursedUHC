package ccplugins.curseduhc.DeathService;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathListener implements Listener {

    private final DeathService service;

    public DeathListener(DeathService service){
        this.service = service;
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        //TODO: Make this only work when game is started
        service.notifyEvent(event.getEntity());
    }
}
