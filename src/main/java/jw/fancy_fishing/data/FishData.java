package jw.fancy_fishing.data;

import jw.fancy_fishing.enums.FishRarity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FishData {

    public FishRarity rarity;

    public String name;
    public String description;

    public int healthPoints;
    public int mass;
    public int quality;

    public ArrayList effects;
    public ArrayList biomes;

    private static final String TXT_THIS = "This fish is %s.";
    private static final String TXT_QUALITY = "It is of %s quality.";
    private static final String TXT_MASS = "%d oz";

    public String getLore () {
        StringBuilder lore = new StringBuilder();

        lore.append(name).append("\n");

        lore.append(description).append("\n");

        lore.append(
                String.format(TXT_THIS, rarity.adjective)
        ).append("\n");

        String quality;

        switch (this.quality){
            case 1:
                quality = "poor";
                break;
            case 2:
                quality = "fair";
                break;
            case 3:
                quality = "good";
                break;
            case 4:
                quality = "excellent";
                break;
            default:
                quality = "unknown";
                break;
        }

        lore.append(
                String.format(TXT_QUALITY, quality)
        ).append("\n");

        lore.append(
                String.format(TXT_MASS, mass)
        );

        return lore.toString();
    }

}
