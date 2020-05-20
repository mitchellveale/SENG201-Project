public class FarmerActions {
    public static final int MAX_DAILY_ACTIONS = 2;

    public static int remainingActions = MAX_DAILY_ACTIONS;

    /**
     * Resets actions to the max daily actions
     */
    public static void resetActions(){
        remainingActions = MAX_DAILY_ACTIONS;
    }

    /**
     * Multiplies the amount of all animals, decrements growthCompound
     */
    public static void useBreedingCompound() {
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].animalMultiplication(1.5);
    	}
    	Item.BREEDING_COMPOUND.use();
    }
    
    
    /**
     * Harvests all mature crops
     */
    public static void harvestCrops(){
        for(CropField field : Farm.cropFields){
            if (field.getPlantedCrop() != null)
                field.harvest();
        }
        remainingActions--;
    }

    /**
     * Increases the growth multiplier, increases more if growthCompound is used, uses an action
     * @param c
     * @param growthCompound
     */
    public static void tendToCrop(CropField c, Boolean growthCompound){
        if (growthCompound) {
            c.addGrowthMultiplier(3);
            Item.GROWTH_COMPOUND.use();
        }else{
            c.addGrowthMultiplier(2);
        }
        remainingActions--;
    }
    
    /**
     * Increases healthiness of all animals, uses an action
     */
    public static void feedAnimalsHay() {
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHealthiness(3);
    	}
    	remainingActions -= 1;
    }

    /**
     * Increases happiness and healthiness of all animals, uses an action
     */
    public static void feedAnimalsTreats(){
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHappiness(3);
    	}
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHealthiness(3);
    	}
    	remainingActions -= 1;
    }

    /**
     * Increases happiness of all animals, uses an action
     */
    public static void playWithAnimals(){
    	
    	for(int i=0; i<Farm.AnimalPens.length;i++) {
    		Farm.AnimalPens[i].getAnimal().increaseHappiness(2);
    	}
    	remainingActions -= 1;
    }

    /**
     * Increases farmCondition, uses an action
     */
    public static void tendToFarm(){
        Farm.farmCondition += 0.5;
        if (Farm.farmCondition > 1)
            Farm.farmCondition = 1;
        remainingActions--;
    }

    /**
     * 
     * @return remaining actions
     */
    public static int getRemainingActions() {
        return remainingActions;
    }
}