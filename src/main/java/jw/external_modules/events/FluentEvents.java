package jw.external_modules.events;

import jw.fancy_fishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FluentEvents implements Listener {
    private static FluentEvents instance;
    private static List<FluentEvent<PluginDisableEvent>> onPluginDisableEvents;
    private static List<FluentEvent<PluginEnableEvent>> onPluginEnableEvents;
    private static FluentEvents getInstance()
    {
        if(instance == null)
        {
            instance = new FluentEvents();
            Bukkit.getPluginManager().registerEvents(instance, Main.getPlugin());
            onPluginDisableEvents = new ArrayList<>();
            onPluginEnableEvents = new ArrayList<>();
        }
        return instance;
    }

    @EventHandler
    public final void onPluginStart(PluginEnableEvent pluginEnableEvent)
    {
        if(pluginEnableEvent.getPlugin() == Main.getPlugin())
        {
            for(var fluentEvent :onPluginEnableEvents)
            {
                fluentEvent.invoke(pluginEnableEvent);
            }
        }
    }

    @EventHandler
    public final void onPluginStopEvent(PluginDisableEvent pluginDisableEvent)
    {
        if(pluginDisableEvent.getPlugin() == Main.getPlugin())
        {
            for(var fluentEvent :onPluginDisableEvents)
            {
                fluentEvent.invoke(pluginDisableEvent);
            }
        }
    }

    public static <T extends Event> FluentEvent<T> onEvent(Class<T> eventType, Consumer<T> action)
    {
        var fluentEvent = new FluentEvent<T>(action);

        if(eventType.equals(PluginDisableEvent.class))
        {
            getInstance().onPluginDisableEvents.add((FluentEvent<PluginDisableEvent>)fluentEvent);
            return fluentEvent;
        }
        if(eventType.equals(PluginEnableEvent.class))
        {
            getInstance().onPluginEnableEvents.add((FluentEvent<PluginEnableEvent>)fluentEvent);
            return fluentEvent;
        }

        Bukkit.getPluginManager().registerEvent(eventType, getInstance(), EventPriority.NORMAL,
                (listener, event) ->
                {
                    fluentEvent.invoke((T)event);
                }, Main.getPlugin());
        return fluentEvent;
    }

    public static <T extends Event> FluentEvent<T> onEventAsync(Class<T> tClass, Consumer<T> action)
    {

        var fluentEvent = new FluentEvent<T>(action);
        Bukkit.getPluginManager().registerEvent(tClass, getInstance(), EventPriority.NORMAL,
                (listener, event) ->
                {
                    Bukkit.getScheduler().runTask(Main.getPlugin(),()->
                    {
                        fluentEvent.invoke((T)event);
                    });
                }, Main.getPlugin());
        return fluentEvent;
    }
}
