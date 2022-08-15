package de.trollagent.lobby.commands;

import de.trollagent.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private Lobby lobby;

    public SetWarpCommand(Lobby lobby) {
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

        if (!player.hasPermission(lobby.getConfig().getString("set-warp-perm"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + lobby.getConfig().getString("no-perms")));
            return true;
        }

        if (args.length != 1)
            return false;

        if (lobby.getLocationManager().existLocation(args[0]))
            lobby.getLocationManager().removeLocation(args[0]);

        lobby.getLocationManager().saveLocation(args[0], player.getLocation());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                lobby.getConfig().getString("prefix")
                        + ChatColor.DARK_GREEN + args[0] + " was set!"));

        return true;
    }

}
