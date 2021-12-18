package ccplugins.curseduhc.MiscFeatures.Runnables;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;

public class VilagerZombifier extends BukkitRunnable {
    @Override
    public void run() {
        for(Entity entity : Bukkit.getWorld("world").getEntitiesByClass(Villager.class)){
            entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ZOMBIE);
            entity.teleport(entity.getLocation().add(0,-600,0));
        }
    }
}
