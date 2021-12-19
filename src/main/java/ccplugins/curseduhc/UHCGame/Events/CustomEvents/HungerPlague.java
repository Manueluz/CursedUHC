package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class HungerPlague extends UHCEvent {
    public HungerPlague(Plugin plugin) {
        super(plugin);
        setDuration(120000);
        setName("Hunger Plague");
    }

    @Override
    public void update() {
        for(UUID id : GameControler.getControler().getAlivePlayers()){
            Player player = Bukkit.getPlayer(id);
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,30,4));
        }
    }
}
