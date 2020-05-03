public class Farm {
    private static int currentDay = 1;

    /**
     * the farmer's total money
     */
    public static int money = 1000;

    private static CropField[] cropFields = new CropField[6];

    private static int gameLength;
    private static String farmName;
    //If we add farmer attributes later we should change this to a 'Farmer' class
    private static String farmerName;
    private static FarmType farmType;

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
    }

    /**
     * Advances game to the next day by performing all end of day calculations
     */
    public static void nextDay(){
        currentDay++;
        if (currentDay > gameLength) {
            // TODO: Add functionality for the end of the game
        }
        money += farmType.getExtraCash();
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
    public boolean isFinalDay(){
        return (currentDay == gameLength);
    }
}
