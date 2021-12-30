package jw.fancy_fishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


//fancy_fishing.data   - DTO
//fancy_fishing.model  - object uses by managers and events
//fancy_fishing.utilities - just utilities and tools

public final class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        log(ChatColor.GREEN+ "FancyFishing is running 👍");
        initCommands();
        initEvents();
    }

    private void initCommands()
    {
        CommandsProvider commandsProvider = new CommandsProvider();
        var rootCmd = commandsProvider.rootCommand();
        var getFishCmd = commandsProvider.getFishCmd();
        rootCmd.setLogs(true);
        rootCmd.addSubCommand(getFishCmd);
    }

    private void initEvents()
    {
        new FishingEventHandler();
    }

    @Override
    public void onDisable() {
        log(ChatColor.RED+ "disable");
    }


    public static Main getPlugin()
    {
        return Main.getPlugin(Main.class);
    }

    public static void log(String message)
    {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
