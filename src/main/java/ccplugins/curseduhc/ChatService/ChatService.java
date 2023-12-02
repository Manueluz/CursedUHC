package ccplugins.curseduhc.ChatService;

import ccplugins.curseduhc.CursedUHCConfig;
import ccplugins.curseduhc.Helpers.PluginHelper;
import ccplugins.curseduhc.Service.Service;
import ccplugins.curseduhc.TeamService.Team;
import ccplugins.curseduhc.TeamService.TeamService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ChatService implements Service {

    private TeamService teams;

    private final Map<UUID,ChatMode> playerChatModes = new HashMap<>();

    public void init(CursedUHCConfig config){
        teams = config.getByClass(TeamService.class);
        
        PluginHelper.registerListener(new ChatListener(this));
        PluginHelper.registerCommand("chat", new ChatCommandListener(this));
    }
    
    public void stop(){
       
    }
    
    public Optional<Team> getTeam(UUID playerId){
        return teams.getTeam(playerId);
    }
    public String getTeamName(UUID playerId){
        return getTeam(playerId)
            .map(Team::getName)
            .orElse("Alone");
    }
    
    public void setPlayerChatMode(UUID id, ChatMode mode){
        playerChatModes.put(id, mode);
    }
    
    public ChatMode getPlayerChatMode(UUID id){
        return playerChatModes.getOrDefault(id, ChatMode.GLOBAL_CHAT);
    }
}
