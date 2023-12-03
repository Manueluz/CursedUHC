package ccplugins.curseduhc.EventService.CustomEvents;

import ccplugins.curseduhc.UHCGame.GameControler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

//TODO: Update this, its deprecated anyways
public class SuddenDeath implements UHCEvent {
    private boolean isDone;
    public SuddenDeath() {
        isDone = false;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "WARNING USED DISCARDED EVENT");
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
    @Override public void end() {}

    @Override
    public String getName() {
        return "Sudden Death";
    }

    @Override
    public int getDuration() {
        return 1;
    }
}
