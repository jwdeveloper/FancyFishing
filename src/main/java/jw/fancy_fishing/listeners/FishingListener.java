package jw.fancy_fishing.listeners;

import jw.external.jw_modules.events.EventBase;
import jw.external.jw_modules.messages.FluentMessage;
import jw.fancy_fishing.models.FisherModel;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.HashMap;
import java.util.Optional;
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
        var player = event.getPlayer();
        var state = event.getState();



        switch (state)
        {
            case FISHING ->
                    {
                        var fisher = new FisherModel(player);
                        fisher.onFishingStarted(event);
                        fishermans.putIfAbsent(player.getUniqueId(),fisher);
                    }
            case REEL_IN ->
                    {
                        var fisherOptional = getFisher(player.getUniqueId());
                        if(fisherOptional.isEmpty())
                            return;
                        fisherOptional.get().onFishingEnded(event);
                        fishermans.remove(player.getUniqueId());
                    }
            case BITE ->
                    {
                        var fisherOptional = getFisher(player.getUniqueId());
                        if(fisherOptional.isEmpty())
                            return;
                        fisherOptional.get().onFishHooked(event);
                    }
        }
    }


    private Optional<FisherModel> getFisher(UUID uuid)
    {
        if(!fishermans.containsKey(uuid))
        {
           return  Optional.empty();
        }
        return Optional.of(fishermans.get(uuid));
    }
}
