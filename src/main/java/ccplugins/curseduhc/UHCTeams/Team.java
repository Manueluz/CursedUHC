package ccplugins.curseduhc.UHCTeams;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Team {
    private final List<UUID> members;
    private String name;

    public Team(List<UUID> members){
        this.members = members;
        this.members.stream()
            .map(Bukkit::getPlayer)
            .filter(Objects::nonNull)
            .forEach(p -> p.sendMessage(ChatColor.of(new Color(100,205,150)) + "Ahora estas en un equipo!"));
        name = "No-Name";
    }

    public List<UUID> getMembers(){
        return members;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message){
        members.stream()
            .map(Bukkit::getPlayer)
            .filter(Objects::nonNull)
            .filter(Player::isOnline)
            .forEach(p -> p.sendMessage(message));
    }
}
