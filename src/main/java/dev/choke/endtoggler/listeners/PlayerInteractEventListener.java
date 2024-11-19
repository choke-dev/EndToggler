package dev.choke.endtoggler.listeners;

import dev.choke.endtoggler.EndToggler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractEventListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.enabled")) return;
        if (EndToggler.getInstance().getConfig().getBoolean("endPortal.allowInsertEnderEye")) return;
        if (event.getPlayer().hasPermission("endtoggler.bypass.insertendereye")) return;

        if (event.getClickedBlock() == null || event.getItem() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getType() != Material.END_PORTAL_FRAME) return;
        if (!event.getClickedBlock().getBlockData().getAsString().contains("eye=false")) return;
        if (event.getItem().getType() != Material.ENDER_EYE) return;

        event.setCancelled(true);
        EndToggler.getMessageManager().sendMessageKey(event.getPlayer(), EndToggler.getMessageManager().getMessageKey("endPortal.attemptedEnderEyePlace"));
    }
}
