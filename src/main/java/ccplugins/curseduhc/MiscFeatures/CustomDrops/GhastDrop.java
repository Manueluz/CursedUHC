package ccplugins.curseduhc.MiscFeatures.CustomDrops;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class GhastDrop implements Listener {
    Random rand = new Random();
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.GOLD_INGOT, rand.nextInt(2)));
    }
}
