package jw.fancy_fishing.data;

import jw.fancy_fishing.enums.fishing_rod.FishingRodYield;

import java.io.Serializable;

public class FishingRodData implements Serializable
{
    public FishingRodYield fishingRodYield = FishingRodYield.UNKNOWN;
}
