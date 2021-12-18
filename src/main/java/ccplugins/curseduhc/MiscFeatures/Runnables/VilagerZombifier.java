package ccplugins.curseduhc.MiscFeatures.Runnables;


import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class VilagerZombifier implements Listener {
    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent event){
        if(event.getEntity().getType() == EntityType.VILLAGER) {
            event.getLocation().getWorld().spawnEntity(event.getLocation(),EntityType.ZOMBIE);
            event.setCancelled(true);
        }
    }
}
