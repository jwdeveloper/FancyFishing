package jw.external.jw_modules.events.custom_events;

import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class FluentPluginEnableEvent extends PluginEnableEvent {
    public FluentPluginEnableEvent(@NotNull Plugin plugin) {
        super(plugin);
    }
}
