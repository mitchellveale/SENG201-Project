/**
 * An Enum which represents an Animal object
 * @author Ryan Bellamy and Mitchell Veale
 *
 */
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
	 * @param name A string of the Animals name
	 * @param happiness An int of the Animals initial happiness
	 * @param healthiness An int of the Animals initial healthiness
	 * @param currentCount An int of the initial amount of Animals
	 * @param buyPrice An int of the purchase price of an Animal
	 * @param dailyIncome An int of the daily income of an Animal before FarmType multipliers
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
	 * @param i Int of the amount of Animals to add to currentCount
	 */
	public void addAnimals(int i) {
		this.currentCount += i;
	}
	/**
	 * Sets currentCount of Animal to i
	 * @param i Int that the currentCount of the Animal is set to
	 */
	public void setAnimals(int i) {
		this.currentCount = i;
	}
	/**
	 * Increases Happiness of Animal by i
	 * @param i Int that the happiness of an Animal is changed by
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
	 * @param i Int hat the healthiness of an Animal is changed by
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

