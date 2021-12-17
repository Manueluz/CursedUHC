package ccplugins.curseduhc.UHCGame;


import ccplugins.curseduhc.UHCGame.Events.CustomEvents.*;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCGame.GameCommands.GameCommands;

import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

public class GameControler {

    private ArrayList<UUID> currentPlayers;
    private Instant startTime;
    private boolean GameState;
    private static GameControler controler;
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

        Iterator<KeyedBossBar> iterator = plugin.getServer().getBossBars();
        while (iterator.hasNext()){
            KeyedBossBar bar = iterator.next();
            bar.setVisible(false);
            bar.removeAll();
            plugin.getServer().removeBossBar(bar.getKey());
        }

        for(World world : plugin.getServer().getWorlds()){
            world.getWorldBorder().setCenter(0,0);
            world.getWorldBorder().setSize(5000);
            world.getWorldBorder().setWarningTime(15);
        }
    }

    public static GameControler getControler(){
        return controler;
    }
    public Instant getStartTime(){
        return startTime;
    }
    public boolean isGameStarted(){
        return GameState;
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
            UHCEvent event = events.get(rand.nextInt(events.size()));
            EventHandler.getHandler().addEvent(event);
            plugin.getLogger().info("Added event:" + event.getName());
            events.remove(event);
        }


        EventHandler.getHandler().start();

        //Start The CountDown
        startTime = Instant.now();

        BossBar bar = plugin.getServer().createBossBar("TimeLeft:", BarColor.BLUE,BarStyle.SOLID);
        for(Player p : plugin.getServer().getOnlinePlayers()){
            bar.addPlayer(p);
        }

        Updates updater = new Updates(bar,plugin);
        updater.runTaskTimer(plugin,1,1);

        bar.setVisible(true);
    }
}
