package ccplugins.curseduhc.UHCGame.PlayerSpreader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerSpreader {
    private PlayerSpreader(){}

    public static void spreadPlayers(World world, int radius, ArrayList<UUID> players){
        double degrees = 0;

        for(UUID id : players){
            Player player = Bukkit.getPlayer(id);
            if(player == null){continue;}
            player.getInventory().clear();
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,10,100));

            int x = (int) (radius*Math.cos(Math.toRadians(degrees)));
            int z = (int) (radius*Math.sin(Math.toRadians(degrees)));

            degrees += (double)360/players.size();
            player.teleport(new Location(world,x,world.getHighestBlockYAt(x,z)+1,z));
        }
    }
}
