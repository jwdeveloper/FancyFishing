package jw.fancy_fishing.enums;

public enum FishRarity {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    EPIC("epic"),
    LEGENDARY("legendary"),
    EXOTIC("exotic");

    public String adjective;

    private FishRarity (String adjective) {
        this.adjective = adjective;
    }

}
