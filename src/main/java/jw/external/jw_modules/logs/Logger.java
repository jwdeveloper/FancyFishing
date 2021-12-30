package jw.external.jw_modules.logs;

import org.bukkit.Bukkit;

public class Logger
{
    public static void log(String message)
    {
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
