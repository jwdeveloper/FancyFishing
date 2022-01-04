package jw.fancy_fishing.models;

import jw.external.jw_modules.messages.FluentMessage;
import jw.fancy_fishing.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FisherModel
{
    private final Player player;
    private final Date  fishStartTime;

    public FisherModel(Player player)
    {
        this.player =player;
        fishStartTime = new Date();
    }


    //to do CurrentTime - fishStartTime
    public long fishingTime()
    {
        return  0;
    }

    public void onFishingStarted(PlayerFishEvent event)
    {
        Main.log("Looking hok");
        var location = event.getHook().getLocation();

        Main.log("hook found"+ location.toString());
    }

    public void onFishHooked(PlayerFishEvent event)
    {

    }

    public void onFishingEnded(PlayerFishEvent event)
    {

    }
}
