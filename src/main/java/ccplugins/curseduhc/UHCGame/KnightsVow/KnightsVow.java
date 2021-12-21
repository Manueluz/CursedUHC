package ccplugins.curseduhc.UHCGame.KnightsVow;

import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class KnightsVow implements Listener {

    private static boolean enabled = true;
    private static boolean registered = false;

    public static void enable(){enabled = true;}
    public static void disable(){enabled = false;}

    public static void init(Plugin plugin, long duration){
        if(!registered) {
            plugin.getServer().getPluginManager().registerEvents(new KnightsVow(), plugin);
            registered = true;
        }
        new Countdown(duration, plugin).addLastTask(new KnightsVowFinishTask());
        enable();
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(enabled && event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            event.setCancelled(true);
        }
    }
}
