package dev.choke.endtoggler.listeners;

import dev.choke.endtoggler.EndToggler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerPortalEventListener implements Listener {
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.enabled")) return;
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.allowEnterEnd")) return;
        if (event.getPlayer().hasPermission("endtoggler.bypass.enterportal")) return;
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.END_PORTAL) return;

        event.setCancelled(true);
        EndToggler.getMessageManager().sendMessageKey(event.getPlayer(), EndToggler.getMessageManager().getMessageKey("endPortal.attemptedPortalEnter"));

    }
}
