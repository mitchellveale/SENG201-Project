public class CropField {
    private Crop plantedCrop = null;

    private double growthMultiplier = 0;
    private double yieldMultiplier = 0;

    private int growth = 0;
    private double amount = 0;

    private boolean fertilized = false;

    public void PlantCrop(Crop crop){
        // Assumes seed amount has been checked in calling method
        crop.plant();
        plantedCrop = crop;
        resetMultipliers();
        if (fertilized)
            addYieldMultplier(1.5);
    }

    private void resetMultipliers(){
        growthMultiplier = 1;
        yieldMultiplier = 1 * Farm.getFarmType().getCropYieldMultiplier();
    }

    public void addYieldMultplier(double amount){
        yieldMultiplier += (amount - 1);
    }

    public void addGrowthMultiplier(double amount){
        growthMultiplier += (amount - 1);
    }

    public void grow(){
        if (isMature())
            return;
        growth++;
        amount += (plantedCrop.getBaseYield() * yieldMultiplier) / actualGrowTime();
    }

    public void harvest(){
        Farm.money += plantedCrop.getSellPrice() * amount;

        if (plantedCrop.getHealthBoost() > 0){
            // TODO: This requires animal to be finalised
        }

        fertilized = plantedCrop.doesFertilize();
        plantedCrop = null;

    }

    public boolean isMature(){
        return (growth >= actualGrowTime());
    }

    public double getGrowthPercent(){
        return growth / actualGrowTime();
    }

    private int actualGrowTime()
    {
        return (int)Math.ceil(plantedCrop.getBaseGrowTime() / growthMultiplier);
    }

    public Crop getPlantedCrop() {
        return plantedCrop;
    }

    public void fertilize(){
        //This assumes that a check for if the field is empty is done elsewhere
        fertilized = true;
    }
}
