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
        setDuration(120);
        setName("Hunger Plague");
    }

    @Override
    public void update() {
        for(UUID id : GameControler.getControler().getAlivePlayers()){
            Player player = Bukkit.getPlayer(id);
            //If he already has the effect and we set it again the next tick minecraft will bug and cancel the effect
            for(PotionEffect effect : player.getActivePotionEffects()){
                if(effect.getType() == PotionEffectType.HUNGER){
                    return;
                }
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,30,3));
        }
    }
    @Override public void end() {}
}
