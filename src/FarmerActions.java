public class FarmerActions {
    public static final int MAX_DAILY_ACTIONS = 2;

    private static int remainingActions;

    public static void resetActions(){
        remainingActions = MAX_DAILY_ACTIONS;
    }

    public static void harvestCrops(){
        for(CropField field : Farm.cropFields){
            if (field.getPlantedCrop() != null)
                field.harvest();
        }
    }

    public static void tendToCrop(CropField c, Boolean growthCompound){
        if (growthCompound) {
            c.addGrowthMultiplier(3);
            Item.GROWTH_COMPOUND.use();
        }else{
            c.addGrowthMultiplier(2);
        }
    }

    public static void tendToAnimals(){
        //TODO: ryan this is for you
    }

    public static void playWithAnimals(){
        //TODO: ryan this is also for you
    }

    public static void tendToFarm(){
        Farm.farmCondition += 0.5;
        if (Farm.farmCondition > 1)
            Farm.farmCondition = 1;
    }

    public static int getRemainingActions() {
        return remainingActions;
    }
}
