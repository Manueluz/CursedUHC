package ccplugins.curseduhc.UHCGame.KnightsVow;

import ccplugins.curseduhc.UHCGame.Countdown.FinishTask;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;

public class KnightsVowFinishTask extends FinishTask {
    @Override
    public void run() {
        KnightsVow.disable();
        Bukkit.broadcastMessage(ChatColor.of(new Color(47, 255, 0)) + "El pacto de caballeros ha acabado, Buena suerte a todos!");
    }
}
