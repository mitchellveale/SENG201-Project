import java.util.Scanner;  // Import the Scanner class

// TODO extremely stripped down text game that only takes perfect user input at the moment
// the farm type doesnt have an effect on the farm yet

public class TextGame {

	static Scanner scan = new Scanner(System.in);
	//Farm userFarm = null;
	
	public static void setup() {
		
		System.out.println("Enter desired game length (5-10 days): ");
		int gameLength = scan.nextInt();  // Read user input
		System.out.println("Enter Farmer name: ");
		String farmerName = scan.next();
		System.out.println("Enter Farm name: ");
		String farmName = scan.next();
		/*
		System.out.println("1 - Subsidised Farm");
		System.out.println("2 - Fertile Farm");
		System.out.println("3 - Big Farm");
		System.out.println("4 - Organic Farm");
		*/
		FarmType[] farmTypes = FarmType.values();
		for(int i=1;i<farmTypes.length + 1;i++) {
			System.out.println(i + " - " + farmTypes[i-1].getName());
		}
		System.out.println("Please enter the number corresponding to your choice: ");
		int farmChoice = scan.nextInt();
		FarmType userFarmType = null;

		if (farmChoice < farmTypes.length){
			//input is valid
			userFarmType = farmTypes[farmChoice-1];
		}else{
			//input is not valid
		}
		/*
		switch(farmChoice) {
		
		case 1:
			userFarmType = FarmType.SUBSIDISED_FARM;
			break;
		case 2:
			userFarmType = FarmType.FERTILE_LAND;
		    break;
		case 3:
			userFarmType = FarmType.EXPANSIVE_LAND;
			break;
		case 4:
			userFarmType = FarmType.ORGANIC;
			break;
		}
		 */
		Farm.createFarm(gameLength, farmName, farmerName, userFarmType);
	    // Plays game now 	
	}
	public static void play() {
		System.out.println("Welcome to day " + Farm.getCurrentDay() + " / " + Farm.getGameLength() + "!");
		System.out.println("Bank: " + Farm.money);
		System.out.println("Actions remaining: ");
	}
	
	
	
	public static void startGame(){
		//TextGame game = new TextGame();
		setup();
		play();


	}

}
