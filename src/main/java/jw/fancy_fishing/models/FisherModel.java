package jw.fancy_fishing.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.Date;

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


    public void onFishHooked()
    {

    }

    public void onFishPull()
    {

    }


}
