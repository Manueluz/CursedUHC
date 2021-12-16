package ccplugins.curseduhc.UHCGame;


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
import java.util.UUID;

public class GameControler {

    private ArrayList<UUID> currentPlayers;
    private Instant startTime;
    private boolean GameState;
    private static GameControler controler;
    private Plugin plugin;

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
            world.getWorldBorder().setSize(2500);
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
