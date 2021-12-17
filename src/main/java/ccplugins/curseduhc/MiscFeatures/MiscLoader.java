package ccplugins.curseduhc.MiscFeatures;

import ccplugins.curseduhc.MiscFeatures.CustomCrafts.CraftRegisterer;
import ccplugins.curseduhc.MiscFeatures.CustomDrops.GhastDrop;
import ccplugins.curseduhc.MiscFeatures.CustomItems.GoldenHeadEatListener;
import org.bukkit.plugin.Plugin;

public class MiscLoader {

    private MiscLoader(){};

    public static void init(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new GhastDrop(),plugin);
        CraftRegisterer.registerCustomCrafts(plugin);
        plugin.getServer().getPluginManager().registerEvents(new GoldenHeadEatListener(),plugin);
    }
}