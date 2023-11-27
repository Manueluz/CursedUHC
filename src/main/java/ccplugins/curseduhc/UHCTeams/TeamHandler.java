package ccplugins.curseduhc.UHCTeams;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.CursedUHCConfig;
import ccplugins.curseduhc.Handlers.Handler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.Color;
import java.util.*;

public class TeamHandler implements Handler {
    private List<Team> teams;
    private final JavaPlugin plugin = CursedUHC.plugin;


    public void init(CursedUHCConfig config){
        teams = new ArrayList<>();
        new TeamsCreator(this).runTaskTimer(plugin,10,10);
        /*
        plugin.getServer().getPluginManager().registerEvents(new AttacksListener(),plugin);
        plugin.getCommand("team").setExecutor(new TeamCommands());
        plugin.getCommand("team").setTabCompleter(new TeamCommandTabCompleter());*/
    }

    public void stop(){
        //Uneeded
    }

    public void createTeam(UUID... members){
        createTeam(Arrays.asList(members));
    }

    public void createTeam(List<UUID> members){
        plugin.getServer().broadcastMessage(ChatColor.of(new Color(35,255,150)) + "Se ha formado un nuevo equipo!");
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
        /*
        int singlePlayerCount = 0;
        for(Player p : plugin.getServer().getOnlinePlayers()){
            if(!teams.containsKey(p.getUniqueId())){singlePlayerCount++;}
        }
        return singlePlayerCount <= 1;*/
        return true;
    }

    public static TeamHandler getHandler(){
        return new TeamHandler();
    }
}
