package dev.choke.endtoggler.listeners;

import dev.choke.endtoggler.EndToggler;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class EntityPortalEventListener implements Listener {

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event) {
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.enabled")) return;
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.allowEnterEnd")) return;
        if (event.getTo().getWorld().getEnvironment() != World.Environment.THE_END) return;

        event.setCancelled(true);
    }

}
