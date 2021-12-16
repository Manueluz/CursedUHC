package ccplugins.curseduhc.UHCTeams;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttacksListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            TeamHandler handler = TeamHandler.getHandler();
            if (handler.hasTeam((Player) event.getEntity())){
                if(handler.getTeam((Player) event.getEntity()).isMember((Player) event.getDamager())){
                    event.setCancelled(true);
                }
            }
        }
    }
}
