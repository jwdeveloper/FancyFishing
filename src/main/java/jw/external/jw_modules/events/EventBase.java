package jw.external.jw_modules.events;

import jw.fancy_fishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public abstract class EventBase implements Listener
{
    public EventBase()
    {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    public abstract void onPluginStart(PluginEnableEvent event);

    public abstract void onPluginStop(PluginDisableEvent event);

    @EventHandler
    public final void onPluginStartEvent(PluginEnableEvent pluginEnableEvent)
    {
          if(pluginEnableEvent.getPlugin() == Main.getPlugin())
          {
              onPluginStart(pluginEnableEvent);
          }
    }
    @EventHandler
    public final void onPluginStopEvent(PluginDisableEvent pluginDisableEvent)
    {
        if(pluginDisableEvent.getPlugin() == Main.getPlugin())
        {
            onPluginStop(pluginDisableEvent);
        }
    }

}
