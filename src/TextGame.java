import java.util.Scanner;  // Import the Scanner class

// extremely stripped down text game that only takes perfect user input at the moment
// the farm type doesnt have an effect on the farm yet

public class TextGame {

	static Scanner scan = new Scanner(System.in);
	Farm userFarm = null;
	
	public void setup() {
		
		System.out.println("Enter desired game length (5-10 days): ");
		int gameLength = scan.nextInt();  // Read user input
		System.out.println("Enter Farmer name: ");
		String farmerName = scan.next();
		System.out.println("Enter Farm name: ");
		String farmName = scan.next();
		System.out.println("1 - Subsidised Farm");
		System.out.println("2 - Fertile Farm");
		System.out.println("3 - Big Farm");
		System.out.println("4 - Organic Farm");
		System.out.println("Please enter the number corresponding to your choice: ");
		int farmChoice = scan.nextInt();
		FarmType userFarmType = null;
		
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
		 
		userFarm = new Farm(gameLength, farmName, farmerName, userFarmType);
	    // Plays game now 	
	}
	public void play() {
		System.out.println("Welcome to day " + userFarm.getCurrentDay() + " / " + userFarm.getgameLength() + "!");
		System.out.println("Bank: " + userFarm.getBank());
		System.out.println("Actions remaining: ");
	}
	
	
	
	public static void main(String[] args) {
		TextGame game = new TextGame();
		game.setup();
		game.play();


	}

}
