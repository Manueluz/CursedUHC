package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class WeebParty extends UHCEvent{

    public WeebParty(Plugin plugin) {
        super(plugin);
        setDuration(300);
        setName("Weeb Party");
    }

    @Override
    public void update() {
        for(UUID id : GameControler.getControler().getAlivePlayers()){
            Player player = Bukkit.getPlayer(id);
            if(player.getWorld().getBlockAt(player.getLocation()).isLiquid()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,30,1));
            }
        }
    }

    @Override public void end() {}
}
