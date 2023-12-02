package ccplugins.curseduhc.ChatService;

import ccplugins.curseduhc.ChatService.ChatModes.ChatMode;
import ccplugins.curseduhc.Helpers.PlayerCommandExecutor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;

import java.awt.*;

public class ChatCommandListener extends PlayerCommandExecutor {

    private final ChatService service;

    public ChatCommandListener(ChatService service){
        this.service = service;
    }

    @Override
    public boolean doCommand(Player player, Command command, String label, String[] args) {
        if(args.length != 1 || args[0].length() != 1) return false;
        if(service.isDead(player.getUniqueId())){
            player.sendMessage("You are dead!");
            return true;
        }

        switch (args[0]){
            case "G":
                service.setPlayerChatMode(player.getUniqueId(), ChatMode.GLOBAL_CHAT);
                player.sendMessage(ChatColor.of(new Color(100,205,150))+"Ahora estas hablando por el chat global!");
                return true;
            case "T":
                service.setPlayerChatMode(player.getUniqueId(), ChatMode.TEAM_CHAT);
                player.sendMessage(ChatColor.of(new Color(100,205,150))+"Ahora estas hablando por el chat de equipo!");
                return true;
            default:
                return false;
        }
    }
}
