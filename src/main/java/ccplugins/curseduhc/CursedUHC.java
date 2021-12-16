package ccplugins.curseduhc;

import ccplugins.curseduhc.ChatHandler.ChatListener;
import ccplugins.curseduhc.DeathHandler.DeathListener;
import ccplugins.curseduhc.UHCGame.GameControler;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class CursedUHC extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading TeamHandler");
        TeamHandler.init(this);
        getLogger().info("Loading ChatListener");
        ChatListener.init(this);
        getLogger().info("Loading DeathListener");
        DeathListener.init(this);
        getLogger().info("Loading GameControler");
        GameControler.init(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
