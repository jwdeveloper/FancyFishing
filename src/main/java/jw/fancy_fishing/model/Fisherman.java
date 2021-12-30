package jw.fancy_fishing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Fisherman
{
    private UUID playerUUID;
    private Date fishStartTime;
}
