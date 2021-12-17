package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;


public class SuddenDeath extends UHCEvent {
    private boolean isDone;
    public SuddenDeath(Plugin plugin) {
        super(plugin);
        setDuration(100);
        setName("Sudden Death");
        isDone = false;
    }

    @Override
    public void update() {
        if (!isDone){
            isDone = true;

            double lowestHealth = 20;
            for(Player player : plugin.getServer().getOnlinePlayers()){
                if(player.getHealth() < lowestHealth){
                    lowestHealth = player.getHealth();
                }
            }

            ArrayList<Player> lowList = new ArrayList<>();
            for(Player player : plugin.getServer().getOnlinePlayers()){
                if(player.getHealth() == lowestHealth){
                    lowList.add(player);
                }
            }

            if(lowList.size() == 1) {
                lowList.get(0).damage(200);
            }
        }
    }
}
