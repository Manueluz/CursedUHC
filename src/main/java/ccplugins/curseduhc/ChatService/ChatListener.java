package ccplugins.curseduhc.ChatService;

import ccplugins.curseduhc.TeamService.Team;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.awt.*;

import java.util.Optional;
import java.util.UUID;


public class ChatListener implements Listener {

    private final ChatService handler;

    public ChatListener(ChatService handler) {
        this.handler = handler;
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        UUID playerId = event.getPlayer().getUniqueId();

        switch (handler.getPlayerChatMode(playerId)){
            case TEAM_CHAT: handleTeamChat(event); break;
            case GLOBAL_CHAT: handleGlobalChat(event); break;
            case SPECTATOR_CHAT: handleSpectatorChat(event); break;
        }
    }

    private void handleGlobalChat(AsyncPlayerChatEvent event) {
        ChatColor b = ChatColor.of(new Color(255, 218, 0));
        ChatColor n = ChatColor.of(new Color(0, 246, 88));

        String teamName = handler.getTeamName(event.getPlayer().getUniqueId());

        event.setFormat(b + "[" + n + teamName + b + "][" + n + "%1$s" + b + "]: " + ChatColor.of(new Color(198, 198, 198)) + "%2$s");
    }

    private void handleTeamChat(AsyncPlayerChatEvent event){
        ChatColor b = ChatColor.of(new Color(210, 16, 223));
        ChatColor n = ChatColor.of(new Color(105, 9, 239));

        Optional<Team> teamOp = handler.getTeam(event.getPlayer().getUniqueId());

        teamOp.ifPresent(team -> team.sendMessage(b + "[" + n + "Team" + b + "][" + n + event.getPlayer().getDisplayName() + b + "]: " + ChatColor.of(new Color(215, 184, 200)) + event.getMessage()));

        event.setCancelled(true);
    }

    private void handleSpectatorChat(AsyncPlayerChatEvent event){
        ChatColor b = ChatColor.of(new Color(161, 153, 0));
        ChatColor n = ChatColor.of(new Color(121, 121, 121));

        event.setFormat(b+"["+n+"Spectator"+b+"]["+n+"%1$s"+b+"]: "+ ChatColor.of(new Color(90, 90, 90)) +"%2$s");
    }
}