package ccplugins.curseduhc.ChatService.ChatModes;

import ccplugins.curseduhc.ChatService.ChatService;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface ChatMode {
    ChatMode TEAM_CHAT = new TeamMode();
    ChatMode GLOBAL_CHAT = new GlobalMode();
    ChatMode SPECTATOR_CHAT = new SpectatorMode();

    void handleChat(AsyncPlayerChatEvent event, ChatService service);
}