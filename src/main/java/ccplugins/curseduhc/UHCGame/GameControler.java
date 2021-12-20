package ccplugins.curseduhc.UHCGame;


import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCGame.GameCommands.GameCommands;
import ccplugins.curseduhc.UHCGame.PlayerSpreader.PlayerSpreader;
import ccplugins.curseduhc.UHCGame.UI.UIupdater;
import ccplugins.curseduhc.UHCGame.WinDetection.WinDetector;
import ccplugins.curseduhc.UHCGame.WorldBorderTasks.WorldBorderReduceTask;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.UUID;

public class GameControler {

    private static GameControler controler;
    private Countdown finalCountDown;
    private Countdown firstWBCountdown;
    private Countdown secondWBCountdown;
    private BukkitTask winDetector;
    private ArrayList<UUID> currentPlayers;
    private boolean GameState;
    private final Plugin plugin;

    private GameControler(Plugin plugin){
        currentPlayers = new ArrayList<>();
        this.plugin = plugin;
        GameState = false;
    }

    public static void init(JavaPlugin plugin){
        if(controler != null){return;}
        controler = new GameControler(plugin);
        plugin.getCommand("startGame").setExecutor(new GameCommands());
    }

    public static GameControler getControler(){return controler;}
    public ArrayList<UUID> getGamePlayers(){return  new ArrayList<>(currentPlayers);}
    public Countdown getFinalCountDown(){return finalCountDown;}
    public boolean isGameStarted(){return GameState;}
    public ArrayList<UUID> getAlivePlayers(){
        ArrayList<UUID> players = getGamePlayers();
        players.removeIf(player -> DeathListener.getDeadPlayers().contains(player));
        return players;
    }

    public void stopGame(){
        GameState = false;
        firstWBCountdown.pauseCountdown();
        secondWBCountdown.pauseCountdown();
        finalCountDown.pauseCountdown();
        winDetector.cancel();
        EventHandler.getHandler().stop();
    }
    public void startGame(){
        if(GameState){return;}

        //Register the game players
        currentPlayers.clear();
        for(Player player : plugin.getServer().getOnlinePlayers()){
            currentPlayers.add(player.getUniqueId());
        }
        //Teleport the game players
        PlayerSpreader.spreadPlayers(plugin.getServer().getWorld("world"), 2400, currentPlayers);

        //Change the game state
        GameState = true;

        //Clear the teams
        TeamHandler.getHandler().clearTeams();

        //Clean the dead players
        DeathListener.getDeadPlayers().clear();

        //Start The events
        EventHandler.getHandler().loadEvents();
        EventHandler.getHandler().start();

        //Set the world border to 2500 size
        for(World world : Bukkit.getServer().getWorlds()){
            world.setGameRule(GameRule.NATURAL_REGENERATION,false);
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO,false);
            world.getWorldBorder().setCenter(0,0);
            world.getWorldBorder().setSize(5000);
        }

        //Start The CountDown
        finalCountDown = new Countdown(10800,plugin);
        firstWBCountdown = new Countdown(7200,plugin);
        secondWBCountdown = new Countdown(9000,plugin);

        firstWBCountdown.addLastTask(new WorldBorderReduceTask(3750,900));
        secondWBCountdown.addLastTask(new WorldBorderReduceTask(200,1800));

        //Start the UI updater
        UIupdater updater = new UIupdater(plugin);
        updater.runTaskTimer(plugin,0,3);

        //Start the win detector
        winDetector = new WinDetector(plugin).runTaskTimer(plugin,20,20);
    }
}
