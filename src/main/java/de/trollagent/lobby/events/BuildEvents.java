package de.trollagent.lobby.events;

import de.trollagent.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

public class BuildEvents implements Listener {

    private Lobby lobby;

    public BuildEvents(Lobby lobby) {
        this.lobby = lobby;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!player.hasMetadata("buildmode")) event.setCancelled(true);

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (!player.hasMetadata("buildmode")) event.setCancelled(true);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (player.hasMetadata("buildmode")) player.removeMetadata("buildmode", lobby);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!player.hasMetadata("buildmode")) event.setCancelled(true);

    }

}
