package dev.choke.endtoggler.commands;

import dev.choke.endtoggler.EndToggler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class EndStatus implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, String> replacements = Map.of("status", EndToggler.getInstance().getConfig().getBoolean("endPortal.enabled")
                ? EndToggler.getMessageManager().getMessageKey("endPortal.endStatusEnabled")
                : EndToggler.getMessageManager().getMessageKey("endPortal.endStatusDisabled")
        );
        EndToggler.getMessageManager().sendMessageKey(sender, EndToggler.getMessageManager().getMessageKey("endPortal.endStatus"), replacements);

        return true;
    }
}
