public class FarmerActions {
    public static final int MAX_DAILY_ACTIONS = 2;

    public static int remainingActions = MAX_DAILY_ACTIONS;

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
    
    public static void feedAnimalsHay() {
    	Farm.cowPen.holdingAnimal.increaseHealthiness(3);
    	Farm.chickenPen.holdingAnimal.increaseHealthiness(3);
    	Farm.pigPen.holdingAnimal.increaseHealthiness(3);
    	remainingActions -= 1;
    }

    public static void feedAnimalsTreats(){
    	Farm.cowPen.holdingAnimal.increaseHappiness(3);
    	Farm.chickenPen.holdingAnimal.increaseHappiness(3);
    	Farm.pigPen.holdingAnimal.increaseHappiness(3);
    	Farm.cowPen.holdingAnimal.increaseHealthiness(3);
    	Farm.chickenPen.holdingAnimal.increaseHealthiness(3);
    	Farm.pigPen.holdingAnimal.increaseHealthiness(3);
    	remainingActions -= 1;
    }

    public static void playWithAnimals(){
    	Farm.cowPen.holdingAnimal.happiness += 2;
    	Farm.chickenPen.holdingAnimal.happiness += 2;
    	Farm.pigPen.holdingAnimal.happiness += 2;
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