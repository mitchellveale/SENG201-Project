
import java.util.Scanner;  // Import the Scanner class

public class runTextGame {
	
	Scanner scan = new Scanner(System.in);  // Create a Scanner object
	int gameLength = 0;
	String farmerName = "x";
	int farmType = 0;
	String farmName;
	
	public void setupGame() {
		do {
			System.out.println("Enter valid game length (Between 5-10 days): \n");
			gameLength = scan.nextInt();  // Read user input
		}
		while(gameLength < 5 || gameLength > 10);
		
		
		do { 
			System.out.println("Enter Valid Farmer name (3-15 characters, no numbers/characters) \n");
			farmerName = scan.nextLine();
		}
		while(farmerName.length() < 3 || farmerName.length() > 15);    // TODO find a is-alpha method 
		
		
		do {
		System.out.println(" 1 : Crop-Master");            // examples for now 
		System.out.println(" 2 : animal-whisperer");
		System.out.println(" 3 : Money");
		System.out.println(" 4 : lucky \n");
		farmType = scan.nextInt(); 
		}
		while(farmType < 0 || farmType > 4); 
		
		
		do {
		System.out.println("Enter Farm Name: \n");
		farmName = scan.nextLine();
		}
		while(farmName.length()< 1);
		
		//Farm userFarm = new Farm(farmName, farmerName, farmType);
	}

	void play(){
		
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
		System.out.println("Input the Number that corresponds to the action");
		
	}
	
	
	public static void main(String[] args) {
		
		runTextGame game = new runTextGame();  
		game.setupGame();
		game.play();
		
	}
	
}
