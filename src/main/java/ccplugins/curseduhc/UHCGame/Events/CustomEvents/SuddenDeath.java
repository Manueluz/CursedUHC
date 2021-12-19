package ccplugins.curseduhc.UHCGame.Events.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;
import java.util.UUID;


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
            for(UUID id : GameControler.getControler().getAlivePlayers()){
                Player player = Bukkit.getPlayer(id);
                if(player.getHealth() < lowestHealth){
                    lowestHealth = player.getHealth();
                }
            }

            ArrayList<Player> lowList = new ArrayList<>();
            for(UUID id : GameControler.getControler().getAlivePlayers()){
                Player player = Bukkit.getPlayer(id);
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
