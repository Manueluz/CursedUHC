package ccplugins.curseduhc.ChatService;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class ChatListener implements Listener {

    private final ChatService service;

    public ChatListener(ChatService service) {
        this.service = service;
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        service.getPlayerChatMode(event.getPlayer().getUniqueId()).handleChat(event, service);
    }
}