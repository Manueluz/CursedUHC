package ccplugins.curseduhc.DeathService.DeathEvents;

import ccplugins.curseduhc.DeathService.DeathService;
import org.bukkit.entity.Player;

public interface DeathEventListener {
    void onPlayerDeath(Player player, DeathService service);
}
