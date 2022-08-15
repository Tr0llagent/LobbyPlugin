package de.trollagent.lobby.events;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EnviromentEvents implements Listener {

    @EventHandler
    public void onHotbarSwitch(PlayerItemHeldEvent event) {
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 2);
    }

}
