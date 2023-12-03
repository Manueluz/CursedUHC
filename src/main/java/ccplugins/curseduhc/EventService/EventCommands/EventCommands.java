package ccplugins.curseduhc.EventService.EventCommands;

import ccplugins.curseduhc.EventService.EventService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public class EventCommands implements CommandExecutor {

    private final EventService service;

    public EventCommands(EventService service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if(!sender.hasPermission("UHC.admin")) {
            sender.sendMessage(ChatColor.RED+" you don't have permission to do this");
            return true;
        }

        if(args.length < 1) return false;

        return switch (args[0]) {
            case "list" -> list(sender);
            case "skip" -> skip(sender);
            case "start" -> start(sender, args);
            case "setTimer" -> setTimer(sender, args);
            default -> false;
        };
    }

    private boolean list(CommandSender sender) {
        service.getEvents()
                .forEach(e -> sender.sendMessage(
                        ChatColor.DARK_GREEN
                                + "["
                                +ChatColor.GREEN
                                +"Event"
                                +ChatColor.DARK_GREEN
                                +"]"
                                +ChatColor.GREEN
                                + ": "
                                + e.getName()
                ));
        return true;
    }

    private boolean skip(CommandSender sender) {
        service.skip();
        sender.sendMessage(ChatColor.GREEN+"Skipped next event");
        return true;
    }
    private boolean start(CommandSender sender, String[] args) {
        if(args.length != 2) return false;

        long seconds = service.getInternalTimer().getRemainingSeconds();

        service.nextEvent();

        if(args[1].equalsIgnoreCase("false"))
            service.getInternalTimer().setRemainingSeconds(seconds);

        sender.sendMessage(ChatColor.GREEN+"Started next event");

        return true;
    }
    private boolean setTimer(CommandSender sender, String[] args) {
        if(args.length != 2) return false;

        long newSeconds;

        try{
            newSeconds = Long.parseLong(args[1]);
        }catch (NumberFormatException e){
            return false;
        }

        service.getInternalTimer().setRemainingSeconds(newSeconds);

        sender.sendMessage(ChatColor.GREEN+"Changed next event timer");

        return true;
    }
}
