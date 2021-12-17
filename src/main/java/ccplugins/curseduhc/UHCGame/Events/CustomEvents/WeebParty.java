package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WeebParty extends UHCEvent{

    public WeebParty(Plugin plugin) {
        super(plugin);
        setDuration(300000);
        setName("Weeb Party");
    }

    @Override
    public void update() {
        for(Player player : plugin.getServer().getOnlinePlayers()){
            if(player.getWorld().getBlockAt(player.getLocation()).isLiquid()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,30,1));
            }
        }
    }
}
