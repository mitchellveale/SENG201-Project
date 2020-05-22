/**
 * An Enum which represents the Farms type and therefore its unique abilities
 * @author Mitchell Veale and Ryan Bellamy
 *
 */
public enum FarmType {
    SUBSIDISED_FARM ("Subsidised Farm", "Receive an extra $200 at the beginning of each day", 200, 1, 1, 1),
    FERTILE_LAND ("Fertile Land", "Crops have 50% larger yields", 0, 1.5d, 1, 1),
    EXPANSIVE_LAND ("Expansive Land", "Animal pens have 100% more capacity", 0, 1, 2, 1),
    ORGANIC ("Organic", "Animals and Crops sell for 30% more", 0, 1, 1, 1.3);

    private final String name;
    private final String description;
    private final int extraCash;
    private final double cropYieldMultiplier;
    private final double animalPenSizeMultiplier;
    private final double valueMultiplier;

    /**
     * Initializes a FarmType
     * @param name A string of the name of the FarmType
     * @param description A string of the description of the FarmType
     * @param extraCash An int of the bonus amount of money the farm receives daily 
     * @param cropYieldMultiplier A double of the amount the crop yield is multiplied by
     * @param animalPenSizeMultiplier A double of the amount the AnimalPen size is multiplied by
     * @param valueMultiplier A double of the amount that the crop sell price/animal daily income is multiplied by
     */
    FarmType(String name, String description, int extraCash, double cropYieldMultiplier, double animalPenSizeMultiplier, double valueMultiplier){
        this.name = name;
        this.description = description;
        this.extraCash = extraCash;
        this.cropYieldMultiplier = cropYieldMultiplier;
        this.animalPenSizeMultiplier = animalPenSizeMultiplier;
        this.valueMultiplier = valueMultiplier;
    }

    /**
     * 
     * @return Name of the FarmType
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return Description of the FarmType
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return extraCash value of the FarmType
     */
    public int getExtraCash() {
        return extraCash;
    }

    /**
     * 
     * @return CropYieldMultiplier of the FarmType
     */
    public double getCropYieldMultiplier() {
        return cropYieldMultiplier;
    }

    /**
     * 
     * @return AnimalPenSizeMultiplier of the FarmType
     */
    public double getAnimalPenSizeMultiplier() {
        return animalPenSizeMultiplier;
    }

    /**
     * 
     * @return ValueMultiplier of the FarmType
     */
    public double getValueMultiplier() {
        return valueMultiplier;
    }
}
