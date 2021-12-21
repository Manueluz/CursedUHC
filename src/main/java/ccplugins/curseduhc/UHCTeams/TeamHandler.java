package ccplugins.curseduhc.UHCTeams;

import ccplugins.curseduhc.UHCTeams.TeamCommands.TeamCommandTabCompleter;
import ccplugins.curseduhc.UHCTeams.TeamCommands.TeamCommands;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamHandler {

    private final HashMap<UUID,Team> teams;
    private static TeamHandler handler;
    private final Plugin plugin;

    private TeamHandler(Plugin plugin){
        this.plugin = plugin;
        teams = new HashMap<>();
    }

    public static void init(JavaPlugin plugin){
        if(handler != null){return;}
        handler = new TeamHandler(plugin);
        TeamsCreator listener = new TeamsCreator(plugin);
        listener.runTaskTimer(plugin,0,60);
        plugin.getServer().getPluginManager().registerEvents(new AttacksListener(),plugin);
        plugin.getCommand("team").setExecutor(new TeamCommands());
        plugin.getCommand("team").setTabCompleter(new TeamCommandTabCompleter());
    }

    public static TeamHandler getHandler(){
        return handler;
    }

    public void createTeam(ArrayList<Player> members){
        Team team = new Team(members);
        plugin.getServer().broadcastMessage(ChatColor.of(new Color(35,255,150)) + "Se ha formado un nuevo equipo entre " + members.get(0).getDisplayName() + " y " + members.get(1).getDisplayName());
        for(Player p : plugin.getServer().getOnlinePlayers()){
            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,100,1);
        }
        for(Player p : members){
            teams.put(p.getUniqueId(),team);
            p.sendMessage(ChatColor.of(new Color(100,205,150)) + "Ahora estas en un equipo!");
        }
    }

    public void clearTeams(){
        teams.clear();
    }

    public Team getTeam(Player member){
        return teams.get(member.getUniqueId());
    }

    public boolean hasTeam(Player player){
        return teams.containsKey(player.getUniqueId());
    }

    public boolean teamsCompleted(){
        int singlePlayerCount = 0;
        for(Player p : plugin.getServer().getOnlinePlayers()){
            if(!teams.containsKey(p.getUniqueId())){singlePlayerCount++;}
        }
        return singlePlayerCount <= 1;
    }
}
