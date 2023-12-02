package ccplugins.curseduhc.TeamService.TeamCommands;

import ccplugins.curseduhc.Helpers.PlayerCommandExecutor;
import ccplugins.curseduhc.TeamService.Team;
import ccplugins.curseduhc.TeamService.TeamService;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Optional;

public class TeamCommands extends PlayerCommandExecutor {
    private static final int MAX_LEN = 4;
    private TeamService service;

    public TeamCommands(TeamService service) {
        this.service = service;
    }

    @Override
    public boolean doCommand(Player player, Command command, String label, String[] args) {
        if(args.length != 2) return false;
        if(!args[0].equals("setName")) return false;

        String name = args[1];
        Optional<Team> teamOptional = service.getTeam(player.getUniqueId());

        if(teamOptional.isEmpty()) {
            player.sendMessage("You must be in a team");
            return true;
        }

        Team team = teamOptional.get();

        if(name.length() > MAX_LEN) {
            player.sendMessage("Team name too large, max is " + MAX_LEN);
            return true;
        }
        if(service.existsTeam(name)) {
            player.sendMessage("Team already exists");
            return true;
        }

        if(!team.canChangeName()){
            player.sendMessage("Team name already set");
            return true;
        }

        team.setName(name);

        return true;
    }
}
