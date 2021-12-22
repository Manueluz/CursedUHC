package ccplugins.curseduhc.UHCGame.UI;

import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

public class ScoreboardUI {

    private static ScoreboardUI scoreboardUI;
    private final Plugin plugin;


    private ScoreboardUI(Plugin plugin){
        this.plugin = plugin;
    }

    public static void init(Plugin plugin){
        if(scoreboardUI != null){return;}
        scoreboardUI = new ScoreboardUI(plugin);
    }

    public static ScoreboardUI getScoreboardUI(){return scoreboardUI;}

    public void update(){

        for(Player player : plugin.getServer().getOnlinePlayers()){
            Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();

            Objective name = CreateObjective(scoreboard);

            String nextEventS = "NONE";
            String remainingTimeS = "00:00";
            if(EventHandler.getHandler().nextEvent() != null) {
                nextEventS = EventHandler.getHandler().nextEvent().getName();
                remainingTimeS = EventHandler.getHandler().getEventCountdown().toString().substring(3, 8);
            }

            name.getScore(ChatColor.GRAY+"    "+remainingTimeS).setScore(2);
            if(TeamHandler.getHandler().hasTeam(player)){
                for(UUID id : TeamHandler.getHandler().getTeam(player).getMembers()){
                    if(id == player.getUniqueId()){continue;}
                    name.getScore(ChatColor.GRAY+"    "+plugin.getServer().getOfflinePlayer(id).getName()).setScore(5);
                }
            }else{
                name.getScore(ChatColor.GRAY+""+ChatColor.MAGIC+"    AAAAAAAAAA").setScore(5);
            }
            name.getScore(ChatColor.GRAY+"    "+nextEventS).setScore(8);

            player.setScoreboard(scoreboard);
        }
    }

    private Objective CreateObjective(Scoreboard scoreboard) {
        Objective name = scoreboard.registerNewObjective("UI","", DisplaySlot.SIDEBAR.name());
        name.setDisplaySlot(DisplaySlot.SIDEBAR);
        name.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+">--"+ChatColor.YELLOW+""+ChatColor.BOLD+"Cursed UHC"+ChatColor.GOLD+""+ChatColor.BOLD+"--<");
        name.getScore("    ").setScore(10);
        name.getScore(ChatColor.RED + "    Proximo evento:").setScore(9);
        name.getScore("   ").setScore(7);
        name.getScore(ChatColor.AQUA+"    Tu compañero:").setScore(6);
        name.getScore("  ").setScore(4);
        name.getScore(ChatColor.LIGHT_PURPLE+"    Evento en:").setScore(3);
        name.getScore(" ").setScore(1);
        name.getScore(ChatColor.GOLD+""+ChatColor.BOLD+"  </"+ChatColor.YELLOW+""+ChatColor.BOLD+"By Manueluz"+ChatColor.GOLD+""+ChatColor.BOLD+">").setScore(0);
        return name;
    }
}
