package ccplugins.curseduhc.UHCTeams;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCGame.GameControler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class TeamsCreator extends BukkitRunnable {

    private final Plugin plugin;
    private final TeamHandler handler;

    public TeamsCreator(Plugin plugin){
        this.plugin = plugin;
        this.handler = TeamHandler.getHandler();
    }

    @Override
    public void run() {
        if(!GameControler.getControler().isGameStarted()){return;}
        if(handler.teamsCompleted()){
            plugin.getServer().broadcastMessage(ChatColor.of(new Color(35,255,150)) + "Todos los jugadores tienen equipo!");
            cancel();
        }
        for(Player player : plugin.getServer().getOnlinePlayers()){
            if(handler.hasTeam(player)){continue;}
            Collection<Entity> nearPlayers = player.getWorld().getNearbyEntities(player.getLocation(),5,5,5, en->en instanceof Player);
            for(Entity entity : nearPlayers){
                Player p = (Player) entity;
                if(p.getUniqueId() == player.getUniqueId() || handler.hasTeam(p) || DeathListener.getDeadPlayers().contains(p.getUniqueId())){continue;}

                ArrayList<Player> teamMembers = new ArrayList<>();

                teamMembers.add(player);
                teamMembers.add(p);

                handler.createTeam(teamMembers);
            }
        }
    }
}
