package ccplugins.curseduhc.DeathService;

import ccplugins.curseduhc.ChatService.ChatModes.ChatMode;
import ccplugins.curseduhc.ChatService.ChatService;
import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.CursedUHCConfig;
import ccplugins.curseduhc.DeathService.DeathCommands.DeadCommands;
import ccplugins.curseduhc.DeathService.DeathEnforcer.RespawnEnforcer;
import ccplugins.curseduhc.DeathService.DeathEnforcer.TeamEnforcer;
import ccplugins.curseduhc.DeathService.DeathEvents.Anouncer;
import ccplugins.curseduhc.DeathService.DeathEvents.DeathEventListener;
import ccplugins.curseduhc.DeathService.DeathEvents.HeadSpawner;
import ccplugins.curseduhc.Helpers.PluginHelper;
import ccplugins.curseduhc.Service.Service;
import ccplugins.curseduhc.TeamService.Team;
import ccplugins.curseduhc.TeamService.TeamService;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class DeathService implements Service {
    private ChatService chatService;
    private TeamService teamService;
    private Map<UUID, Location> deadPlayers;
    private List<DeathEventListener> deadListeners;

    @Override
    public void init(CursedUHCConfig config) {
        chatService = config.getByClass(ChatService.class);
        teamService = config.getByClass(TeamService.class);

        deadPlayers = new HashMap<>();
        deadListeners = new ArrayList<>();

        new TeamEnforcer(this).runTaskTimer(CursedUHC.plugin,10,10);

        addListener((player,ignore) -> registerDeath(player));
        addListener(new Anouncer());
        addListener(new HeadSpawner());

        PluginHelper.registerCommand("revive", new DeadCommands(this));
        PluginHelper.registerListener(new DeathListener(this));
        PluginHelper.registerListener(new RespawnEnforcer());
    }

    public boolean isDead(UUID playerId){
        return deadPlayers.containsKey(playerId);
    }

    public void addListener(DeathEventListener listener){
        deadListeners.add(listener);
    }

    public void registerDeath(Player player){
        deadPlayers.put(player.getUniqueId(),player.getLocation());
    }

    public List<UUID> getDeadPlayers() {
        return deadPlayers
                .keySet()
                .stream()
                .toList();
    }

    @Override
    public void stop() {
        //Nothing?
    }

    public void notifyEvent(Player player){
        deadListeners.forEach(listener -> listener.onPlayerDeath(player,this));
    }

    public void setChatMode(UUID uniqueId, ChatMode chatMode) {
        chatService.setPlayerChatMode(uniqueId,chatMode);
    }

    public Optional<Team> getTeam(UUID playerId) {
        return teamService.getTeam(playerId);
    }

    public void revive(Player player) {
        Location location = deadPlayers.get(player.getUniqueId());

        deadPlayers.remove(player.getUniqueId());

        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(location);

    }
}
