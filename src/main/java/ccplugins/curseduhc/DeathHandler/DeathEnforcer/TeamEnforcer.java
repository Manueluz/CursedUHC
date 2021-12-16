package ccplugins.curseduhc.DeathHandler.DeathEnforcer;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TeamEnforcer extends BukkitRunnable {

    private final JavaPlugin plugin;

    public TeamEnforcer(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(UUID id : DeathListener.getDeadPlayers()){
            Player player = plugin.getServer().getPlayer(id);
            if(player != null && TeamHandler.getHandler().hasTeam(player) && player.getGameMode() == GameMode.SPECTATOR){
                for(UUID memberId : TeamHandler.getHandler().getTeam(player).getMembers()){
                    if(memberId != player.getUniqueId()){
                        Player teamMate = plugin.getServer().getPlayer(memberId);
                        if(!DeathListener.getDeadPlayers().contains(teamMate.getUniqueId())){
                            if(teamMate.getWorld().getName() != player.getWorld().getName()) {
                                player.teleport(teamMate.getLocation());
                            }
                            if(player.getSpectatorTarget() != teamMate) {
                                player.setSpectatorTarget(teamMate);
                            }
                        }
                    }
                }

            }
        }
    }
}
