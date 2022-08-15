package de.trollagent.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.groundUpdate(event.getPlayer());
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.6d).setY(1.0d));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getAllowFlight()) return;
        groundUpdate(event.getPlayer());
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        event.setCancelled(true);
    }

    public void groundUpdate(Player player) {
        Location location = player.getPlayer().getLocation();
        location = location.subtract(0, 1, 0);
        Block block = location.getBlock();
        if (!block.getType().isSolid()) return;
        player.setAllowFlight(true);
    }

}
