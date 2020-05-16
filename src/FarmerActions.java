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
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHealthiness(3);
    	}
    	remainingActions -= 1;
    }

    public static void feedAnimalsTreats(){
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHappiness(3);
    	}
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHealthiness(3);
    	}
    	remainingActions -= 1;
    }

    public static void playWithAnimals(){
    	
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHappiness(2);
    	}
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