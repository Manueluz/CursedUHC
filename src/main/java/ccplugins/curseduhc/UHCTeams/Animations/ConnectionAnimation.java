package ccplugins.curseduhc.UHCTeams.Animations;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;



public class ConnectionAnimation {
    public static void animate(Player player1, Player player2, Plugin plugin){
        new trailAnimation(player1,player2,plugin);
        //new trailAnimation(player2.getLocation(),center,plugin); //Teams creator will launch one from each player
    }
    private static class trailAnimation extends BukkitRunnable {

        Vector direction;
        Location current;
        Player start;
        Player end;

        int step;

        public trailAnimation(Player start, Player end, Plugin plugin){
            direction = end.getLocation().subtract(start.getLocation().add(0,1,0)).toVector().normalize();

            this.start = start;
            this.current = start.getLocation().add(0,1,0);
            this.end = end;

            this.runTaskTimer(plugin,0,1);

            step = 0;
        }

        @Override
        public void run() {
            if( !end.isOnline() ||current.distance(end.getLocation().add(0,1,0)) < 0.5f || step > 3600){
                this.cancel();
            }
            direction = end.getLocation().add(0,1,0).subtract(current).toVector().normalize().multiply(0.2);
            step++;
            current = current.add(direction);
            start.spawnParticle(Particle.SMALL_FLAME,current,8,0,0,0,0);
        }
    }
}
