package de.trollagent.lobby.commands;

import de.trollagent.lobby.Lobby;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {

    private Lobby lobby;

    public WarpCommand(Lobby lobby) {
        this.lobby = lobby;
    }

    private ArrayList<String> locations;

    private TextComponent component = new TextComponent("Warps: \n");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                    + ChatColor.DARK_RED + "This can only be executed by an Player!"));

            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(lobby.getConfig().getString("warp-perm"))) {

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                    + lobby.getConfig().getString("no-perms")));

            return true;
        }

        if (args.length != 1) {
            locations = lobby.getLocationManager().getLocations();
            if (locations != null) {
                for (String locationName : locations) {
                    if (!locationName.equals(locations.get(locations.size() - 1)))
                        locationName += ", ";

                    TextComponent component = new TextComponent(locationName);

                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            TextComponent.fromLegacyText("/warp " + locationName.replace(", ", ""))));

                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + locationName
                            .replace(", ", "")));

                    this.component.addExtra(component);
                }

                player.spigot().sendMessage(component);
                return true;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + ChatColor.DARK_RED + "There are no Warps."));
            return true;
        }

        if (lobby.getLocationManager().existLocation(args[0])) {
            player.teleport(lobby.getLocationManager().getLocation(args[0]));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                    + ChatColor.DARK_GREEN + "You where teleported to " + args[0]));

        }else player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                lobby.getConfig().getString("prefix")
                + ChatColor.DARK_RED + "The Warp " + args[0] + " does not exist!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return lobby.getLocationManager().getLocations();
    }
}
