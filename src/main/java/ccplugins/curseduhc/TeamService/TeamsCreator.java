package ccplugins.curseduhc.TeamService;

import ccplugins.curseduhc.CursedUHC;
import ccplugins.curseduhc.TeamService.Animations.ConnectionAnimation;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamsCreator extends BukkitRunnable {

    private final Plugin plugin = CursedUHC.plugin;
    private final TeamService handler;

    public TeamsCreator(TeamService handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        plugin.getServer()
            .getOnlinePlayers()
            .stream()
            .filter(p -> !handler.hasTeam(p.getUniqueId()))
            .forEach((player) -> {
                player.getNearbyEntities(50,50,50)
                    .stream()
                    .filter(e -> e instanceof Player)
                    .map(e -> (Player) e)
                    .filter(p -> !handler.hasTeam(p.getUniqueId()))
                    .forEach((player2) -> {
                        if(distance(player2,player) < 3)
                            handler.createTeam(player.getUniqueId(),player2.getUniqueId());
                        else
                            ConnectionAnimation.animate(player,player2,plugin);
                    });
            });
        /*
        if(!GameControler.getControler().isGameStarted()){return;}
        if(handler.teamsCompleted()){
            plugin.getServer().broadcastMessage(ChatColor.of(new Color(35,255,150)) + "Todos los jugadores tienen equipo!");
            cancel();
        }
        for(Player player : plugin.getServer().getOnlinePlayers()){

            if(handler.hasTeam(player)){continue;}
            if(!GameControler.getControler().getGamePlayers().contains(player.getUniqueId())){continue;}

            Collection<Entity> nearPlayers = player.getWorld().getNearbyEntities(player.getLocation(),150,150,150, en->en instanceof Player);
            for(Entity entity : nearPlayers){
                Player p = (Player) entity;
                if(p.getUniqueId() == player.getUniqueId() || !GameControler.getControler().getGamePlayers().contains(p.getUniqueId()) || handler.hasTeam(p) || DeathListener.getDeadPlayers().contains(p.getUniqueId())){continue;}

                if(player.getLocation().distance(p.getLocation())< 3) {
                    ArrayList<Player> teamMembers = new ArrayList<>();

                    teamMembers.add(player);
                    teamMembers.add(p);

                    handler.createTeam(teamMembers);
                }else{
                    ConnectionAnimation.animate(player,p,plugin);
                }
            }
        }
        */
    }

    private int distance(Player player1, Player player2) {
        return (int) player1.getLocation().distance(player2.getLocation());
    }
}
