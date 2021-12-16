package ccplugins.curseduhc.ChatHandler;


import ccplugins.curseduhc.ChatHandler.ChatCommands.ChatCommandListener;
import ccplugins.curseduhc.UHCTeams.TeamHandler;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import org.bukkit.plugin.java.JavaPlugin;


import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class ChatListener implements Listener {
    private static HashMap<UUID,Integer> playerChatModes;
    private static ChatListener listener;

    private ChatListener(){
        playerChatModes = new HashMap<>();
    };

    public static void init(JavaPlugin plugin){
        if(listener != null){return;}
        listener = new ChatListener();
        plugin.getServer().getPluginManager().registerEvents(listener,plugin);
        plugin.getCommand("chat").setExecutor(new ChatCommandListener());
    }

    public static HashMap<UUID,Integer> getPlayerChatModes(){
        return playerChatModes;
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        if(!playerChatModes.containsKey(event.getPlayer().getUniqueId())){
            playerChatModes.put(event.getPlayer().getUniqueId(),0);
        }

        switch (playerChatModes.get(event.getPlayer().getUniqueId())){
            case 0:
                ChatColor b = ChatColor.of(new Color(255, 218, 0));
                ChatColor n = ChatColor.of(new Color(0, 246, 88));
                event.setFormat(b+"["+n+"Global"+b+"]["+n+"%1$s"+b+"]: "+ ChatColor.of(new Color(198, 198, 198)) +"%2$s");
                break;
            case 1:
                if(TeamHandler.getHandler().hasTeam(event.getPlayer())) {
                    b = ChatColor.of(new Color(210, 16, 223));
                    n = ChatColor.of(new Color(105, 9, 239));
                    TeamHandler.getHandler().getTeam(event.getPlayer()).sendMessage(b+"["+n+"Team"+b+"]["+n+event.getPlayer().getDisplayName()+b+"]: "+ ChatColor.of(new Color(215,184,200)) +event.getMessage());
                }
                event.setCancelled(true);
                break;
            default:
                event.setFormat("[FAIL][%1$s]: [ERROR]");
        }
    }
}
