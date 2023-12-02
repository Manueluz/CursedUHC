package ccplugins.curseduhc.ChatService.ChatModes;

import ccplugins.curseduhc.ChatService.ChatService;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class SpectatorMode implements ChatMode{
    @Override
    public void handleChat(AsyncPlayerChatEvent event, ChatService service) {
        ChatColor b = ChatColor.of(new Color(161, 153, 0));
        ChatColor n = ChatColor.of(new Color(121, 121, 121));

        //TODO: Fix this sending the message to everyone
        event.setFormat(b+"["+n+"Spectator"+b+"]["+n+"%1$s"+b+"]: "+ ChatColor.of(new Color(90, 90, 90)) +"%2$s");
    }
}
