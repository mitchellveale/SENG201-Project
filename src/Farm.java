import java.util.Random;
/**
 * A class which represents a Farm
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class Farm {
    private static int currentDay;

    /**
     * the farmer's total money
     */
    public static int money;

    public static final CropField[] cropFields = new CropField[6];
    public static AnimalPen[] AnimalPens = new AnimalPen[3];
    
    public  static AnimalPen cowPen;
    public  static AnimalPen chickenPen;
    public  static AnimalPen pigPen;

    private static int gameLength;
    private static String farmName;
    private static String farmerName;
    private static FarmType farmType;

    public static double farmCondition;


    /**
     * Instantiates the Farm class
     * @param gameLength An int of the length of the game in days
     * @param farmName A string of the name of the farm
     * @param farmerName A string of the name of the farmer
     * @param farmType  A FarmType object which is the farm type of the farm
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
        AnimalPens[0] = cowPen;
        AnimalPens[1] = chickenPen;
        AnimalPens[2] = pigPen;
        currentDay = 1;
        money = 1000;
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
        for(AnimalPen pen : AnimalPens) {
        	money += (pen.getAnimal().getdailyIncome() * pen.getAnimal().getCurrentCount());
        	pen.getAnimal().increaseHappiness(-1);
        	pen.getAnimal().increaseHealthiness(-1);
        }
        farmCondition = ((farmCondition * 100)-20)/100;

        if (farmCondition < 0)
            farmCondition = 0;

        FarmerActions.resetActions();
    }

    /**
     * Uses the lotto ticket item
     * @return An int of the amount won
     */
    public static int useLottoTicket(){
        Random random = new Random();
        int amount =  random.nextInt(Item.LOTTO_TICKET.getPrice() * 4);
        money += amount;
        Item.LOTTO_TICKET.use();
        return amount;
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
    
    /**
     * 
     * @return The current day
     */
    public static int getCurrentDay() {
    	return currentDay;
    }
    
    /**
     * 
     * @return The length of the game in days
     */
    public static int getGameLength() {
    	return gameLength;
    }
    
    /**
     * 
     * @return The final score of the game
     */
    public static int getScore() {
        int a = 0;
        int c =0;
        for(AnimalPen pen : AnimalPens) {
        	a += pen.getAnimal().getCurrentCount() * pen.getAnimal().getbuyPrice();
        }
        for(CropField field : cropFields) {
        	if(field.getPlantedCrop() != null) {
        	c += field.getGrowthPercent() * field.getPlantedCrop().getSellPrice();
        }}
        	return (money + a + c); 
    }

    /**
     * 
     * @return The farmers name
     */
    public static String getFarmerName() {
        return farmerName;
    }
}
