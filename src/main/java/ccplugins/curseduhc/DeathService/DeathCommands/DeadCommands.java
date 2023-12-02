package ccplugins.curseduhc.DeathService.DeathCommands;

import ccplugins.curseduhc.DeathService.DeathListener;
import ccplugins.curseduhc.DeathService.DeathService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

public class DeadCommands implements CommandExecutor {

    private final DeathService service;

    public DeadCommands(DeathService service){
        this.service = service;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String label,@Nonnull String[] args) {
        if(!sender.hasPermission("UHC.revive") || args.length != 1) return false;

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null){
            sender.sendMessage("Player not found");
            return true;
        }

        UUID playerID = player.getUniqueId();

        if(!service.isDead(playerID)) {
            sender.sendMessage("Player is not dead");
            return true;
        }

        service.revive(player);

        return true;
    }
}
