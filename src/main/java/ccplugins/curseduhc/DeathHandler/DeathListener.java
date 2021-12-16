package ccplugins.curseduhc.DeathHandler;

import ccplugins.curseduhc.DeathHandler.DeathCommands.DeadCommands;
import ccplugins.curseduhc.DeathHandler.DeathEnforcer.RejoinEnforcer;
import ccplugins.curseduhc.DeathHandler.DeathEnforcer.RespawnEnforcer;
import ccplugins.curseduhc.DeathHandler.DeathEnforcer.TeamEnforcer;
import ccplugins.curseduhc.UHCTeams.TeamsCreator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DeathListener implements Listener {

    private static DeathListener listener;
    private static ArrayList<UUID> deadPlayers;
    private static HashMap<UUID, Location> deadLocations;

    private static Plugin plugin;
    private DeathListener(Plugin plugin){
        DeathListener.plugin = plugin;
        deadLocations = new HashMap<>();
        deadPlayers = new ArrayList<>();
    }

    public static void init(JavaPlugin plugin){
        if(listener != null){return;}
        listener = new DeathListener(plugin);

        plugin.getServer().getPluginManager().registerEvents(listener,plugin);
        plugin.getServer().getPluginManager().registerEvents(new RespawnEnforcer(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new RejoinEnforcer(),plugin);

        plugin.getCommand("revive").setExecutor(new DeadCommands());

        new TeamEnforcer(plugin).runTaskTimer(plugin,5,2);
    }
    public static HashMap<UUID,Location> getDeadLocations(){
        return deadLocations;
    }
    public static ArrayList<UUID> getDeadPlayers(){
        return deadPlayers;
    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        deadPlayers.add(event.getEntity().getUniqueId());
        deadLocations.put(event.getEntity().getUniqueId(),event.getEntity().getLocation());
        event.setDeathMessage(ChatColor.of(new Color(255, 5, 0)) +""+ ChatColor.BOLD + "Felicidades a " + event.getEntity().getDisplayName() + " por cumplir sus sueños y morir!");
        for(Player p : plugin.getServer().getOnlinePlayers()){
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH,100,1);
        }
    }
}
