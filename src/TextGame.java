import java.util.Scanner;  
import java.util.regex.Pattern;

public class TextGame {

	static Scanner scan = new Scanner(System.in);
	
	public static void setup() {
		int gameLength = 0;
		boolean stop = false;
		while(stop == false) {
			System.out.println("Enter desired game length (Must be between 5-10 days!): ");
			String input = scan.nextLine(); 
			gameLength = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;
			if(gameLength <= 10 && gameLength >= 5) {
				stop = true;
			}
			else {
				System.out.println("Invalid Input, Try Again");
			}
		}

		
	    stop = false;
		String farmerName = null;
		while(stop == false) {
			System.out.println("Enter Farmer name (Must be 3-15 alphabetic characters only!): ");
			farmerName = scan.nextLine();
			if(Pattern.matches("([a-zA-Z]{3,15})", farmerName)) {
				stop = true;
			}
			else {
				System.out.println("Invalid Input, Try Again");
			}
		}
		
		// requirements don't actually have a restriction on farm name, could remove?
		stop = false;
		String farmName = null;
		while(stop == false) {
			System.out.println("Enter Farm name: ");
			farmName = scan.nextLine();
			if(Pattern.matches("([a-zA-Z]{3,15})", farmName)) {
				stop = true;
			}
			else {
				System.out.println("Invalid Input, Try Again");
			}
		}
		
		
		stop = false;
		FarmType userFarmType = null;
		FarmType[] farmTypes = FarmType.values();
		while(stop == false) {
			for(int i=1;i<farmTypes.length + 1;i++) {
				System.out.println(i + " - " + farmTypes[i-1].getName());
			}
			System.out.println("Please enter the number corresponding to your choice: ");
			String input = scan.nextLine();
			int farmChoice = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;
			if (farmChoice <= farmTypes.length && farmChoice > 0){
				//input is valid
				userFarmType = farmTypes[farmChoice-1];
				stop = true; }
			else {
				System.out.println("Invalid Input, Try Again");
			}
		}
		
		
		Farm.createFarm(gameLength, farmName, farmerName, userFarmType);
	    // Plays game now 	
	}
	
	
	
	public static void play() {
		System.out.println("Day " + Farm.getCurrentDay() + " / " + Farm.getGameLength() + "!");
		System.out.println("Bank: $" + Farm.money);
		System.out.println("Actions remaining: "+ FarmerActions.getRemainingActions());
		System.out.println("1 : Go to store");					
		System.out.println("2 : View crop status");
		System.out.println("3 : View animal status");
		System.out.println("4 : Tend to crops");
		System.out.println("5 : Feed animals");
		System.out.println("6 : Play with Animals");
		System.out.println("7 : Harvest crops");
		System.out.println("8 : Tend to farm land");
		if(Farm.isFinalDay()) {
		System.out.println("9 : Finish Game");}
		else {System.out.println("9 : Next Day");}
			
		System.out.println("\n Please enter the number that corresponds to the action you would like to perform!");
		
		String doNext = scan.next();
		
		switch(doNext) {
		
		case "1":
			//go to store
			store();
			break;
		
		case "2":
			//view crop status
			viewCropStatus();
			break;
		
		case "3":
			//view animal status
			viewAnimalStatus();
			break;
			
		case "4":
			//tend to crops
			break;
			
		case "5":
			//feed animals
			
			break;
			
		case "6":
			//play with animals 
			playAnimals();
			break;
			
		case "7":
			// harvest crops
			break;
			
		case "8":
			// tend to farm land
			break;
			
		case "9":
			// next day
			Farm.nextDay(); 
			break;
			
		default:
			System.out.println("That input is invalid, Try Again!");
			break;
		}
		play();
	}
	
	public static void playAnimals() {
		if(FarmerActions.getRemainingActions() == 0) {
			System.out.println("NO Actions Remaining!");
		}
		else if(Farm.cowPen.holdingAnimal.happiness == 10) { // As all animals have the same happiness
			System.out.println("Animals Have full happiness");
		}
		else {
			FarmerActions.playWithAnimals();
			System.out.println("Animals recieved a happiness boost!\n");
		}
	}
	
	public static void viewAnimalStatus() {
		System.out.println("Name, Count, Happiness, Healthiness, Daily Income\n");
		System.out.println(Farm.cowPen);
		System.out.println(Farm.pigPen);
		System.out.println(Farm.chickenPen);
		System.out.println("Press any character to go back to main menu");
		String doNext = scan.next();
	}
	
