package jw.fancy_fishing.data;

import jw.external.jw_modules.messages.FluentMessage;
import jw.fancy_fishing.enums.fish.FishQuantity;
import jw.fancy_fishing.enums.fish.FishRarity;

import jw.fancy_fishing.utilities.FishMessages;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FishData
{
    private FishRarity rarity = FishRarity.COMMON;
    private FishQuantity quality = FishQuantity.UNKNOWN;

    private String name;
    private String description;

    private int healthPoints;
    private int mass;

    public ArrayList effects;
    public ArrayList biomes;

    public String getLore()
    {
       return FluentMessage.create()
                .text(name)
                .newLine()
                .text(description)
                .newLine()
                .format(FishMessages.FISH_THIS, rarity.name().toLowerCase())
                .newLine()
                .format(FishMessages.FISH_QUALITY, quality.name().toLowerCase())
                .newLine()
                .format(FishMessages.FISH_MASS, mass)
                .toString();
    }

}
