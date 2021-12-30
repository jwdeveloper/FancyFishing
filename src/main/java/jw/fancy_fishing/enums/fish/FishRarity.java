package jw.fancy_fishing.enums.fish;

public enum FishRarity {

    COMMON(1),
    UNCOMMON(0.7f),
    RARE(0.5f),
    EPIC(0.4f),
    LEGENDARY(0.3f),
    EXOTIC(0.2f);

    public final float rarity;

    private FishRarity (float rarity) {
        this.rarity = rarity;
    }

}
