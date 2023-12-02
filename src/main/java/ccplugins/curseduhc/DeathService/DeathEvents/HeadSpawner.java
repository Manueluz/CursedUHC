package ccplugins.curseduhc.DeathService.DeathEvents;

import ccplugins.curseduhc.DeathService.DeathService;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

public class HeadSpawner implements DeathEventListener{
    @Override
    public void onPlayerDeath(Player player, DeathService service) {
        marchFences(player.getLocation());

        Skull skull = createSkull(player.getLocation().add(0,1,0));
        skull.setOwningPlayer(player);
    }

    private Skull createSkull(Location location) {
        World world = location.getWorld();
        Block block = world.getBlockAt(location);

        block.setType(Material.PLAYER_HEAD);

        return (Skull) block.getState();
    }

    private void marchFences(Location location) {
        World world = location.getWorld();

        while(world.getBlockAt(location).isEmpty()){
            world.getBlockAt(location).setType(Material.CRIMSON_FENCE);
            location.add(0,-1,0);
        }
    }
}
