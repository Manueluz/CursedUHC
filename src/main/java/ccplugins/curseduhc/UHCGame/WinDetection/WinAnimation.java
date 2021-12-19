package ccplugins.curseduhc.UHCGame.WinDetection;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;


public class WinAnimation extends BukkitRunnable {

    private double radius;
    private double angle;
    private final Location loc;
    private final Random rand;

    public WinAnimation(UUID winner){
        loc = Bukkit.getPlayer(winner).getLocation().add(0,2,0);
        rand = new Random();
        radius = 1;
        angle = 0;
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            Location location = new Location(loc.getWorld(),0,0,0);

            location.add(loc);
            location.setY(0);

            int x = rand.nextInt(500)-250;
            int z = rand.nextInt(500)-250;
            int y = location.getWorld().getHighestBlockYAt(x,z)+3;

            location.add(x,y,z);

            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkMeta meta = firework.getFireworkMeta();

            meta.addEffect(getRandomEffect());
            firework.setFireworkMeta(meta);
        }
        for(double offset = 0; offset < 360; offset += 45){
            double x = radius*Math.cos(Math.toRadians(angle+offset));
            double z = radius*Math.sin(Math.toRadians(angle+offset));
            double y = Math.cos(radius);

            Location location = new Location(loc.getWorld(),0,0,0);

            location.add(loc);

            location.add(x,y,z);

            loc.getWorld().spawnParticle(Particle.GLOW_SQUID_INK,location,0,0,0,0,1);
        }

        radius += 0.1f;
        angle += 2f;

        if (angle > 360*8){
            this.cancel();
        }
    }

    private FireworkEffect getRandomEffect(){
        FireworkEffect.Builder builder = FireworkEffect.builder()
                .withColor(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withColor(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withColor(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withColor(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withFade(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withFade(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withFade(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)))
                .withFade(Color.fromRGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        builder.with(FireworkEffect.Type.values()[rand.nextInt(FireworkEffect.Type.values().length)]);
        return builder.build();
    }

}
