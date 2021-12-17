package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


import java.util.Random;

public class Stonks extends UHCEvent {
    Random rand;
    public Stonks(Plugin plugin) {
        super(plugin);
        setName("Stonks");
        setDuration(120000);
        rand = new Random();
    }

    @Override
    public void update() {
        for(World world : plugin.getServer().getWorlds()){
            for(Item entity : world.getEntitiesByClass(Item.class)){
                if(entity.getItemStack().getType() == Material.EMERALD){
                    int number = rand.nextInt(3);
                    if(number == 0){
                        entity.teleport(entity.getLocation().add(0,-600,0));
                    }else {
                        entity.setItemStack(new ItemStack(Material.DIAMOND, number+entity.getItemStack().getAmount()));
                    }
                }
            }
        }
    }
}
