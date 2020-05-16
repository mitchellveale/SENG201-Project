
public enum Animal {

	COW ("Cow", 5, 5, 10, 20, 5),
	PIG ("Pig", 5, 5, 10, 10, 1),
	Chicken("Chicken", 5 , 5, 10, 6, 3);
	
	String name;
	private int happiness;
	private int healthiness;
	private int CurrentCount;
	private int buyPrice;
	private int baseDailyIncome;
	
	Animal(String newName, int newHappiness, int newHealthiness, int newCurrentCount, int newbuyPrice, int newDailyIncome) {
		this.name = newName;
		this.happiness = newHappiness;
		this.healthiness = newHealthiness;
		this.CurrentCount = newCurrentCount;
		this.buyPrice = newbuyPrice;
		this.baseDailyIncome = (int)Math.round(newDailyIncome * Farm.getFarmType().getValueMultiplier());
	}
	
	public void addAnimals(int i) {
		this.CurrentCount += i;
	}
	
	public void setAnimals(int i) {
		this.CurrentCount = i;
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
	
	public int getCurrentCount() {
		return CurrentCount;
	}
	
	public int getbuyPrice() {
		return buyPrice;
	}
	
	public int getdailyIncome() {
		return baseDailyIncome * ((happiness+healthiness)/10);
	}
	
	
}

