package dev.choke.endtoggler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigManager {
    private final JavaPlugin plugin;
    private static final String VERSION_KEY = "version"; // The key for versioning in config.yml

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isConfigUpToDate() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        // Load the existing config
        if (!configFile.exists()) {
            plugin.saveDefaultConfig(); // Save default config if it doesn't exist
        }
        FileConfiguration existingConfig = YamlConfiguration.loadConfiguration(configFile);

        // Load the default config to compare versions
        InputStream defaultConfigStream = plugin.getResource("config.yml");
        if (defaultConfigStream == null) {
            plugin.getLogger().warning("Default config.yml not found in jar!");
            return true; // Assume up-to-date if defaults can't be loaded
        }

        FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));
        int existingVersion = existingConfig.getInt(VERSION_KEY, -1);
        int defaultVersion = defaultConfig.getInt(VERSION_KEY, -1);

        return existingVersion == defaultVersion;
    }

    public void updateConfigWithDefaults() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        // Load the existing config
        if (!configFile.exists()) {
            plugin.saveDefaultConfig(); // Save default config if none exists
        }

        FileConfiguration existingConfig = YamlConfiguration.loadConfiguration(configFile);

        // Load the default config
        InputStream defaultConfigStream = plugin.getResource("config.yml");
        if (defaultConfigStream != null) {
            FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));

            // Append missing keys
            boolean updated = appendMissingKeys(existingConfig, defaultConfig);
            if (updated) {
                try {
                    existingConfig.set(VERSION_KEY, defaultConfig.getInt(VERSION_KEY, -1)); // Update version key
                    existingConfig.save(configFile); // Save the updated config
                    plugin.getLogger().info("Config updated with new keys.");
                } catch (IOException e) {
                    plugin.getLogger().warning("Failed to save updated config: " + e.getMessage());
                }
            }
        }
    }

    private boolean appendMissingKeys(FileConfiguration existing, FileConfiguration defaults) {
        boolean updated = false;

        for (String key : defaults.getKeys(true)) {
            if (!existing.contains(key)) { // Check if the key is missing
                existing.set(key, defaults.get(key)); // Copy the default value
                updated = true;
            }
        }

        return updated;
    }
}
