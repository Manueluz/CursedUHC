package ccplugins.curseduhc.EventService.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class WeebParty implements UHCEvent {
    @Override
    public void update() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(player -> player.getGameMode() == GameMode.SURVIVAL)
                .filter(player -> player.getLocation().getBlock().isLiquid())
                .filter(p -> p.getActivePotionEffects().stream().noneMatch(e -> e.getType() == PotionEffectType.WITHER))
                .forEach(p -> {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,40,4));
                });
    }

    @Override
    public String getName() {
        return "Weeb Party";
    }

    @Override
    public int getDuration() {
        return 300;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
