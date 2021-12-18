package ccplugins.curseduhc.MiscFeatures;

import ccplugins.curseduhc.MiscFeatures.CustomCrafts.CraftRegisterer;
import ccplugins.curseduhc.MiscFeatures.CustomDrops.GhastDrop;
import ccplugins.curseduhc.MiscFeatures.CustomItems.GoldenHeadEatListener;
import ccplugins.curseduhc.MiscFeatures.Runnables.CompassPointer;
import ccplugins.curseduhc.MiscFeatures.Runnables.VilagerZombifier;
import org.bukkit.plugin.Plugin;

public class MiscLoader {

    private MiscLoader(){};

    public static void init(Plugin plugin){

        plugin.getServer().getPluginManager().registerEvents(new GhastDrop(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new GoldenHeadEatListener(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new VilagerZombifier(),plugin);

        new CompassPointer().runTaskTimer(plugin,0,40);

        CraftRegisterer.registerCustomCrafts(plugin);
    }
}
