package ccplugins.curseduhc.UHCGame.Events.EventCommands;

import ccplugins.curseduhc.UHCGame.Events.CustomEvents.UHCEvent;
import ccplugins.curseduhc.UHCGame.Events.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("eventQueue") && sender.hasPermission("UHC.admin")){
            UHCEvent[] events = EventHandler.getHandler().getEventQueue().toArray(new UHCEvent[0]);
            int counter = events.length;
            for(UHCEvent event : events){
                if(counter > 1){
                    sender.sendMessage(ChatColor.DARK_GREEN + "["+ChatColor.GREEN+"Event"+ChatColor.DARK_GREEN+"] "+ChatColor.GREEN+ + counter + ": " +event.getName());
                    counter--;
                }else {
                    sender.sendMessage(ChatColor.DARK_GREEN + "Next Event -> " +event.getName() + " <-");
                }
            }
            return true;
        }
        return false;
    }
}
