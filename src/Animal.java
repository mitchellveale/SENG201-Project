
public class Animal {

	
	String name;
	int happiness;
	int healthiness;
	int CurrentCount;
	int buyPrice;
	int dailyIncome;
	
	public Animal(String newName, int newHappiness, int newHealthiness, int newCurrentCount, int newbuyPrice, int newDailyIncome) {
		this.name = newName;
		this.happiness = newHappiness;
		this.healthiness = newHealthiness;
		this.CurrentCount = newCurrentCount;
		this.buyPrice = newbuyPrice;
		this.dailyIncome = newDailyIncome;
	}
		
	public String getName() {
		return name;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public int getHealthiness() {
		return healthiness;
	}
	
	public int getCurrentCount() {
		return CurrentCount;
	}
	
	public int getbuyPrice() {
		return buyPrice;
	}
	
	public int getdailyIncome() {
		return dailyIncome;
	}
	
	
	}
