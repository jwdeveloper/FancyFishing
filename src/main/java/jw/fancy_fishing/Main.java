package jw.fancy_fishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    //PRO TIPS
    // use /reload to reload plugin on server

    //HOW TO JUMP INSIDE THE CLASS
    //to open class definition press CTRL and move cursor over a class for example "TestEvents" and click
    @Override
    public void onEnable()
    {
        log(ChatColor.GREEN+ "FancyFishing enable");
        //JUMP TO CLASS DEFINITION
        new TestEvents();

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
