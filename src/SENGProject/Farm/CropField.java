package SENGProject.Farm;

/**
 * A class which represents a crop field, which holds a crop
 * @author Ryan Bellamy and Mitchell Veale
 *
 */
public class CropField {
    private Crop plantedCrop = null;

    private double growthMultiplier = 0;
    private double yieldMultiplier = 0;

    private int growth = 0;
    private double amount = 0;

    private boolean fertilized = false;

    /**
     *  Plants a crop in the CropField, assuming there is enough seed 
     * @param crop The crop object to be planted in the CropField
     */
    public void PlantCrop(Crop crop){
        amount = 0;
        growth = 0;
        crop.plant();
        plantedCrop = crop;
        resetMultipliers();
        if (fertilized)
            addYieldMultiplier(1.5);
    }

    /**
     * resets growth and yield multipliers
     */
    private void resetMultipliers(){
        growthMultiplier = 1;
        yieldMultiplier = 1 * Farm.getFarmType().getCropYieldMultiplier();
    }
    
    /**
     * Adds x amount to the cropFields yield multiplier
     * @param amount A double of the amount the yield is multiplied by 
     */
    public void addYieldMultiplier(double amount){
        yieldMultiplier += (amount - 1);
    }
    
    /**
     * Adds x amount to the cropFields growth multiplier
     * @param amount A double of the amount the growth is multiplied by 
     */
    public void addGrowthMultiplier(double amount){
        growthMultiplier += (amount - 1);
    }

    /**
     * Grows the crop in the field if the field isn't null or mature
     */
    public void grow(){
        if (isMature() || plantedCrop == null)
            return;
        growth++;
        amount += ((plantedCrop.getBaseYield() * yieldMultiplier) / actualGrowTime()) * (0.5 + (Farm.getFarmCondition() / 2));
    }

    /**
     * if the crop is mature it is sold, bank is increased, cropField is set to null. 
     */
    public void harvest(){
        if(!isMature())
            return;
        Farm.addMoney((int)(plantedCrop.getSellPrice() * amount));

        if (plantedCrop.getHealthBoost() > 0){
        	for(AnimalPen pen : Farm.getAnimalPens()) {
            	pen.getAnimal().increaseHealthiness(plantedCrop.getHealthBoost());
            }
        }
        fertilized = plantedCrop.doesFertilize();
        plantedCrop = null;
    }

    /**
     * 
     * @return Money gained from harvest
     */
    public int harvestValue(){
        if (plantedCrop == null || !isMature())
            return 0;
        return (int)(plantedCrop.getSellPrice() * amount);
    }
    /**
     * 
     * @return true if growth >= grow time, otherwise false
     */
    public boolean isMature(){
        if (plantedCrop == null)
            return false;
        return (growth >= actualGrowTime());
    }

    /**
     * 
     * @return the current percentage of growth undergone
     */
    public double getGrowthPercent(){
        return (double) growth / actualGrowTime();
    }

    /**
     * 
     * @return an int of the the time to maturity adjusted by the growth multiplier
     */
    private int actualGrowTime()
    {
    	if(plantedCrop == null) {
    		return 0;
    	}
    	else {
        return (int)Math.ceil(plantedCrop.getBaseGrowTime() / growthMultiplier);}
    }

    /**
     * 
     * @return An int of the remaining time to maturity
     */
    public int getRemainingGrowTime(){
        return (actualGrowTime() - growth);
    }

    /**
     * 
     * @return True if cropField is fertilized, otherwise false
     */
    public boolean isFertilized() {
        return fertilized;
    }

    /**
     * 
     * @return Crop planted in the cropField
     */
    public Crop getPlantedCrop() {
        return plantedCrop;
    }

    /**
     * Sets fertilized to true, decrements remaining amount, all assuming the field is empty
     */
    public void fertilize(){
        fertilized = true;
        Item.FERTILIZER.use();
    }
    
    /**
     * @return The string representation of a crop field
     */
    public String toString() {
    	if(plantedCrop == null) {
    		return "Currently planted: None\nFertilized: " + fertilized+"\n";
    	}
    	else {
    		return "Currently planted: " + plantedCrop + "\n Fertilized: " + fertilized
    				+ "\n Maturity "+ getGrowthPercent()+"\n";
    	}
    }
}
