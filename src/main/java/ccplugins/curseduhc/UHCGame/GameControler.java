package ccplugins.curseduhc.UHCGame;


import ccplugins.curseduhc.UHCGame.Countdown.Countdown;
import ccplugins.curseduhc.UHCGame.Events.CustomEvents.*;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCGame.GameCommands.GameCommands;


import ccplugins.curseduhc.UHCGame.UI.UIupdater;
import ccplugins.curseduhc.UHCGame.WinDetection.WinDetector;
import ccplugins.curseduhc.UHCGame.WorldBorderTasks.WorldBorderReduceTask;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Random;
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
    public ArrayList<UUID> getGamePlayers(){return currentPlayers;}
    public Countdown getFinalCountDown(){return finalCountDown;}
    public boolean isGameStarted(){return GameState;}

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
        GameState = true;

        //Start The events
        ArrayList<UHCEvent> events = new ArrayList<>();
        events.add(new BoltStorm(plugin));
        events.add(new SolarStorm(plugin));
        events.add(new SuddenDeath(plugin));
        events.add(new TouchGrass(plugin));
        events.add(new WeebParty(plugin));
        events.add(new HungerPlague(plugin));
        events.add(new SolarEclipse(plugin));
        events.add(new Stonks(plugin));
        Random rand = new Random();
        while(!events.isEmpty()){
            EventHandler.getHandler().getEventQueue().clear();
            UHCEvent event = events.get(rand.nextInt(events.size()));
            EventHandler.getHandler().addEvent(event);
            plugin.getLogger().info("Added event:" + event.getName());
            events.remove(event);
        }
        EventHandler.getHandler().start();

        //Set the world border to 2500 size
        for(World world : Bukkit.getServer().getWorlds()){
            world.getWorldBorder().setCenter(0,0);
            world.getWorldBorder().setSize(5000);
        }
        //Start The CountDown
        finalCountDown = new Countdown(10800,plugin);
        firstWBCountdown = new Countdown(7200,plugin);
        secondWBCountdown = new Countdown(9000,plugin);

        firstWBCountdown.addLastTask(new WorldBorderReduceTask(3500,900));
        secondWBCountdown.addLastTask(new WorldBorderReduceTask(200,1800));

        //Start the UI updater
        UIupdater updater = new UIupdater(plugin);
        updater.runTaskTimer(plugin,0,3);

        //Register the game players
        for(Player player : plugin.getServer().getOnlinePlayers()){
            currentPlayers.clear();
            currentPlayers.add(player.getUniqueId());
        }

        //Start the win detector
        winDetector = new WinDetector().runTaskTimer(plugin,300,20);
    }
}
