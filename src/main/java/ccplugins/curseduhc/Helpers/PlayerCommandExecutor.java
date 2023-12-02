package ccplugins.curseduhc.Helpers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public abstract class PlayerCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only a player may execute this command");
            return true;
        }

        return doCommand((Player) sender, command, label, args);
    }

    protected abstract boolean doCommand(Player sender, Command command, String label, String[] args);
}
