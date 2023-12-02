package ccplugins.curseduhc.DeathService.DeathEnforcer;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.DeathService.DeathService;
import ccplugins.curseduhc.TeamService.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class TeamEnforcer extends BukkitRunnable {


    private final DeathService service;

    public TeamEnforcer(DeathService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.getDeadPlayers()
                .forEach(p -> {
                    Optional<Team> team = service.getTeam(p);
                    Optional<UUID> mateId = team
                            .map(Team::getMembers)
                            .orElse(new ArrayList<>())
                            .stream()
                            .filter(member -> !service.isDead(member))
                            .findFirst();

                    if(mateId.isEmpty()) return;

                    Player player = Bukkit.getPlayer(p);
                    Player mate = Bukkit.getPlayer(mateId.get());

                    if(player == null || mate == null) return;

                    if(!sameDimension(player,mate))
                        player.teleport(mate);

                    if(player.getGameMode() == GameMode.SPECTATOR){
                        player.setSpectatorTarget(mate);
                    }
                });
    }

    private boolean sameDimension(Player player, Player mate) {
        return player.getWorld().equals(mate.getWorld());
    }
}
