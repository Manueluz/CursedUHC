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
        int x = radius;
        int z = 0;

        double degrees = 0;

        for(UUID id : players){

            Player player = Bukkit.getPlayer(id);

            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,10,100));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,10,100));

            player.teleport(new Location(world,x,world.getHighestBlockYAt(x,z)+1,z));

            int newX = (int) (x*Math.cos(Math.toRadians(degrees)) - z*Math.sin(Math.toRadians(degrees)));
            int newZ = (int) (x*Math.sin(Math.toRadians(degrees)) + z*Math.cos(Math.toRadians(degrees)));

            x = newX;
            z = newZ;

            degrees += (double)360/players.size();

            player.teleport(new Location(world,x,world.getHighestBlockYAt(x,z)+1,z));
        }
    }
}
