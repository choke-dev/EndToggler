package dev.choke.endtoggler.commands;

import dev.choke.endtoggler.EndToggler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EndReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        EndToggler.getMessageManager().sendMessageKey(sender, EndToggler.getMessageManager().getMessageKey("plugin.reloadingConfiguration"));
        EndToggler.getMessageManager().reloadMessages();
        EndToggler.getInstance().reloadConfig();
        EndToggler.getMessageManager().sendMessageKey(sender, EndToggler.getMessageManager().getMessageKey("plugin.configurationReloaded"));
        return true;
    }
}
