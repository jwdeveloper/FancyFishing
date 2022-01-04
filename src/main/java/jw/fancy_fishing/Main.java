package jw.fancy_fishing;

import jw.fancy_fishing.listeners.FishingListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

//fancy_fishing.data   - DTO
//fancy_fishing.model  - object uses by managers and events
//fancy_fishing.listeners  - bukkit event handles
//fancy_fishing.utilities - just utilities and tools
///fancy_fishing.factories - factories of itemstack Fish/Fishing rod

public final class Main extends JavaPlugin
{
    public static Main getPlugin()
    {
        return Main.getPlugin(Main.class);
    }

    public static void log(String message)
    {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    @Override
    public void onEnable()
    {
        log(ChatColor.GREEN+ "FancyFishing is running 👍");
        initCommands();
        initEvents();
    }

    @Override
    public void onDisable() {
        log(ChatColor.GREEN+ "FancyFishing disable");
    }

    private void initCommands()
    {
        CommandsProvider commandsProvider = new CommandsProvider();
        var rootCmd = commandsProvider.rootCommand();
        var getFishCmd = commandsProvider.getFishCmd();
        var getFishingRodCmd = commandsProvider.getFishingRodCmd();
        rootCmd.setLogs(true);
        rootCmd.addSubCommand(getFishCmd);
        rootCmd.addSubCommand(getFishingRodCmd);
    }

    private void initEvents()
    {
        new FishingListener();
    }
}
