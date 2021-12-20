package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class SilverfishParty extends UHCEvent {

    static final BlockBreakListener listener = new BlockBreakListener();

    public SilverfishParty(Plugin plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(listener,plugin);
        setName("Silverfish Party");
        setDuration(180);
    }

    @Override
    public void update() {
        listener.enable();
    }

    @Override
    public void end() {
        listener.disable();
    }

    private static class BlockBreakListener implements Listener{
        private static final Random rand = new Random();
        private Boolean enabled;

        public void enable(){this.enabled = true;}
        public void disable(){this.enabled = true;}

        @EventHandler
        public void onBlockBreak(BlockBreakEvent event){
            if(enabled && rand.nextInt(100)<20){
                World world = event.getBlock().getWorld();
                for(int i = 0; i < rand.nextInt(2); i++){
                    world.spawnEntity(event.getBlock().getLocation().add(0.5,0,0.5), EntityType.SILVERFISH);
                }
            }
        }
    }

}
