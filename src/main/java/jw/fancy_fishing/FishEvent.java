package jw.fancy_fishing;

import jw.external.jw_modules.events.EventBase;
import jw.external.jw_modules.messages.FluentMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class FishEvent extends EventBase {

    @Override
    public void onPluginStart(PluginEnableEvent event) {

    }

    @Override
    public void onPluginStop(PluginDisableEvent event) {

    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
     var msg=   FluentMessage.create().text("Player")
                .space()
                .text(event.getPlayer().name())
                .space()
                .text("is fishing")
                .text(event.getState().name()).get();
     event.getPlayer().sendMessage(msg);
    }

}
