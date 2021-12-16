package ccplugins.curseduhc.DeathHandler.DeathCommands;

import ccplugins.curseduhc.DeathHandler.DeathListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeadCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("UHC.revive") && args != null && args.length == 1){
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null){
                DeathListener.getDeadPlayers().remove(player.getUniqueId());
                player.teleport(DeathListener.getDeadLocations().get(player.getUniqueId()));
                player.setGameMode(GameMode.SURVIVAL);
                //DeathListener.getDeadLocations().remove(player.getUniqueId());
                player.sendMessage(ChatColor.GREEN + "Has sido revivido!");
                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();
                sender.sendMessage(ChatColor.GREEN + "Se ha revivido a " + player.getName() + " en las coordenadas X:" + x + " Y:" + y +" Z:" + z);
                return true;
            }
        }
        return false;
    }
}
