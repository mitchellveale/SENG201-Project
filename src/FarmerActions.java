public class FarmerActions {
    public static final int MAX_DAILY_ACTIONS = 2;

    private static int remainingActions = MAX_DAILY_ACTIONS;

    public static void resetActions(){
        remainingActions = MAX_DAILY_ACTIONS;
    }

    public static void harvestCrops(){
        for(CropField field : Farm.cropFields){
            if (field.getPlantedCrop() != null)
                field.harvest();
        }
        remainingActions--;
    }

    public static void tendToCrop(CropField c, Boolean growthCompound){
        if (growthCompound) {
            c.addGrowthMultiplier(3);
            Item.GROWTH_COMPOUND.use();
        }else{
            c.addGrowthMultiplier(2);
        }
        remainingActions--;
    }

    public static void tendToAnimals(){
        //TODO: ryan this is for you
    }

    public static void playWithAnimals(){
    	Farm.cowPen.holdingAnimal.happiness += 1;
    	Farm.chickenPen.holdingAnimal.happiness += 1;
    	Farm.pigPen.holdingAnimal.happiness += 1;
    	remainingActions -= 1;
    }

    public static void tendToFarm(){
        Farm.farmCondition += 0.5;
        if (Farm.farmCondition > 1)
            Farm.farmCondition = 1;
        remainingActions--;
    }

    public static int getRemainingActions() {
        return remainingActions;
    }
}