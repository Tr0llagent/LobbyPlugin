package de.trollagent.lobby;

import de.trollagent.lobby.commands.BuildCommand;
import de.trollagent.lobby.commands.DelWarpCommand;
import de.trollagent.lobby.commands.SetWarpCommand;
import de.trollagent.lobby.commands.WarpCommand;
import de.trollagent.lobby.events.*;
import de.trollagent.lobby.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Lobby extends JavaPlugin {

    private LocationManager locationManager;

    @Override
    public void onEnable() {

        //Logo
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "######################################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "#                                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "#                                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "#     Lobby System by Trollagent     #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "#                                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "#                                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "######################################");
        Bukkit.getConsoleSender().sendMessage("");

        //Commands
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
        getCommand("build").setExecutor(new BuildCommand(this));

        //Events
        Bukkit.getPluginManager().registerEvents(new BuildEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new ProtectEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJumpEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EnviromentEvents(), this);

        //Other
        intiConfig();
        locationManager = new LocationManager(new File("plugins/Lobby/locations.yml"), this);

    }

    public void intiConfig () {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }


}
