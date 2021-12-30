package jw.fancy_fishing;

import jw.external.jw_modules.events.EventBase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import java.util.HashMap;

public class FishingEventHandler extends EventBase {



    public FishingEventHandler()
    {

    }

    @Override
    public void onPluginStart(PluginEnableEvent event) {

    }

    @Override
    public void onPluginStop(PluginDisableEvent event) {

    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event)
    {

        switch (event.getState())
        {

        }

    }

}
