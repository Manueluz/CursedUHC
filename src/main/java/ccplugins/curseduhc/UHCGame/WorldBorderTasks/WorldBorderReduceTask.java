package ccplugins.curseduhc.UHCGame.WorldBorderTasks;

import ccplugins.curseduhc.UHCGame.Countdown.FinishTask;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldBorderReduceTask extends FinishTask {

    private final int size;
    private final int seconds;

    public WorldBorderReduceTask(int size, int seconds){
        this.size = size;
        this.seconds = seconds;
    }
    @Override
    public void run() {
        for(World world : Bukkit.getServer().getWorlds()){
            world.getWorldBorder().setCenter(0,0);
            world.getWorldBorder().setSize(size,seconds);
        }
    }
}
