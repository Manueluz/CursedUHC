package ccplugins.curseduhc.MiscFeatures;

import ccplugins.curseduhc.MiscFeatures.CustomDrops.GhastDrop;
import org.bukkit.plugin.Plugin;

public class MiscLoader {

    private MiscLoader(){};

    public static void init(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new GhastDrop(),plugin);
    }
}
