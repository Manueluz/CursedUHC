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
            if(args.length < 1)return false;
            switch (args[0]){
                case "list":
                    UHCEvent[] events = EventHandler.getHandler().getEventQueue().toArray(new UHCEvent[0]);
                    int counter = events.length;
                    for(UHCEvent event : events){
                        if(counter > 1){
                            sender.sendMessage(ChatColor.DARK_GREEN + "["+ChatColor.GREEN+"Event"+ChatColor.DARK_GREEN+"] "+ChatColor.GREEN + counter + ": " +event.getName());
                            counter--;
                        }else {
                            sender.sendMessage(ChatColor.DARK_GREEN + "Next Event: " +event.getName());
                        }
                    }
                    return true;
                case "skip":
                    EventHandler.getHandler().getEventQueue().pop();
                    sender.sendMessage(ChatColor.GREEN+"Skipped next event");
                    return true;
                case "start":
                    if(args.length != 2)return false;
                    if(args[1].equalsIgnoreCase("true")){
                        EventHandler.getHandler().update(true);
                        sender.sendMessage(ChatColor.GREEN+"Started next event and restarted the countdown");
                        return true;
                    }else if(args[1].equalsIgnoreCase("false")){
                        EventHandler.getHandler().update(false);
                        sender.sendMessage(ChatColor.GREEN+"Started next event");
                        return true;
                    }else{
                        return false;
                    }
                case "setTimer":
                    if(args.length != 2)return false;
                    try{
                        int seconds = Math.abs(Integer.parseInt(args[1]));
                        EventHandler.getHandler().getEventCountdown().setRemainingSeconds(seconds);
                        sender.sendMessage(ChatColor.GREEN+"Changed the countdown");
                        return true;
                    }catch(NumberFormatException e){return false;}
                default:
                    return false;
            }
        }else {
            sender.sendMessage(ChatColor.RED+" you dont have permission to do this");
            return true;
        }
    }
}
