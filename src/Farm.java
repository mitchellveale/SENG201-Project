public class Farm {
    private static int currentDay = 1;

    /**
     * the farmer's total money
     */
    public static int money = 1000;

    public static final CropField[] cropFields = new CropField[6];
    public  static AnimalPen cowPen;
    public  static AnimalPen chickenPen;
    public  static AnimalPen pigPen;

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
        cowPen = new AnimalPen(Animal.COW);
        chickenPen = new AnimalPen(Animal.Chicken);
        pigPen = new AnimalPen(Animal.PIG);
        for(int i=0;i<=cropFields.length-1;i++) {
        	cropFields[i] = new CropField();
        }
    }

    /**
     * Advances game to the next day by performing all end of day calculations
     */
    public static void nextDay(){
        currentDay++;
        if (currentDay > gameLength) {
        	System.out.println("Score: " + getScore());
        	System.exit(0);
        }
        for(CropField field : cropFields){
            field.grow();
        }
        money += farmType.getExtraCash();
        money += (cowPen.getAnimal().getdailyIncome() * cowPen.getAnimal().CurrentCount);
        money += (chickenPen.getAnimal().getdailyIncome() * chickenPen.getAnimal().CurrentCount);
        money += (pigPen.getAnimal().getdailyIncome() * pigPen.getAnimal().CurrentCount);
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
    
    public static int getScore() {
    	return (Farm.money);	// for now 
    }

    public static String getFarmerName() {
        return farmerName;
    }
}
