package ccplugins.curseduhc.EventService.CustomEvents;

import ccplugins.curseduhc.Helpers.PluginHelper;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class SilverfishParty implements UHCEvent {
    private final Random rand;
    private final Listener listener;

    public SilverfishParty() {
        listener = new Listener() {
            @EventHandler
            public void onBlockBreak(BlockBreakEvent event){
                World world = event.getBlock().getWorld();
                for(int i = 0; i < rand.nextInt(2)+1; i++)
                    world.spawnEntity(event.getBlock().getLocation().add(0.5,0,0.5), EntityType.SILVERFISH);

            }
        };
        rand = new Random();
    }


    @Override
    public void start() {
        PluginHelper.registerListener(listener);
    }

    @Override
    public void end() {
        PluginHelper.unRegisterListener(BlockBreakEvent.class);
    }

    @Override
    public String getName() {
        return "Silverfish Party";
    }

    @Override
    public int getDuration() {
        return 180;
    }

    @Override
    public void update() {
    }
}
