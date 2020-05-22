/**
 * A class which represents a crop
 * @author Ryan Bellamy and Mitchell Veale
 *
 */
public enum Crop{
    WHEAT ("Wheat", 5, 1, 100, 1, 20, false),
    CORN ("Corn", 10, 1, 200, 3, 20, false),
    SOYBEAN ("Soybean", 20, 2, 100, 3, 30, false),
    KALE ("Kale", 20, 2, 100, 2, 0, false),
    BEET ("Beet", 40, 5, 50, 3, 10, true),
    POTATO ("Potato", 50, 2, 200, 4, 0, false);
    private final String name;
    private final int buyPrice;
    private final int sellPrice;
    private final int baseYield;
    private final int baseGrowTime;
    private final int healthBoost;
    private final boolean fertilizes;
    private int seedAmount;

    /**
     * Initializes a Crop
     * @param name A string of the name of a crop
     * @param buyPrice An Int of the purchase price of a crop
     * @param sellPrice An Int of the sale price of a crop
     * @param baseYield An int of the default yield of a crop
     * @param baseGrowTime An Int of the default growing time of a crop
     * @param healthBoost An Int of the health boost Animals receive when crop is harvested
     * @param fertilizes A boolean that is true if it fertilizes the cropField upon harvest
     */
    Crop(String name, int buyPrice, int sellPrice, int baseYield, int baseGrowTime, int healthBoost, boolean fertilizes) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.baseYield = baseYield;
        this.baseGrowTime = baseGrowTime;
        this.healthBoost = healthBoost;
        this.fertilizes = fertilizes;
        seedAmount = 0;
    }

    /**
     * 
     * @return The Crops name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return Crops purchase price
     */
    public int getBuyPrice() {
        return buyPrice;
    }
    
    /**
     * 
     * @return Crops selling price
     */
    public int getSellPrice() {
        return sellPrice;
    }
    
    /**
     * 
     * @return Base yield of a crop
     */
    public int getBaseYield() {
        return baseYield;
    }
    
    /**
     * 
     * @return Base growing time of a crop
     */
    public int getBaseGrowTime() {
        return baseGrowTime;
    }
    
    /**
     * 
     * @return Amount of health boost animals get upon harvest of crop
     */
    public int getHealthBoost() {
        return healthBoost;
    }

    /**
     * 
     * @return True if Crop fertilizes crop field on harvesting, otherwise false
     */
    public boolean doesFertilize() {
        return fertilizes;
    }
    
    /**
     * 
     * @return A description of abilities should a crop have special abilities
     */
    public String abilities() {
        String ans = "";
        if (healthBoost > 0)
            ans += "Grants " + healthBoost + " health to all animals when harvested. ";
        if (fertilizes)
            ans += "Fertilizes the field it was grown in when harvested. ";
        return ans;
    }
    
    /**
     * 
     * @return Standard description of a crop
     */
    public String description(){
        return "Grows in " + baseGrowTime + " day(s)\n    Sells for $" + sellPrice * baseYield;
    }
    
    /**
     * Buys a crop seed, takes care of payment, increments seed count
     */
    public void buy(){
        Farm.money -= buyPrice;
        seedAmount++;
    }
    
    /**
     * 
     * @return Number of seed for a crop type
     */
    public int getSeedAmount() {
        return seedAmount;
    }
    
    /**
     * Buys x amount of crop seeds, takes care of payment, increments seed count
     * @param amount An int of the quantity of seeds to buy
     */
    public void buy(int amount){
        if (amount > 0) {
            Farm.money -= buyPrice * amount;
            seedAmount += amount;
        }
    }
    
    /**
     * Decrements seed amount
     */
    public void plant(){
        seedAmount--;
    }
}
