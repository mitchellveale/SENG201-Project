import java.util.Scanner;  // Import the Scanner class
import java.util.regex.Pattern;

// TODO extremely stripped down text game that only takes perfect user input at the moment
// the farm type doesn't have an effect on the farm yet

public class TextGame {

	static Scanner scan = new Scanner(System.in);
	
	public static void setup() {
		int gameLength;
		do {
			System.out.println("Enter desired game length (Must be between 5-10 days!): ");
			gameLength = scan.nextInt();  
		}
		while(gameLength > 10 || gameLength < 5);
		

		System.out.println("Enter Farmer name: ");
		String farmerName = scan.next();

		System.out.println("Enter Farm name: ");
		String farmName = scan.next();
		FarmType[] farmTypes = FarmType.values();
		for(int i=1;i<farmTypes.length + 1;i++) {
			System.out.println(i + " - " + farmTypes[i-1].getName());
		}
		System.out.println("Please enter the number corresponding to your choice: ");
		int farmChoice = scan.nextInt();
		FarmType userFarmType = null;

		if (farmChoice < farmTypes.length && farmChoice > 0){
			//input is valid
			userFarmType = farmTypes[farmChoice-1];
		}else{
			//input is not valid
		}
		Farm.createFarm(gameLength, farmName, farmerName, userFarmType);
	    // Plays game now 	
	}
	
	
	public static void play() {
		System.out.println("Welcome to day " + Farm.getCurrentDay() + " / " + Farm.getGameLength() + "!");
		System.out.println("Bank: " + Farm.money);
		System.out.println("Actions remaining: ");
		System.out.println("1 : Go to store");					// basically the main menu
		System.out.println("2 : View crop status");
		System.out.println("3 : View animal status");
		System.out.println("4 : Tend to crops");
		System.out.println("5 : Feed animals");
		System.out.println("6 : Play with Animals");
		System.out.println("7 : Harvest crops");
		System.out.println("8 : Tend to farm land");
		System.out.println("9 : Next Day");
		System.out.println("\n Please enter the number that corresponds to the action you would like to perform!");
		
		int doNext = scan.nextInt();
		
		switch(doNext) {
		
		case 1:
			//go to store
			store();
			break;
		
		case 2:
			//view crop status
			break;
		
		case 3:
			//view animal status
			break;
			
		case 4:
			//tend to crops
			break;
			
		case 5:
			//feed animals
			break;
			
		case 6:
			//play with animals 
			break;
			
		case 7:
			// harvest crops
			break;
			
		case 8:
			// tend to farm land
			break;
			
		case 9:
			// next day
			//Farm.nextDay();
			//play(); ?
			break;
			
		default:
			System.out.println("That input is invalid");
		}
	}

	
	public static void store(){
		boolean exit = false;
		while (!exit) {
			System.out.println("What would you like to buy?");
			System.out.println("1 - Seeds");
			System.out.println("2 - Special Items");
			System.out.println("3 - Exit");
			String choice = scan.next();
			switch (choice.trim()){
				case "1":
					buySeeds();
					break;
				case "2":
					buyItems();
					break;
				case "3":
					exit = true;
					break;
				default:
					System.out.println("That input is invalid");
			}

		}
	}

	public static void buySeeds(){
		Crop[] crops = Crop.values();
		boolean back = false;
		while (!back){
			System.out.println("Your Money: $" + Farm.money);
			int i;
			for (i=1; i < crops.length + 1; i++){
				System.out.println(i + " - Purchase " + crops[i-1].getName() + " seeds for $" + crops[i-1].getBuyPrice() + " (You have " + crops[i-1].getSeedAmount() + ")");
			}
			System.out.println(i + " - Back");

			String input = scan.next();
			int choice = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;
			if (choice == i)
				back = true;
			else if (choice > 0 && choice < crops.length + 1) {
				System.out.println("How many would you like?");
				input = scan.next();

				// if input is a number parse it otherwise make amount 0
				int amount = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;

				if (Farm.money >= (crops[choice - 1].getBuyPrice() * amount)) {
					if (amount > 0) {
						crops[choice - 1].buy(amount);
						System.out.println("You purchased " + amount + " " + crops[choice - 1].getName() + " seeds");
					}
				} else {
					System.out.println("You don't have enough money for that");
					System.out.println("To buy " + amount + " " + crops[choice - 1].getName() + " seeds you need $" + crops[choice - 1].getBuyPrice() * amount + " but you only have $" + Farm.money);
				}
				if (amount <= 0)
					System.out.println("That input is invalid");
			}else{
				System.out.println("That input is not valid");
			}
		}
	}

	public static void buyItems(){
		Item[] items = Item.values();
		boolean back = false;
		while (!back){
			System.out.println("Your Money: $" + Farm.money);
			int i;
			for (i=1; i < items.length + 1; i++){
				System.out.println(i + " - Purchase " + items[i-1].getName() + " for $" + items[i-1].getPrice() + " (You have " + items[i-1].getAmount() + ")");
				System.out.println("	" + items[i-1].getName() + " Description: " + items[i-1].getDescription());
			}
			System.out.println(i + " - Back");

			String input = scan.next();
			int choice = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;

			if (choice == i)
				back = true;
			else if (choice > 0 && choice < items.length + 1){
				System.out.println("How many would you like?");
				input = scan.next();

				// if input is valid parse it otherwise make amount 0
				int amount = (Pattern.matches("0*[1-9][0-9]*", input)) ? Integer.parseInt(input) : 0;

				if (Farm.money >= (items[choice-1].getPrice() * amount)){
					if (amount > 0) {
						items[choice - 1].buy(amount);
						System.out.println("You purchased " + amount + " " + items[choice-1].getName());
					}
				}else{
					System.out.println("You don't have enough money for that");
					System.out.println("To buy " + amount + " " + items[choice-1].getName() + " you need $" + items[choice-1].getPrice() * amount + " but you only have $" + Farm.money);
				}
				if (amount <= 0)
					System.out.println("That input is invalid");
			}else{
				System.out.println("That input is not valid");
			}
		}
	}
	
	
	
	public static void startGame(){
		//TextGame game = new TextGame();
		setup();
		play();


	}

}
