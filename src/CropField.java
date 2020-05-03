public class CropField {
    private Crop plantedCrop = null;

    private double growthMultiplier = 0;
    private double yieldMultiplier = 0;

    private int growth = 0;
    private double amount = 0;

    private boolean fertilized = false;

    public void PlantCrop(Crop crop){
        plantedCrop = crop;
        resetMultipliers();
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
        // ugh, its too late at night to think of all the math that this method requires
    }

    public void harvest(){
        Farm.money += plantedCrop.getSellPrice() * amount;

        if (plantedCrop.getHealthBoost() > 0){
            // TODO: This requires animal to be finalised
        }

        fertilized = plantedCrop.doesFertilize();
        plantedCrop = null;

    }

}
