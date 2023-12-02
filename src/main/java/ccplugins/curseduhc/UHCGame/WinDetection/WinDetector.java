package ccplugins.curseduhc.UHCGame.WinDetection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class WinDetector extends BukkitRunnable {

    private final Plugin plugin;

    public WinDetector(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        /*
        if(!GameControler.getControler().isGameStarted()){this.cancel();}
        ArrayList<UUID> players = GameControler.getControler().getAlivePlayers();

        ChatColor r = ChatColor.of(new Color(0xFFFF1656,true));
        ChatColor R = ChatColor.of(new Color(0xFFAC0934,true));
        ChatColor g = ChatColor.of(new Color(0xFF2FFF00,true));
        ChatColor G = ChatColor.of(new Color(0xDA1FB401,true));
        ChatColor b = ChatColor.BOLD;
        String k = ChatColor.MAGIC + "A";

        String title = R + k + G + "" + b + "<" + r  + "%1" + g + " y " + r + "%2" + G + "" + b + ">" + R + k;
        String subtitle = R + "" + b + "</" + g + "Ganadores de la primera temporada de " + r + b + "UHC CC" + R + "" + b +">";

        switch (players.size()){
            case 1:
                Player winner = Bukkit.getPlayer(players.get(0));
                Bukkit.getServer().broadcastMessage(ChatColor.of(new Color(83, 255, 0, 255)) + winner.getDisplayName() + " ha ganado la primera edicion de Cursed UHC!!!");
                broadcastTitle(title.replace(" y ","").replace("%2","").replace("%1", winner.getDisplayName()),subtitle.replace("Ganadores","Ganador"),40,200,60);
                new WinAnimation(winner.getUniqueId()).runTaskTimer(plugin,0,1);
                GameControler.getControler().stopGame();
                break;
            case 2:
                Player player1 = Bukkit.getPlayer(players.get(0));
                Player player2 = Bukkit.getPlayer(players.get(1));
                if(TeamHandler.getHandler().hasTeam(player1.getUniqueId()) && TeamHandler.getHandler().getTeam(player1.getUniqueId()).get().isMember(player2)){
                    new WinAnimation(player1.getUniqueId()).runTaskTimer(plugin,0,1);
                    Bukkit.getServer().broadcastMessage(ChatColor.of(new Color(83, 255, 0, 255)) + player1.getDisplayName() + " y " + player2.getDisplayName() + " han ganado la primera edicion de Cursed UHC!!!");
                    broadcastTitle(title.replace("%1", player1.getDisplayName()).replace("%2", player2.getDisplayName()),subtitle,40,200,60);
                    GameControler.getControler().stopGame();
                }
        }*/
    }

    private void broadcastTitle(String title, String subtitle, int fadein, int stay, int fadeout){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle(title,subtitle,fadein,stay,fadeout);
        }
    }
}
