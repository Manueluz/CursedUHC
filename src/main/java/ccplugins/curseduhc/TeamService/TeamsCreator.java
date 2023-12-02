package ccplugins.curseduhc.TeamService;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.TeamService.Animations.ConnectionAnimation;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamsCreator extends BukkitRunnable {

    private final Plugin plugin = CursedUHC.plugin;
    private final TeamService service;

    public TeamsCreator(TeamService service){
        this.service = service;
    }

    @Override
    public void run() {
        plugin.getServer()
                .getOnlinePlayers()
                .stream()
                .filter(p -> !service.hasTeam(p.getUniqueId()))
                .filter(p -> !service.isDead(p.getUniqueId()))
                .forEach((player) ->
                    player.getNearbyEntities(50,50,50)
                        .stream()
                        .filter(e -> e instanceof Player)
                        .map(e -> (Player) e)
                        .filter(p -> !service.hasTeam(p.getUniqueId()))
                        .filter(p -> !service.isDead(p.getUniqueId()))
                        .forEach((player2) -> {
                            if(distance(player2,player) < 3)
                                service.createTeam(player.getUniqueId(),player2.getUniqueId());
                            else
                                ConnectionAnimation.animate(player,player2,plugin);
                    })
            );
    }

    private int distance(Player player1, Player player2) {
        return (int) player1.getLocation().distance(player2.getLocation());
    }
}
