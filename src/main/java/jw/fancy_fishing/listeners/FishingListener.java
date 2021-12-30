package jw.fancy_fishing.listeners;

import jw.external.jw_modules.events.EventBase;
import jw.external.jw_modules.messages.FluentMessage;
import jw.fancy_fishing.models.FisherModel;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.HashMap;
import java.util.UUID;

public class FishingListener extends EventBase {

    private final HashMap<UUID, FisherModel> fishermans;

    public FishingListener()
    {
        fishermans = new HashMap<>();
    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event)
    {
        var message = FluentMessage.create()
                .color(ChatColor.GREEN)
                .text("Fishing state")
                .space()
                .color(ChatColor.DARK_GREEN)
                .text(event.getState().name())
                .toString();

        event.getPlayer().sendMessage(message);

        //to do
        // check name of the state when player starts fishing
        // check name of the state when player ends fishing
        //check on what conditions other states are tirgered
        // if starts add player to fishermans
        // if ends remove player from fishermans

        var player = event.getPlayer();
        var state = event.getState();
        var startFishing = true;
        if(startFishing)
        {
            fishermans.put(player.getUniqueId(),new FisherModel(player));
        }
        else
        {
          fishermans.remove(player.getUniqueId());
        }
    }
}
