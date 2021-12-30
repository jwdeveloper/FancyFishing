package jw.fancy_fishing.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Fisherman
{
    private final Player player;
    private final Date  fishStartTime;

    public Fisherman(Player player)
    {
        this.player =player;
        fishStartTime = new Date();
    }
}