	public static void viewCropStatus() {
		boolean exit = false;
		while(!exit) {
		for(int i = 1; i <= Farm.cropFields.length;i++) {
			System.out.println(i +" - Crop Field #" + i);
			System.out.println(Farm.cropFields[i-1]);
		}
		System.out.println(Farm.cropFields.length +1 + " - back");
		String doNext = scan.next();
		int cropNumber = (Pattern.matches("[0-9]+", doNext)) ? Integer.parseInt(doNext) : 0;
		if(cropNumber == 7) {
			exit = true;
		}
		else if (cropNumber < 7 && cropNumber>0) {
			cropOptions(Farm.cropFields[cropNumber-1]);
		}
		else {
			System.out.println("Invalid input - Try again");
		}
		
		
		}
	}
	
	public static void cropOptions(CropField o) {
		boolean stop = false;
		while(!stop) {
		System.out.println(o);
		if(o.getPlantedCrop()==null) {
			System.out.println("1 - Plant Crop");
			System.out.println("2 - Spread Fertilizer");
			System.out.println("3 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				//plant crop
				plantCrop(o);
				break;
			case "2":
				//spread fertilizer
				break;
			case "3":
				//back
				stop = true;
				break;
			default:
				System.out.println("Invalid input - Try again");
			}
			
		}
		else {
			System.out.println("1 - Tend to crop");
			System.out.println("2 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				// tend to crop
				break;
			case "2":
				//back
				stop = true;
				break;
			default:
				System.out.println("Invalid input - Try again");
		}
		}
		}
	}
	
	public static void plantCrop(CropField o) {
		Crop[] crops = Crop.values();
		boolean back = false;
		while(!back) {
			for (int i=1; i < crops.length + 1; i++){
				System.out.println(i +" - "+ crops[i-1].getName()+ " (You have " + crops[i-1].getSeedAmount() + ")");
		}
		System.out.println(crops.length+1 + " - back");
		System.out.println("What would you like to plant?");
		String doNext = scan.next();
		int seedType = (Pattern.matches("[0-9]+", doNext)) ? Integer.parseInt(doNext) : 0;
		if(seedType == 7) {
			back = true;
		}
		else if (seedType < 7 && seedType>0) {
			if(crops[seedType-1].getSeedAmount()>0) {
			o.PlantCrop(crops[seedType-1]);
			back = true;
			}
			else {
				System.out.println("You dont have enough seeds - Buy some from the store!");
			}
		}
		else {
			System.out.println("Invalid input - Try again");
		}
		
		
	}}

	
	public static void store(){
		boolean exit = false;
		while (!exit) {
			System.out.println("What would you like to buy?");
			System.out.println("1 - Seeds");
			System.out.println("2 - Special Items");
			System.out.println("3 - Animals");
			System.out.println("4 - Exit");
			String choice = scan.next();
			switch (choice.trim()){
				case "1":
					buySeeds();
					break;
				case "2":
					buyItems();
					break;
				case "3":
					buyAnimals();
					break;
				case "4":
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
				if (crops[i-1].abilities() != "")
					System.out.println("    " + crops[i-1].abilities());
				System.out.println("    " + crops[i-1].description());
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
	
	public static void buyAnimals() {
		Animal[] animals = Animal.values();
		boolean back = false;
		while(back == false) {
			System.out.println("Your Money: $" + Farm.money);
			int i;
			for (i=1; i < animals.length + 1; i++){
				System.out.println(i + " - Purchase " + animals[i-1].getName() + " for $" + 
			    animals[i-1].getbuyPrice() + " (You have " + animals[i-1].getCurrentCount() + ")");
				System.out.println("You can hold a maximum of " + Farm.cowPen.getCapacity()); // As all animals pens have the same capacity
		}
			
		System.out.println(i + " - Back");
		String input = scan.next();
		int choice = (Pattern.matches("[0-9]+", input)) ? Integer.parseInt(input) : 0;

		if (choice == i)
			back = true;
		else if (choice > 0 && choice < animals.length + 1){
			System.out.println("How many would you like?");
			input = scan.next();

			// if input is valid parse it otherwise make amount 0
			int amount = (Pattern.matches("0*[1-9][0-9]*", input)) ? Integer.parseInt(input) : 0;
			if(amount + animals[choice-1].CurrentCount > Farm.cowPen.getCapacity()) {
				System.out.println("This would exceed the max capacity!");
			}

			else if (Farm.money >= (animals[choice-1].getbuyPrice() * amount)){
				if (amount > 0) {
					animals[choice - 1].CurrentCount += amount;
					System.out.println("You purchased " + amount + " " + animals[choice-1].getName());
					Farm.money -= animals[choice-1].getbuyPrice() * amount;
				}
			}else{
				System.out.println("You don't have enough money for that");
				System.out.println("To buy " + amount + " " + animals[choice-1].getName() + " you need $" + animals[choice-1].getbuyPrice() * amount + " but you only have $" + Farm.money);
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
