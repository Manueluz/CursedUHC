package ccplugins.curseduhc.TeamService;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.CursedUHCConfig;
import ccplugins.curseduhc.Helpers.PluginHelper;
import ccplugins.curseduhc.Service.Service;
import ccplugins.curseduhc.TeamService.TeamCommands.TeamCommandTabCompleter;
import ccplugins.curseduhc.TeamService.TeamCommands.TeamCommands;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.Color;
import java.util.*;

public class TeamService implements Service {
    private List<Team> teams;
    private final JavaPlugin plugin = CursedUHC.plugin;


    public void init(CursedUHCConfig config){
        teams = new ArrayList<>();

        new TeamsCreator(this).runTaskTimer(plugin,10,10);

        PluginHelper.registerCommand("team", new TeamCommands(this));
        PluginHelper.registerListener(new AttacksListener(this));
        PluginHelper.registerTabCompleter("team", new TeamCommandTabCompleter());
    }

    public void stop(){
        //Unneeded?
    }

    public void createTeam(UUID... members){
        createTeam(Arrays.asList(members));
    }

    public void createTeam(List<UUID> members){
        plugin.getServer().broadcastMessage(ChatColor.of(new Color(35,255,150)) + "A new team has been created!");
        plugin.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,100,1));
        teams.add(new Team(members));
    }

    public void clearTeams(){
        teams.clear();
    }

    public Optional<Team> getTeam(UUID id){
        return teams
            .stream()
            .filter(t -> t.getMembers().contains(id))
            .findFirst();
    }

    public boolean hasTeam(UUID id){
        return getTeam(id).isPresent();
    }

    public boolean teamsCompleted(){

        //TODO: Fix this
        /*
        int singlePlayerCount = 0;
        for(Player p : plugin.getServer().getOnlinePlayers()){
            if(!teams.containsKey(p.getUniqueId())){singlePlayerCount++;}
        }
        return singlePlayerCount <= 1;*/
        return true;
    }

    public static TeamService getHandler(){
        return new TeamService();
    }

    public boolean existsTeam(String name) {
        return teams
                .stream()
                .anyMatch(t -> t.getName().equals(name));
    }
}
