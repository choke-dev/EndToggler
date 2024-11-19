package dev.choke.endtoggler;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

public class MessageManager {
    private FileConfiguration messagesConfig;
    private File messagesFile;

    @Nullable
    public String getMessageKey(String key) {
        String message = getMessagesConfig().getString(key);
        return message == null ? null : ChatColor.translateAlternateColorCodes('&', message);
    }

    public void sendMessageKey(CommandSender target, String messageKey) {
        sendMessageKey(target, messageKey, null);
    }
    public void sendMessageKey(CommandSender target, String message, Map<String, String> replacements) {
        if (message == null || message.isEmpty()) return;

        if (replacements != null && !replacements.isEmpty()) {
            message = replacePlaceholders(message, replacements);
        }

        target.sendMessage(message);
    }

    private String replacePlaceholders(String message, Map<String, String> replacements) {
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            String placeholder = "%" + entry.getKey() + "%";
            message = message.replace(placeholder, entry.getValue());
        }
        return message;
    }

    public void loadMessages() {
        messagesFile = new File(EndToggler.getInstance().getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            EndToggler.getInstance().saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public void reloadMessages() {
        if (messagesFile == null) {
            messagesFile = new File(EndToggler.getInstance().getDataFolder(), "messages.yml");
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
