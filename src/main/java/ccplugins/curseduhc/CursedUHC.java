package ccplugins.curseduhc;

import ccplugins.curseduhc.ChatService.ChatService;
import ccplugins.curseduhc.DeathService.DeathService;
import ccplugins.curseduhc.EventService.EventService;
import ccplugins.curseduhc.MiscService.MiscService;
import ccplugins.curseduhc.TeamService.TeamService;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CursedUHC extends JavaPlugin {
    private CursedUHCConfig config;

    public static JavaPlugin plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        //Register the plugin
        registerSingleton();

        //Create the config
        config = new CursedUHCConfig()
                .addService(new TeamService())
                .addService(new ChatService())
                .addService(new DeathService())
                .addService(new EventService())
                .addService(new MiscService())
                .init();

/*
        logger.info("Loading TeamHandler");
        TeamHandler.init(this);
        logger.info("Loading ChatListener");
        ChatHandler.init(this);
        logger.info("Loading DeathListener");
        DeathListener.init(this);
        logger.info("Loading GameController");
        GameController.init(this);
        logger.info("Loading EventHandler");
        EventHandler.init(this);
        logger.info("Loading MicsFeatures");
        MiscLoader.init(this);
*/
    }

    @Override
    public void onDisable() {
        config.stop();
    }

    //Private methods
    private void registerSingleton(){
        plugin = this;
        logger = this.getLogger();
    }
}
