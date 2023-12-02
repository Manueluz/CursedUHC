package ccplugins.curseduhc.ChatService.ChatModes;

import ccplugins.curseduhc.ChatService.ChatService;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class GlobalMode implements ChatMode{
    @Override
    public void handleChat(AsyncPlayerChatEvent event, ChatService service) {
        ChatColor b = ChatColor.of(new Color(255, 218, 0));
        ChatColor n = ChatColor.of(new Color(0, 246, 88));

        String teamName = service.getTeamName(event.getPlayer().getUniqueId());

        event.setFormat(b + "[" + n + teamName + b + "][" + n + "%1$s" + b + "]: " + ChatColor.of(new Color(198, 198, 198)) + "%2$s");
    }
}
