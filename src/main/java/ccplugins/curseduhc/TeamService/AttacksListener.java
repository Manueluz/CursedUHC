package ccplugins.curseduhc.TeamService;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

public class AttacksListener implements Listener {
    private final TeamService service;

    public AttacksListener(TeamService service){
        this.service = service;
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player player1)) return;
        if(!((event.getDamager()) instanceof Player player2)) return;

        Optional<Team> playerTeam = service.getTeam(player1.getUniqueId());
        Optional<Boolean> sameTeam = playerTeam
                .map(team -> team
                        .getMembers()
                        .contains(
                                player2.getUniqueId()
                        )
                );

        event.setCancelled(sameTeam.orElse(false));
    }
}
