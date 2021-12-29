package jw.external.jw_modules.events.custom_events;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class FluentPluginDisableEvent extends PluginDisableEvent
{
    public FluentPluginDisableEvent(@NotNull Plugin plugin)
    {
        super(plugin);
    }
}
