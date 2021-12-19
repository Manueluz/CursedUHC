package ccplugins.curseduhc.UHCTeams.TeamCommands;

import ccplugins.curseduhc.UHCTeams.TeamHandler;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class TeamCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TeamHandler handler = TeamHandler.getHandler();
        switch (args.length){
            case 2:
                if(args[0].equals("setName")) {
                    if(sender instanceof Player && handler.hasTeam((Player) sender)){
                        if(handler.getTeam(((Player) sender)).getName().equals("Alone")) {
                            if (args[1].length() < 5) {
                                handler.getTeam((Player) sender).setName(args[1].replace("%", "%%"));
                                sender.sendMessage(ChatColor.of(new Color(50, 255, 100)) + "Changed the name of your team to:" + args[1]);
                            } else {
                                sender.sendMessage(ChatColor.of(new Color(255, 50, 100)) + "Your name is too long, max allowed names length is 4 Characters");
                            }
                        }else {
                            sender.sendMessage(ChatColor.of(new Color(255,50,100)) + "Your team already has a name!!");
                        }
                    }else {
                        sender.sendMessage(ChatColor.of(new Color(255,50,100)) + "You dont have a team!");
                    }
                    return true;
                }
            default:
                return false;
        }
    }
}
