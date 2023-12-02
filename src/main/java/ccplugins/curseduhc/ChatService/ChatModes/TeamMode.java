package ccplugins.curseduhc.ChatService.ChatModes;

import ccplugins.curseduhc.ChatService.ChatService;
import ccplugins.curseduhc.TeamService.Team;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;
import java.util.Optional;

public class TeamMode implements ChatMode{
    @Override
    public void handleChat(AsyncPlayerChatEvent event, ChatService service) {
        ChatColor b = ChatColor.of(new Color(210, 16, 223));
        ChatColor n = ChatColor.of(new Color(105, 9, 239));

        Optional<Team> teamOp = service.getTeam(event.getPlayer().getUniqueId());

        teamOp.ifPresent(team -> team.sendMessage(b + "[" + n + "Team" + b + "][" + n + event.getPlayer().getDisplayName() + b + "]: " + ChatColor.of(new Color(215, 184, 200)) + event.getMessage()));

        event.setCancelled(true);
    }
}
