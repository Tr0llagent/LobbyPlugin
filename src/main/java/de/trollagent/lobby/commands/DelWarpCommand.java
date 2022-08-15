package de.trollagent.lobby.commands;

import de.trollagent.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class DelWarpCommand implements CommandExecutor, TabCompleter {


    private Lobby lobby;

    public DelWarpCommand(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + ChatColor.RED + "This can only be executed by an Player!"));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(lobby.getConfig().getString("del-warp-perm"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + lobby.getConfig().getString("no-perms")));
            return true;
        }

        if (args.length != 1)
            return false;

        if (!lobby.getLocationManager().existLocation(args[0])) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + ChatColor.DARK_RED + "The location " + args[0] + " does not exist!"));
            return true;
        }

        lobby.getLocationManager().removeLocation(args[0]);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                lobby.getConfig().getString("prefix")
                        + ChatColor.GREEN + "The location " + args[0] + " was successfully deleted!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return lobby.getLocationManager().getLocations();
    }

}
