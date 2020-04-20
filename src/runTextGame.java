
import java.util.Scanner;  // Import the Scanner class

public class runTextGame {

	
	public static void main(String[] args) {
		
		int gameLength = 0;
		String farmerName = "x";
		int farmType = 0;
		String farmName;
		
		Scanner scan = new Scanner(System.in);  // Create a Scanner object
		runTextGame game = new runTextGame();   // Create a Game
		
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
		
		//start gameplay
		
		System.out.println("Day 1 begins! \n");
		
		System.out.println("Day : ");
		System.out.println("Actions Remaining : ");
		System.out.println("Bank Balance : ");
		
		
		
	}
	
}
