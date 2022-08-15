package de.trollagent.lobby.events;

import de.trollagent.lobby.Lobby;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ProtectEvents implements Listener {

    private Lobby lobby;

    public ProtectEvents(Lobby lobby) {
        this.lobby = lobby;
    }

    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {

        Player player = event.getPlayer();

        if (player.hasMetadata("buildmode")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {

        Player player = event.getPlayer();

        if (player.hasMetadata("buildmode")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (player.hasMetadata("buildmode")) return;

        event.setCancelled(true);

    }

}
