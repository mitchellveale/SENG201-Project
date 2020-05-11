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
        if (isMature() || plantedCrop == null)
            return;
        growth++;
        amount += ((plantedCrop.getBaseYield() * yieldMultiplier) / actualGrowTime()) * (0.5 + (Farm.farmCondition / 2));
    }

    public void harvest(){
        if(!isMature())
            return;
        Farm.money += (int)(plantedCrop.getSellPrice() * amount);

        if (plantedCrop.getHealthBoost() > 0){
            // TODO: This requires animal to be finalised
            // this is just to keep intellij happy
            int t = 1;
        }

        fertilized = plantedCrop.doesFertilize();
        plantedCrop = null;

    }

    public int harvestValue(){
        if (!isMature() || plantedCrop == null)
            return 0;
        return (int)(plantedCrop.getSellPrice() * amount);
    }

    public boolean isMature(){
        return (growth >= actualGrowTime());
    }

    public double getGrowthPercent(){
        return (double) growth / actualGrowTime();
    }

    private int actualGrowTime()
    {
        return (int)Math.ceil(plantedCrop.getBaseGrowTime() / growthMultiplier);
    }

    public int getRemainingGrowTime(){
        return (actualGrowTime() - growth);
    }

    public boolean isFertilized() {
        return fertilized;
    }

    public Crop getPlantedCrop() {
        return plantedCrop;
    }


    public void fertilize(){
        //This assumes that a check for if the field is empty is done elsewhere
        fertilized = true;
        FarmerActions.remainingActions -= 1;
    }
    
    public String toString() {
    	if(plantedCrop == null) {
    		return "Currently planted: " + plantedCrop + "\nFertilized: " + fertilized+"\n";
    	}
    	else {
    		return "Currently planted: " + plantedCrop + "\n Fertilized: " + fertilized
    				+ "\n Maturity "+ getGrowthPercent()+"\n";
    	}
    }
}
