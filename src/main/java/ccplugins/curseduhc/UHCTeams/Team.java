package ccplugins.curseduhc.UHCTeams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    private final ArrayList<UUID> members;
    private String name;

    public Team(ArrayList<Player> members){
        this.members = new ArrayList<>();
        members.forEach(member -> this.members.add(member.getUniqueId()));
        name = "Alone";
    }

    public ArrayList<UUID> getMembers(){return members;}

    public String getName(){return name;}

    public void setName(String name) {this.name = name;}

    public boolean isMember(Player player){
        for(UUID id : members){
            if(id == player.getUniqueId()){
                return true;
            }
        }
        return false;
    }

    public void sendMessage(String message){
        for(UUID id : members){
            Player player = Bukkit.getPlayer(id);
            if (player != null && player.isOnline()) {
                player.sendMessage(message);
            }
        }
    }
}
