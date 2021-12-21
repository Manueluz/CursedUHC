package ccplugins.curseduhc.UHCGame.PlayerSpreader;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerSpreader {
    private PlayerSpreader(){}

    public static void spreadPlayers(World world, int radius, ArrayList<UUID> players, Plugin plugin){

        //Trap them
        for(UUID id : players){
            Player player = Bukkit.getPlayer(id);
            player.setGameMode(GameMode.ADVENTURE);
        }

        new placePlayerTask(players,radius,world).runTaskTimer(plugin,0,40);//If we tp all at once the server will die
    }
    private static class placePlayerTask extends BukkitRunnable{

        private final ArrayList<UUID> players;
        private int index;
        private double degrees;
        private final int radius;
        private final World world;

        public placePlayerTask(ArrayList<UUID> players, int radius, World world){
            this.players = players;
            this.radius = radius;
            this.world = world;

            index = 0;
            degrees = 0;
        }

        @Override
        public void run() {
            if(index == players.size()){this.cancel();end();return;}
            Player player = Bukkit.getPlayer(players.get(index));
            if(player != null) {
                player.getInventory().clear();
                int potionDuration = 160 + 40 * players.size();
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, potionDuration, 100));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, potionDuration, 100));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, potionDuration, 100));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, potionDuration, 100));

                int x = (int) (radius * Math.cos(Math.toRadians(degrees)));
                int z = (int) (radius * Math.sin(Math.toRadians(degrees)));

                degrees += (double) 360 / players.size();
                player.teleport(new Location(world, x+0.5, 250, z+0.5));

                //floor
                world.getBlockAt(player.getLocation().add(0,-1,0)).setType(Material.GLASS);
                //walls
                for(int i = 1; i <= 2; i++){
                    world.getBlockAt(player.getLocation().add(1,i-1,0)).setType(Material.GLASS);
                    world.getBlockAt(player.getLocation().add(-1,i-1,0)).setType(Material.GLASS);
                    world.getBlockAt(player.getLocation().add(0,i-1,1)).setType(Material.GLASS);
                    world.getBlockAt(player.getLocation().add(0,i-1,-1)).setType(Material.GLASS);
                }
                //top
                world.getBlockAt(player.getLocation().add(0,2,0)).setType(Material.GLASS);
            }
            index++;
        }

        private void end(){
            for(UUID id : players){
                Player player = Bukkit.getPlayer(id);
                world.getBlockAt(player.getLocation().add(0,-1,0)).setType(Material.AIR);
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}
