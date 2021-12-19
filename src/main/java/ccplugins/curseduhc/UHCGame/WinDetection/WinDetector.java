package ccplugins.curseduhc.UHCGame.WinDetection;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCGame.GameControler;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.UUID;

public class WinDetector extends BukkitRunnable {
    @Override
    public void run() {
        ArrayList<UUID> players = new ArrayList<>(GameControler.getControler().getGamePlayers());
        players.removeIf(player -> DeathListener.getDeadPlayers().contains(player));

        switch (players.size()){
            case 1:
                Player winner = Bukkit.getPlayer(players.get(0));
                Bukkit.getServer().broadcastMessage(winner.getDisplayName() + " ha ganado la primera edicion de Cursed UHC!!!");
                GameControler.getControler().stopGame();
                break;
            case 2:
                Player player1 = Bukkit.getPlayer(players.get(0));
                Player player2 = Bukkit.getPlayer(players.get(1));
                if(TeamHandler.getHandler().hasTeam(player1) && TeamHandler.getHandler().getTeam(player1).isMember(player2)){
                    Bukkit.getServer().broadcastMessage(player1.getDisplayName() + " y " + player2.getDisplayName() + " han ganado la primera edicion de Cursed UHC!!!");
                    GameControler.getControler().stopGame();
                }
        }
    }
}
