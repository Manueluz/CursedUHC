# CursedUHC - Minecraft 1.18
A minecraft plugin for an UHC.

The main idea of the plugin is for certain events to trigger on an interval, giving some challenges to the players

## Events
Currently the UHC has 8 events in use

|Event Name|Description|Duration|
|-|-|-|
|Thunderstorm|Lots of lightning fall from the sking around the players|2 min|
|Solar Flare|Players burn when going outside|6 min|
|Silverfish Party|Every block spawns silverfish when broken|3 min|
|Stonks|Throw 2 emeralds get 1 diamond|2 min|
|Touch Grass|Players are kicked out of the caves to the exterior|Instant|
|Weeb party|Touching water gives you wither effect|5 min|
|Hunger Strike|All players have the hunger effect|2 min|
|Solar eclipse|Sun goes down|14 min|

Events are enqueued and choosen at random every 15 minutes.

## Teams
Teams are created when two players without a team get near eachother, two particle rays will appear and bring them together.
Teams can use a team chat to talk to eachother, and can set their team name (**once**)

## World-Config
- Villages are zombie villages (villager trading was too **OP**)
- There arent coords (Players might agree on an spot before starting the game to team up) a compass will guide them to 0.0
- Ghasts only drop gold ingots
- Golden heads crafted with the heads of your enemies
- Golden Melon crafted with 1 gold block
- Notch Apple crafted with 8 gold blocks
- Players drop their heads when dying
- There inst friendly fire
- Scoreboard shows info about the game
- Dragon healthbar displays remainign time
- Joining after game start makes you an spectator

## Other events
- At the sart of the game players are thrown around in a circle
- You cant hurt other players until 30 mins after the game starts
- Game lasts 3 hours
- At 2 hours borders go from (2500 -> 1750)
- At 1 hour border starts closing down to a size of 100 blocks

---
# Wishlist
## Random world generator
A world service that will generate a unique and random world (Like minecraft [old custom worlds](https://minecraft.fandom.com/wiki/Old_Customized)) for each UHC Game, will probably need lots of tuning

Interesting documentation:
- [Old custom worlds](https://minecraft.fandom.com/wiki/Old_Customized) and [modern custom worlds](https://minecraft.fandom.com/wiki/Custom)
- [WorldCreator](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Bukkit.html#createWorld(org.bukkit.WorldCreator))

## Random Item drops
Every item drop is randomly generated.

The random drops are repeatable inside each game, for example if a log drops coal it will always drop coal in that same game.

## Add Configuration
Add a config file to select the game modes, duration, times, events...

maybe [YAML](https://bitbucket.org/snakeyaml/snakeyaml-engine/wiki/Documentation)?
