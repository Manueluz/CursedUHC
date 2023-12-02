package ccplugins.curseduhc.Helpers;

import ccplugins.curseduhc.CursedUHC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;

public class PluginHelper {
    private PluginHelper() {}
    
    public static void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = CursedUHC.plugin.getCommand(commandName);
        
        if(command == null) {
            CursedUHC.logger.warning("Command " + commandName + " not found, executor left unloaded");
            return;
        }
        command.setExecutor(executor);
    }

    public static void registerTabCompleter(String commandName, TabCompleter tabCompleter) {
        PluginCommand command = CursedUHC.plugin.getCommand(commandName);

        if(command == null) {
            CursedUHC.logger.warning("Command " + commandName + " not found, tabCompleter left unloaded");
            return;
        }
        command.setTabCompleter(tabCompleter);
    }
    
    public static void registerListener(Listener listener){
        CursedUHC.plugin.getServer().getPluginManager().registerEvents(listener, CursedUHC.plugin);
    }
}