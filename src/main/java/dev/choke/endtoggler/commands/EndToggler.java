package dev.choke.endtoggler.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class EndToggler implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            dev.choke.endtoggler.EndToggler.getMessageManager().sendMessageKey(sender, dev.choke.endtoggler.EndToggler.getMessageManager().getMessageKey("plugin.wrongUsage"));
            return true;
        }
        if (args.length == 1) {
            boolean enabled = dev.choke.endtoggler.EndToggler.getInstance().getConfig().getBoolean("endPortal.enabled");
            if (args[0].equalsIgnoreCase("disable")) {
                if (!enabled) {
                    dev.choke.endtoggler.EndToggler.getMessageManager().sendMessageKey(sender, dev.choke.endtoggler.EndToggler.getMessageManager().getMessageKey("endPortal.alreadyDisabled"));
                } else {
                    dev.choke.endtoggler.EndToggler.getInstance().getConfig().set("endPortal.enabled", false);
                    dev.choke.endtoggler.EndToggler.getMessageManager().sendMessageKey(sender, dev.choke.endtoggler.EndToggler.getMessageManager().getMessageKey("endPortal.disabled"));
                }
            } else if (args[0].equalsIgnoreCase("enable")) {
                if (enabled) {
                    dev.choke.endtoggler.EndToggler.getMessageManager().sendMessageKey(sender, dev.choke.endtoggler.EndToggler.getMessageManager().getMessageKey("endPortal.alreadyEnabled"));
                } else {
                    dev.choke.endtoggler.EndToggler.getInstance().getConfig().set("endPortal.enabled", true);
                    dev.choke.endtoggler.EndToggler.getMessageManager().sendMessageKey(sender, dev.choke.endtoggler.EndToggler.getMessageManager().getMessageKey("endPortal.enabled"));
                }
            }
            dev.choke.endtoggler.EndToggler.getInstance().saveConfig();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> tabComplete = new ArrayList<>();
        if (args.length == 1) {
            tabComplete.add("enable");
            tabComplete.add("disable");
        }
        return tabComplete;
    }
}
