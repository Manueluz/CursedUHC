package ccplugins.curseduhc.DeathService.DeathEvents;

import ccplugins.curseduhc.DeathService.DeathService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import java.awt.Color;

public class Anouncer implements DeathEventListener {
    @Override
    public void onPlayerDeath(Player player, DeathService service) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH,100,1);
            p.sendMessage(ChatColor.of(new Color(255, 5, 0)) +""+ ChatColor.BOLD + "Felicidades a " + player.getDisplayName() + " por cumplir sus sueños y morir!");
        });
    }
}
