package ccplugins.curseduhc.UHCGame.GameCommands;

import ccplugins.curseduhc.UHCGame.GameControler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("UHC.admin")){
            if(command.getName().equals("startGame")){
                GameControler.getControler().startGame();
                return true;
            }
        }
        return false;
    }
}
