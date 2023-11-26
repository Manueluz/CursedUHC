package ccplugins.curseduhc;

import ccplugins.curseduhc.ChatHandler.ChatHandler;
import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.MiscFeatures.MiscLoader;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import ccplugins.curseduhc.UHCGame.GameControler;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

//Editor freaks out about the class being unused
@SuppressWarnings("unused")
public final class CursedUHC extends JavaPlugin {

    public static JavaPlugin plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        //Register the plugin
        registerSingleton();

        //Create the config
        CursedUHCConfig config = new CursedUHCConfig()
            .addHandler(new TeamHandler())
            .addHandler(new ChatHandler())
            .addHandler()
            .addHandler()
            .addHandler()
            .addHandler()
            .init();


        logger.info("Loading TeamHandler");
        TeamHandler.init(this);
        logger.info("Loading ChatListener");
        ChatHandler.init(this);
        logger.info("Loading DeathListener");
        DeathListener.init(this);
        logger.info("Loading GameController");
        GameControler.init(this);
        logger.info("Loading EventHandler");
        EventHandler.init(this);
        logger.info("Loading MicsFeatures");
        MiscLoader.init(this);
    }

    @Override
    public void onDisable() {
        EventHandler.getHandler().stop();
    }

    //Private methods
    private void registerSingleton(){
        plugin = this;
        logger = this.getLogger();
    }
}
