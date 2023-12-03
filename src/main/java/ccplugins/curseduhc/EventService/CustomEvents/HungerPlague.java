package ccplugins.curseduhc.EventService.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HungerPlague implements UHCEvent {
    @Override
    public void update() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
                .filter(p -> p.getActivePotionEffects().stream().noneMatch(e -> e.getType() == PotionEffectType.HUNGER))
                .forEach(p -> {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,40,4));
                });
    }

    @Override
    public String getName() {
        return "Hunger Plague";
    }

    @Override
    public int getDuration() {
        return 120;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
