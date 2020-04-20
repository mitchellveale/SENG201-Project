
import java.util.Scanner;  // Import the Scanner class


public class runTextGame {
	
	Scanner scan = new Scanner(System.in);  // Create a Scanner object
	int gameLength = 0;
	String farmerName;
	int farmType = 0;
	String farmName;
	//Farm userFarm;
	
	public void setupGame() {
		
		// Gets gameLength, farmerName, farmType, farmName and initializes userFarm
		
		do {
			System.out.println("Enter valid game length (Between 5-10 days): \n");
			gameLength = scan.nextInt();  // Read user input
		}
		while(gameLength < 5 || gameLength > 10);
		
		
		do { 
			System.out.println("Enter Valid Farmer name (3-15 characters, no numbers/characters) \n");
			farmerName = scan.nextLine();
		}
		while(farmerName.length() < 3 || farmerName.length() > 15);  // TODO find a is-alpha method & maybe change to exception
		
		
		do {
			System.out.println("*** Input the number that corresponds to the action you would like to do ***");
			System.out.println(" 1 : Crop-Master");            // examples for now 
			System.out.println(" 2 : Animal-whisperer");
			System.out.println(" 3 : Money");
			System.out.println(" 4 : Lucky \n");
			farmType = scan.nextInt(); 
			}
			while(farmType < 0 || farmType > 4); 
		
		
		do {
		System.out.println("Enter Farm Name: \n");
		farmName = scan.nextLine();
		}
		while(farmName.length()< 1);
		
		// userFarm = new Farm(farmName, farmerName, farmType);
	}

	public void playMenu(){
		
		int doCommand = 0;
		do {

		System.out.println("Day x begins! \n");
		System.out.println("Day : ");
		System.out.println("Actions Remaining : ");
		System.out.println("Bank Balance : ");
		System.out.println("1 : Visit Store");
		System.out.println("2 : Tend Crops");
		System.out.println("3 : Feed Animals");
		System.out.println("4 : Play with Animals");
		System.out.println("5 : Harvest Crops");
		System.out.println("6 : Tend to Farm Land" );
		System.out.println("7 : Next Day\n");
		System.out.println("*** Input the number that corresponds to the action you would like to do ***");
		
		doCommand= scan.nextInt();
		}
		while(doCommand < 1 || doCommand > 7);          // would like a error message for invalid input 

		switch(doCommand) {
		
		case 1: 
			// open store page
			storeMenu();
			break;
		case 2:
			// tend crops
			break;
		case 3: 
			// Feed animals
			break;
		case 4:
			//Play with animals
			break;
		case 5:
			// harvest crops
			break;
		case 6:
			// tend to farm
			break;
		case 7:
			// next day
			break;
		}
	}
	
	void storeMenu() {
		System.out.println("Welcome to the store!");
	}
	
	
	public static void main(String[] args) {
		
		runTextGame game = new runTextGame();  
		game.setupGame();
		game.playMenu();
		
	}
	
}
