package jw.fancy_fishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Hello world");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
