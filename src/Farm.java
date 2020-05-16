import java.util.Random;

public class Farm {
    private static int currentDay = 1;

    /**
     * the farmer's total money
     */
    public static int money = 1000;

    public static final CropField[] cropFields = new CropField[6];
    public static AnimalPen[] AnimalPens = new AnimalPen[3];
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
        AnimalPens[0] = cowPen;
        AnimalPens[1] = chickenPen;
        AnimalPens[2] = pigPen;
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
        for(int i=0; i<AnimalPens.length;i++) {
        	money += (AnimalPens[i].getAnimal().getdailyIncome() * AnimalPens[i].getAnimal().getCurrentCount());
        }
        for(int i=0; i<AnimalPens.length;i++) {
        	AnimalPens[i].getAnimal().increaseHappiness(-1);
        }
        for(int i=0; i<AnimalPens.length;i++) {
        	AnimalPens[i].getAnimal().increaseHealthiness(-1);
        }
        farmCondition -= 0.2;

        if (farmCondition < 0)
            farmCondition = 0;

        FarmerActions.resetActions();
        if(Item.BREEDING_COMPOUND.getAmount()>0) {
        	for(int i=0; i<AnimalPens.length;i++) {
        		AnimalPens[i].animalMultiplication(1.5);
        	}
        	Item.BREEDING_COMPOUND.use();
        	}
    }

    public static int useLottoTicket(){
        Random random = new Random();
        int amount =  random.nextInt(Item.LOTTO_TICKET.getPrice() * 2);
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
