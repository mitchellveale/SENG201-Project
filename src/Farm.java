public class Farm {
    private static int currentDay = 1;

    /**
     * the farmer's total money
     */
    public static int money = 1000;

    public static CropField[] cropFields = new CropField[6];
    public  static AnimalPen cowPen = new AnimalPen(Animal.COW);
    public  static AnimalPen chickenPen = new AnimalPen(Animal.Chicken);
    public  static AnimalPen pigPen = new AnimalPen(Animal.PIG);

    private static int gameLength;
    private static String farmName;
    //If we add farmer attributes later we should change this to a 'Farmer' class
    private static String farmerName;
    private static FarmType farmType;

    public static double farmCondition;


    /**
     * Instantiates the Farm class
     * @param gameLength the length of the game in days
     * @param farmName the name of the farm
     * @param farmerName the name of the farmer
     * @param farmType  the farm type.
     */
    public static void createFarm(int gameLength, String farmName, String farmerName, FarmType farmType){
        Farm.gameLength = gameLength;
        Farm.farmName = farmName;
        Farm.farmerName = farmerName;
        Farm.farmType = farmType;
        farmCondition = 1;
        Farm.processFarmType();
    }
    
    public static void processFarmType() {
    	money += farmType.getExtraCash();
    	// TODO need some help Farm.CropField.yieldMultiplier += farmType.getCropYieldMultiplier();
    	cowPen.capacity *= farmType.getAnimalPenSizeMultiplier();
    	chickenPen.capacity *= farmType.getAnimalPenSizeMultiplier();
    	pigPen.capacity *= farmType.getAnimalPenSizeMultiplier();
    	cowPen.holdingAnimal.dailyIncome *= farmType.getValueMultiplier();
    	chickenPen.holdingAnimal.dailyIncome *= farmType.getValueMultiplier();
    	pigPen.holdingAnimal.dailyIncome *= farmType.getValueMultiplier();
    	// TODO need help again Farm.CropField.crop.sellPrice *= farmType.getValueMultiplier();
    }

    /**
     * Advances game to the next day by performing all end of day calculations
     */
    public static void nextDay(){
        currentDay++;
        if (currentDay > gameLength) {
            // TODO: Add functionality for the end of the game
        }
        for(CropField field : cropFields){
            field.grow();
        }
        money += farmType.getExtraCash();
        farmCondition -= 0.2;
        FarmerActions.resetActions();
    }

    /**
     * @return the farm's type
     */
    public static FarmType getFarmType() {
        return farmType;
    }

    /**
     * @return true if the current day is the final day of the game else false
     */
    public static boolean isFinalDay(){
        return (currentDay == gameLength);
    }
    
    public static int getCurrentDay() {
    	return currentDay;
    }
    
    public static int getGameLength() {
    	return gameLength;
    }

}
