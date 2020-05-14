
public enum Animal {

	COW ("Cow", 7, 7, 10, 15, 5),
	PIG ("Pig", 7, 7, 10, 5, 1),
	Chicken("Chicken", 7 , 7, 10, 6, 3);
	
	String name;
	int happiness;
	int healthiness;
	double CurrentCount;
	int buyPrice;
	double baseDailyIncome;
	
	Animal(String newName, int newHappiness, int newHealthiness, int newCurrentCount, int newbuyPrice, int newDailyIncome) {
		this.name = newName;
		this.happiness = newHappiness;
		this.healthiness = newHealthiness;
		this.CurrentCount = newCurrentCount;
		this.buyPrice = newbuyPrice;
		this.baseDailyIncome = newDailyIncome * Farm.getFarmType().getValueMultiplier();
	}
	
	public void increaseHappiness(int i) {
		this.happiness += i;
		if(this.happiness > 10) {
			this.happiness = 10;
		}
	}
	
	public void increaseHealthiness(int i) {
		this.healthiness += i;
		if(this.healthiness > 10) {
			this.healthiness = 10;
		}
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
	
	public double getCurrentCount() {
		return CurrentCount;
	}
	
	public int getbuyPrice() {
		return buyPrice;
	}
	
	public double getdailyIncome() {
		return baseDailyIncome * ((happiness+healthiness)/10);
	}
	
	
}

