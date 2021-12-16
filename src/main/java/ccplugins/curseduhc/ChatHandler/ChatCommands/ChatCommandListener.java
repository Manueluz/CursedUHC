package ccplugins.curseduhc.ChatHandler.ChatCommands;


import ccplugins.curseduhc.ChatHandler.ChatListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class ChatCommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length > 1 || args[0].length() != 1){return false;}
        switch (args[0]){
            case "G":
                ChatListener.getPlayerChatModes().put(((Player) sender).getUniqueId(),0);
                sender.sendMessage(ChatColor.of(new Color(100,205,150))+"Ahora estas hablando por el chat global!");
                return true;
            case "T":
                ChatListener.getPlayerChatModes().put(((Player) sender).getUniqueId(),1);
                sender.sendMessage(ChatColor.of(new Color(100,205,150))+"Ahora estas hablando por el chat de equipo!");
                return true;
            default:
                return false;
        }
    }
}
