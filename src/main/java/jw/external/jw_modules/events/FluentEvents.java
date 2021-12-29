package jw.external.jw_modules.events;

import jw.external.jw_modules.events.custom_events.FluentPluginDisableEvent;
import jw.external.jw_modules.events.custom_events.FluentPluginEnableEvent;
import jw.fancy_fishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FluentEvents implements Listener {
    private static FluentEvents instance;

    private static FluentEvents getInstance() {
        if (instance == null) {
            instance = new FluentEvents();
            Bukkit.getPluginManager().registerEvents(instance, Main.getPlugin());
        }
        return instance;
    }

    @EventHandler
    public final void onPluginStart(PluginEnableEvent pluginEnableEvent) {
        if (pluginEnableEvent.getPlugin() != Main.getPlugin())
            return;
        Bukkit.getPluginManager().callEvent(new FluentPluginEnableEvent(Main.getPlugin()));
    }

    @EventHandler
    public final void onPluginStopEvent(PluginDisableEvent pluginDisableEvent) {
        if (pluginDisableEvent.getPlugin() != Main.getPlugin())
            return;
        Bukkit.getPluginManager().callEvent(new FluentPluginDisableEvent(Main.getPlugin()));
    }

    public static <T extends Event> SimpleEvent<T> onEvent(Class<T> eventType, Consumer<T> action) {
        return onEvent(eventType, action, EventPriority.NORMAL);
    }

    public static <T extends Event> SimpleEvent<T> onEvent(Class<T> eventType, Consumer<T> action, EventPriority eventPriority) {
        var fluentEvent = new SimpleEvent<T>(action);
        Bukkit.getPluginManager().registerEvent(eventType,
                getInstance(),
                eventPriority,
                (listener, event) ->
                {
                    fluentEvent.invoke((T) event);
                },
                Main.getPlugin());

        return fluentEvent;
    }

    public static <T extends Event> SimpleEvent<T> onEventAsync(Class<T> eventType, Consumer<T> action) {
        return onEventAsync(eventType, action, EventPriority.NORMAL);
    }

    public static <T extends Event> SimpleEvent<T> onEventAsync(Class<T> eventType, Consumer<T> action, EventPriority eventPriority) {
        var fluentEvent = new SimpleEvent<T>(action);
        Bukkit.getPluginManager().registerEvent(eventType,
                getInstance(),
                eventPriority,
                (listener, event) ->
                {
                    Bukkit.getScheduler().runTask(Main.getPlugin(), () ->
                    {
                        fluentEvent.invoke((T) event);
                    });
                },
                Main.getPlugin());
        return fluentEvent;
    }
}
