import java.util.Scanner;
import java.util.regex.Pattern;

public class TextGame {

	static Scanner scan = new Scanner(System.in);
	
	private static void setup() {
		int gameLength = 0;
		boolean stop = false;
		while(!stop) {
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
		while(!stop) {
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
		while(!stop) {
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
		while(!stop) {
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
	
	
	
	private static void play() {
		System.out.println("Day " + Farm.getCurrentDay() + " / " + Farm.getGameLength() + "!");
		System.out.println("Bank: $" + Farm.getMoney());
		System.out.println("Farm Condition: "+ Farm.getFarmCondition());
		System.out.println("Actions remaining: "+ FarmerActions.getRemainingActions());
		System.out.println("1 : Go to store");					
		System.out.println("2 : Manage Crops");
		System.out.println("3 : View animal status");
		System.out.println("4 : Feed animals");
		System.out.println("5 : Play with Animals");
		System.out.println("6 : Harvest crops");
		System.out.println("7 : Tend to farm land");
		System.out.println("8 : Use Lotto ticket");
		System.out.println("9 : Use Animal Growth Compound");
		if(Farm.isFinalDay()) {
		System.out.println("10 : Finish Game");}
		else {System.out.println("10 : Next Day");}
			
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
			//feed animals
			feedAnimals();
			break;
			
		case "5":
			//play with animals 
			playAnimals();
			break;
			
		case "6":
			// harvest crops
			harvestCrops();
			break;
			
		case "7":
			// tend to farm land
			if(FarmerActions.getRemainingActions()>0) {
			FarmerActions.tendToFarm();
			System.out.println("Farm Condition Increased!\n");}
			else {System.out.println("No remaining Actions!\n");}
			break;
			
		case "8":
			if(Item.LOTTO_TICKET.getAmount()>0) {
				System.out.println("You won $"+ Farm.useLottoTicket());
			}
			else {
				System.out.println("You don't have a lotto ticket!");
			}
			break;
			
		case "9":
			// Use animal growth compound
			if(FarmerActions.getRemainingActions()>0) {
				if(Item.BREEDING_COMPOUND.getAmount()>0) {
				FarmerActions.useBreedingCompound();
				System.out.println("Breeding Compound used");
				}
				else {System.out.println("You don't have enough Breeding Compound");
				}
			}
			else {System.out.println("No remaining Actions!\n");}
				break;
			
		case "10":
			// next day
			Farm.nextDay(); 
			break;
			
		default:
			System.out.println("That input is invalid, Try Again!\n");
			break;
		}
		play();
	}
	
	private static void feedAnimals() {
		boolean back = false;
		while(!back) {
			System.out.println("1 - Feed animals treats (You have " + Item.ANIMAL_TREATS.getAmount()+")");
			System.out.println("2 - Feed animals Hay (You have " + Item.HAY.getAmount()+")");
			System.out.println("3 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				if(FarmerActions.getRemainingActions()>0) {
				    if(Item.ANIMAL_TREATS.getAmount()>0) {
					    FarmerActions.feedAnimalsTreats();
					    System.out.println("All Animals recieved a health and happiness boost!");
				    } 
				    else {
					    System.out.println("You don't have any animal treats!");
				    }}
				else {
					System.out.println("No actions remaining!");
				}
				break;
				
			case "2":
				if(FarmerActions.getRemainingActions()>0) {
				    if(Item.HAY.getAmount()>0) {
					    FarmerActions.feedAnimalsHay();
					    System.out.println("All Animals recieved a health boost!\n");
				    }
				    else {
					    System.out.println("You don't have any Hay!\n");
				    }}
				else {
					System.out.println("No actions remaining!\n");
				}
				break;
				
			case "3":
				back = true;
				break;
				
			default:
				System.out.println("Invalid input!");
				break;
			}
		}
	}
	
	
	private static void harvestCrops() {
		boolean back = false;
		int ready = 0;
		while(!back) {
			System.out.println("Crops ready for harvest:\n");
			for(CropField field : Farm.getCropFields()) {
				if(field.isMature()) {
					System.out.println(field);
					ready += 1;
				}
			}
			System.out.println("1 - Harvest all");
			System.out.println("2 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				//Harvest all
				if(ready > 0) {
				    if(FarmerActions.getRemainingActions()>0) {
				        int before = Farm.getMoney();
				        FarmerActions.harvestCrops();
				        int after = Farm.getMoney();
				        System.out.println("You made $" + (after-before));
				    }
				    else {
					    System.out.println("No actions remaining");
				    }}
				else {System.out.println("Nothing to harvest");}
				break;
			case "2":
				back = true;
				break;
			default:
				System.out.println("Invalid input - Try again");
			}
			
		}
	}
	
	private static void playAnimals() {
		if(FarmerActions.getRemainingActions() < 0) {
			System.out.println("NO Actions Remaining!");
		}
		else if(Farm.getAnimalPens()[0].getAnimal().getHappiness() == 10) { // As all animals have the same happiness
			System.out.println("Animals Have full happiness already");
		}
		else {
			FarmerActions.playWithAnimals();
			System.out.println("Animals recieved a happiness boost!\n");
		}
	}
	
	private static void viewAnimalStatus() {
		System.out.println("Name, Count, Happiness, Healthiness, Daily Income\n");
		for(AnimalPen pen : Farm.getAnimalPens()) {
			System.out.println(pen);
    	}
		System.out.println("Press any character and enter to go back to main menu");
		String doNext = scan.next();
	}
	
	private static void viewCropStatus() {
		boolean exit = false;
		int i = 1;
		while(!exit) {
		for(CropField field : Farm.getCropFields()) {
			System.out.println(i +" - Crop Field #" + i);
			System.out.println(field);
			i++;
		}
		System.out.println(i + " - back");
		String doNext = scan.next();
		int cropNumber = (Pattern.matches("[0-9]+", doNext)) ? Integer.parseInt(doNext) : 0;
		if(cropNumber == 7) {
			exit = true;
		}
		else if (cropNumber < 7 && cropNumber>0) {
			cropOptions(Farm.getCropFields()[cropNumber-1]);
		}
		else {
			System.out.println("Invalid input - Try again");
		}
		
		
		}
	}
	
	private static void cropOptions(CropField o) {
		boolean stop = false;
		while(!stop) {
		System.out.println(o);
		if(o.getPlantedCrop()==null) {
			System.out.println("1 - Plant Crop");
			System.out.println("2 - Spread Fertilizer (You have " + Item.FERTILIZER.getAmount()+")");
			System.out.println("3 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				//plant crop
				plantCrop(o);
				break;
			case "2":
				//spread fertilizer
				spreadFert(o);
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
				tendCrop(o);
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
	
	private static void tendCrop(CropField o) {
		boolean exit = false;
		while(!exit) {
			System.out.println("1 - Water crop");
			System.out.println("2 - Use Growth Compound");
			System.out.println("3 - Back");
			String doNext = scan.next();
			switch(doNext) {
			case "1":
				//water crop
				if(FarmerActions.getRemainingActions()>0) {
				FarmerActions.tendToCrop(o,false);
				System.out.println("Crop was watered!\n");}
				else {
					System.out.println("No remaining actions!");
				}
				break;
			case "2":
				//Growth compound
				if(FarmerActions.getRemainingActions()>0) {
				    if(Item.GROWTH_COMPOUND.getAmount()>0) {
					    FarmerActions.tendToCrop(o,true);
					    System.out.println("Growth compound was applied");
				    }
				    else {System.out.println("You don't have enough");}}
				else {System.out.println("No remaining actions!");}
				break;
				
			case "3":
				exit = true;
				break;
			default:
				System.out.println("Invalid input - Try again");
			}
		}
	}
	
	private static void spreadFert(CropField o) {
		if(FarmerActions.getRemainingActions()>0) {
		if(Item.FERTILIZER.getAmount()>0) {
			o.fertilize();
			Item.FERTILIZER.use();
		}
		else {
			System.out.println("You don't have enough");
		}
		}
		else {System.out.println("No remaining actions!");}
	}
	
	private static void plantCrop(CropField o) {
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
			if(FarmerActions.getRemainingActions() > 0) {
			    if(crops[seedType-1].getSeedAmount()>0) {
			    o.PlantCrop(crops[seedType-1]);
			    back = true;
			    }
			    else {
				    System.out.println("You dont have enough seeds - Buy some from the store!");
			    }}
			    else {System.out.println("No remaining actions!");}
		}
		else {
			System.out.println("Invalid input - Try again");
		}
		}
	}

	
	private static void store(){
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

	private static void buySeeds(){
		Crop[] crops = Crop.values();
		boolean back = false;
		while (!back){
			System.out.println("Your Money: $" + Farm.getMoney());
			int i;
			for (i=1; i < crops.length + 1; i++){
				System.out.println(i + " - Purchase " + crops[i-1].getName() + " seeds for $" + crops[i-1].getBuyPrice() + " (You have " + crops[i-1].getSeedAmount() + ")");
				if (!crops[i - 1].abilities().equals(""))
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

				if (Farm.getMoney() >= (crops[choice - 1].getBuyPrice() * amount)) {
					if (amount > 0) {
						crops[choice - 1].buy(amount);
						System.out.println("You purchased " + amount + " " + crops[choice - 1].getName() + " seeds");
					}
				} else {
					System.out.println("You don't have enough money for that");
					System.out.println("To buy " + amount + " " + crops[choice - 1].getName() + " seeds you need $" + crops[choice - 1].getBuyPrice() * amount + " but you only have $" + Farm.getMoney());
				}
				if (amount <= 0)
					System.out.println("That input is invalid");
			}else{
				System.out.println("That input is not valid");
			}
		}
	}

	private static void buyItems(){
		Item[] items = Item.values();
		boolean back = false;
		while (!back){
			System.out.println("Your Money: $" + Farm.getMoney());
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

				if (Farm.getMoney() >= (items[choice-1].getPrice() * amount)){
					if (amount > 0) {
						items[choice - 1].buy(amount);
						System.out.println("You purchased " + amount + " " + items[choice-1].getName());
					}
				}else{
					System.out.println("You don't have enough money for that");
					System.out.println("To buy " + amount + " " + items[choice-1].getName() + " you need $" + items[choice-1].getPrice() * amount + " but you only have $" + Farm.getMoney());
				}
				if (amount <= 0)
					System.out.println("That input is invalid");
			}else{
				System.out.println("That input is not valid");
			}
		}
	}
	
	private static void buyAnimals() {
		Animal[] animals = Animal.values();
		boolean back = false;
		while(!back) {
			System.out.println("Your Money: $" + Farm.getMoney());
			int i;
			for (i=1; i < animals.length + 1; i++){
				System.out.println(i + " - Purchase " + animals[i-1].getName() + " for $" + 
			    animals[i-1].getbuyPrice() + " (You have " + animals[i-1].getCurrentCount() + ")");
				System.out.println("You can hold a maximum of " + Farm.getAnimalPens()[0].getCapacity()); // As all animals pens have the same capacity
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
			if(amount + animals[choice-1].getCurrentCount() > Farm.getAnimalPens()[0].getCapacity()) {
				System.out.println("This would exceed the max capacity!");
			}

			else if (Farm.getMoney() >= (animals[choice-1].getbuyPrice() * amount)){
				if (amount > 0) {
					animals[choice - 1].addAnimals(amount);
					System.out.println("You purchased " + amount + " " + animals[choice-1].getName());
					Farm.alterMoney(-1*(animals[choice-1].getbuyPrice() * amount)); 
				}
			}else{
				System.out.println("You don't have enough money for that");
				System.out.println("To buy " + amount + " " + animals[choice-1].getName() + " you need $" + animals[choice-1].getbuyPrice() * amount + " but you only have $" + Farm.getMoney());
			}
			if (amount <= 0)
				System.out.println("That input is invalid");
		}else{
			System.out.println("That input is not valid");
		}
		
	}
	}
	
	
	/**
	 * Sets up the text game and then runs it.
	 */
	public static void startGame(){
		//TextGame game = new TextGame();
		setup();
		play();


	}

}
