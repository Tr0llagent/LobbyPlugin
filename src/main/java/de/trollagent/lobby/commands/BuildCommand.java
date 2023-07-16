package de.trollagent.lobby.commands;

import de.trollagent.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class BuildCommand implements CommandExecutor {

    private Lobby lobby;

    public BuildCommand(Lobby lobby) {
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

        if (!player.hasPermission(lobby.getConfig().getString("build-mode"))){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix")
                            + lobby.getConfig().getString("no-perms")));
            return true;
        }

        if (player.hasMetadata("buildmode")) {
            player.removeMetadata("buildmode", lobby);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix") +
                    ChatColor.RED + "You are no longer in BuildMode!"));

            Bukkit.getConsoleSender().sendMessage((ChatColor.translateAlternateColorCodes('&',
                    lobby.getConfig().getString("prefix") +
                            ChatColor.RED + player.getName() + " is no longer in BuildMode!")));
            player.sendTitle(ChatColor.RED + "BUILDMODE", ChatColor.RED + "DISABLED");
            return true;
        }

        player.setMetadata("buildmode", new FixedMetadataValue(lobby, "buildmode"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                lobby.getConfig().getString("prefix") +
                        ChatColor.GREEN + "You are now in BuildMode!"));
        player.sendTitle(ChatColor.GREEN + "BUILDMODE", ChatColor.GREEN + "ENABLED");

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                lobby.getConfig().getString("prefix") +
                        ChatColor.GREEN + player.getName() + " is now in BuildMode!"));

        return true;
    }

}
