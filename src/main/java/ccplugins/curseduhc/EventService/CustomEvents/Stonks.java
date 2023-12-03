package ccplugins.curseduhc.EventService.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;

import java.util.Random;

public class Stonks implements UHCEvent {
    private final Random rand;
    public Stonks() {
        rand = new Random();
    }

    @Override
    public void update() {
        Bukkit.getServer()
                .getWorlds()
                .stream()
                .flatMap(w -> w.getEntitiesByClass(Item.class).stream())
                .filter(i -> i.getItemStack().getType() == Material.EMERALD)
                .forEach(i -> {
                    if(rand.nextInt(3) == 0)
                        i.remove();
                    i.getItemStack().setType(Material.DIAMOND);
                });
    }

    @Override
    public String getName() {
        return "Stonks";
    }

    @Override
    public int getDuration() {
        return 120;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
