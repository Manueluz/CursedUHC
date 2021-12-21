package ccplugins.curseduhc.UHCTeams.Animations;


import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;



public class ConnectionAnimation {
    public static void animate(Player player1, Player player2, Plugin plugin){
        new trailAnimation(player1.getLocation().add(0,1,0),player2,plugin);
        //new trailAnimation(player2.getLocation(),center,plugin); //Teams creator will launch one from each player
    }
    private static class trailAnimation extends BukkitRunnable {

        Vector direction;
        Location current;
        Location start;
        Player end;

        int step;

        public trailAnimation(Location start, Player end, Plugin plugin){
            direction = end.getLocation().subtract(start).toVector().normalize();

            this.start = start;
            this.current = start;
            this.end = end;

            this.runTaskTimer(plugin,0,2);

            step = 0;
        }

        @Override
        public void run() {
            if( !end.isOnline() ||current.distance(end.getLocation()) < 0.5f || step > 70){
                this.cancel();
            }
            direction = end.getLocation().subtract(start).toVector().normalize();
            step++;
            current = current.add(direction);
            current.getWorld().spawnParticle(Particle.REDSTONE,current.add(0,Math.cos(current.distance(end.getLocation())*3)/3f,0),8,0,0,0,0, new Particle.DustOptions(Color.BLUE,1f));
        }
    }
}
