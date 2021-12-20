package ccplugins.curseduhc.UHCGame.Events.EventCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EventCommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if(args.length <= 1){
            completions.add("list");
            completions.add("skip");
            completions.add("start");
            completions.add("setTimer");
        }else{
            switch (args[0]){
                case "start": completions.add("true");completions.add("false");break;
                case "setTimer": completions.add("seconds");break;
            }
        }
        return completions;
    }
}
