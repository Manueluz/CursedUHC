package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HungerPlague extends UHCEvent {
    public HungerPlague(Plugin plugin) {
        super(plugin);
        setDuration(120000);
        setName("Hunger Plague");
    }

    @Override
    public void update() {
        for(Player player : plugin.getServer().getOnlinePlayers()){
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,30,4));
        }
    }
}
