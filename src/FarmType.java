public enum FarmType {
    SUBSIDISED_FARM ("Subsidised Farm", "Receive an extra $500 at the beginning of the game", 200, 1, 1, 1),
    FERTILE_LAND ("Fertile Land", "Crops have 50% larger yields", 0, 1.5d, 1, 1),
    EXPANSIVE_LAND ("Expansive Land", "Animal pens have 100% more capacity", 0, 1, 2, 1),
    ORGANIC ("Organic", "Animals generate / Crops sell for, 30% more", 0, 1, 1, 1.3);

    private final String name;
    private final String description;
    private final int extraCash;
    private final double cropYieldMultiplier;
    private final double animalPenSizeMultiplier;
    private final double valueMultiplier;

    FarmType(String name, String description, int extraCash, double cropYieldMultiplier, double animalPenSizeMultiplier, double valueMultiplier){
        this.name = name;
        this.description = description;
        this.extraCash = extraCash;
        this.cropYieldMultiplier = cropYieldMultiplier;
        this.animalPenSizeMultiplier = animalPenSizeMultiplier;
        this.valueMultiplier = valueMultiplier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExtraCash() {
        return extraCash;
    }

    public double getCropYieldMultiplier() {
        return cropYieldMultiplier;
    }

    public double getAnimalPenSizeMultiplier() {
        return animalPenSizeMultiplier;
    }

    public double getValueMultiplier() {
        return valueMultiplier;
    }
}
