
public enum Animal {

	COW ("Cow", 5, 5, 10, 20, 5),
	PIG ("Pig", 5, 5, 10, 10, 1),
	Chicken("Chicken", 5 , 5, 10, 6, 3);
	
	private String name;
	private int happiness;
	private int healthiness;
	private int currentCount;
	private int buyPrice;
	private int baseDailyIncome;
	
	/**
	 * Constructs an Animal
	 * @param newName Name of Animal
	 * @param newHappiness Initial happiness
	 * @param newHealthiness Initial healthiness
	 * @param newCurrentCount Initial count
	 * @param newbuyPrice Price of Animal
	 * @param newDailyIncome Base daily income
	 */
	Animal(String name, int happiness, int healthiness, int currentCount, int buyPrice, int dailyIncome) {
		this.name = name;
		this.happiness = happiness;
		this.healthiness = healthiness;
		this.currentCount = currentCount;
		this.buyPrice = buyPrice;
		this.baseDailyIncome = (int)Math.round(dailyIncome * Farm.getFarmType().getValueMultiplier());
	}
	/**
	 * Adds i to the currentCount of Animal
	 * @param i
	 */
	public void addAnimals(int i) {
		this.currentCount += i;
	}
	/**
	 * Sets currentCount of Animal to i
	 * @param i
	 */
	public void setAnimals(int i) {
		this.currentCount = i;
	}
	/**
	 * Increases Happiness of Animal by i
	 * @param i
	 */
	public void increaseHappiness(int i) {
		this.happiness += i;
		if(this.happiness > 10) {
			this.happiness = 10;
		}
		if(this.happiness < 0) {
			this.happiness = 0;
		}
	}
	/**
	 * Increases Healthiness of Animal by i
	 * @param i
	 */
	public void increaseHealthiness(int i) {
		this.healthiness += i;
		if(this.healthiness > 10) {
			this.healthiness = 10;
		}
		if(this.healthiness < 0) {
			this.healthiness = 0;
		}
	}
	
	/**
	 * 
	 * @return Animal Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return Animal Happiness
	 */
	public int getHappiness() {
		return happiness;
	}
	/**
	 * 
	 * @return Animal Happiness
	 */
	public int getHealthiness() {
		return healthiness;
	}
	/**
	 * 
	 * @return currentCount of Animals
	 */
	public int getCurrentCount() {
		return currentCount;
	}
	/**
	 * 
	 * @return buyPrice of Animal
	 */
	public int getbuyPrice() {
		return buyPrice;
	}
	/**
	 * 
	 * @return dailyIncome of Animal
	 */
	public double getdailyIncome() {
		return baseDailyIncome * ((happiness+healthiness)/10.0);
	}
	
	
}

