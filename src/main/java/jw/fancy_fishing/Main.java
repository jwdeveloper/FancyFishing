package jw.fancy_fishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    // *File compilation*
    // after do changes in code use green hammer to rebuild .jar and then
    // use /reload to reload plugin on server

    //method that runs when plugin is loaded
    @Override
    public void onEnable()
    {
        log(ChatColor.GREEN+ "FancyFishing enable");
        //init events
        new TestEvents();
        new FishEvent();

        //init command
        CommandsProvider commandsProvider = new CommandsProvider();
        var rootCmd = commandsProvider.rootCommand();
        var getFishCmd = commandsProvider.getFishCmd();
        rootCmd.setLogs(true);
        rootCmd.addSubCommand(getFishCmd);

        //HOW TO JUMP INSIDE THE CLASS
        //to open class definition press CTRL and move cursor over a class for example "TestEvents" and click
        //JUMP TO CLASS DEFINITION
    }
    //method that runs when plugin is unloaded
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
