public class Farm {
    private static int gameLength;
    private static int currentDay;

    private static int bank;

    /**
     * the name of the farm
     */
    private static String farmName;
    /**
     * the name of the farmer
     */
    //If we add farmer attributes later we should change this to a 'Farmer' class
    private static String farmerName;
    /**
     * the type of farm
     */
    private static FarmType farmType;

    /**
     * Instantiates the Farm class
     * @param farmName the name of the farm
     * @param farmerName the name of the farmer
     * @param farmType  the farm type.
     */
    public static void createFarm(int gameLength, String farmName, String farmerName, FarmType farmType){
        Farm.gameLength = gameLength;
        Farm.farmName = farmName;
        Farm.farmerName = farmerName;
        Farm.farmType = farmType;

        currentDay = 1;
        bank = 1000;
    }

    public static void nextDay(){
        currentDay++;
        if (currentDay > gameLength) {
            // FIXME: Add functionality for the end of the game
        }
        bank += farmType.getExtraCash();
    }
}
