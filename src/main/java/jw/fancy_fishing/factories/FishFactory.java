package jw.fancy_fishing.factories;

import jw.external.jw_modules.builders.ItemStackBuilder;
import jw.fancy_fishing.data.FishData;
import jw.fancy_fishing.enums.fish.FishSpice;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FishFactory
{
    public static ItemStack getFish(FishSpice fishSpecie)
    {
        switch (fishSpecie)
        {
            case CARP -> getCarp();
        }
        return null;
    }

    public static ItemStack getCarp()
    {
        FishData data = new FishData();
        data.setDescription("aaaa");
        data.setMass(12);
        data.setName("carp");

       return ItemStackBuilder.create()
                .withName(data.getName())
                .withMaterial(Material.TROPICAL_FISH)
                .withDescription(data.getLore())
                .withIncludedObject(data)
                .withAmount(1)
                .build();
    }

}
