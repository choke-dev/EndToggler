package dev.choke.endtoggler;

import dev.choke.endtoggler.commands.EndReload;
import dev.choke.endtoggler.commands.EndStatus;
import dev.choke.endtoggler.listeners.EntityPortalEventListener;
import dev.choke.endtoggler.listeners.PlayerInteractEventListener;
import dev.choke.endtoggler.listeners.PlayerPortalEventListener;

import org.bukkit.plugin.java.JavaPlugin;

public final class EndToggler extends JavaPlugin {
    private static EndToggler instance;
    private static MessageManager messageManager;

    public static EndToggler getInstance() {
        return instance;
    }

    public static MessageManager getMessageManager() {
        return messageManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        messageManager = new MessageManager();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        ConfigManager configUpdater = new ConfigManager(this);
        if (!configUpdater.isConfigUpToDate()) {
            configUpdater.updateConfigWithDefaults();
        }
        getMessageManager().loadMessages();

        getCommand("endtoggler").setExecutor(new dev.choke.endtoggler.commands.EndToggler());
        getCommand("endtoggler").setTabCompleter(new dev.choke.endtoggler.commands.EndToggler());

        getCommand("endstatus").setExecutor(new EndStatus());
        getCommand("endreload").setExecutor(new EndReload());

        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerPortalEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPortalEventListener(), this);
    }

}