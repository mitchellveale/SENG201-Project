public enum Crop{
    WHEAT ("Wheat", 5, 1, 100, 1, 20, false, false),
    CORN ("Corn", 10, 1, 200, 3, 20, false, false),
    SOYBEAN ("Soybean", 20, 2, 100, 3, 30, false, false),
    KALE ("Kale", 20, 2, 100, 2, 0, true, false),
    BEET ("Beet", 40, 5, 50, 3, 10, false, true),
    POTATO ("Potato", 50, 2, 200, 4, 0, true, false);
    private final String name;
    private final int buyPrice;
    private final int sellPrice;
    private final int baseYield;
    private final int baseGrowTime;
    private final int healthBoost;
    private final boolean environmentResistance;
    private final boolean fertilizes;
    private int seedAmount;

    /**
     * Initializes a Crop
     * @param name
     * @param buyPrice
     * @param sellPrice
     * @param baseYield
     * @param baseGrowTime
     * @param healthBoost
     * @param environmentResistance
     * @param fertilizes
     */
    Crop(String name, int buyPrice, int sellPrice, int baseYield, int baseGrowTime, int healthBoost, boolean environmentResistance, boolean fertilizes) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.baseYield = baseYield;
        this.baseGrowTime = baseGrowTime;
        this.healthBoost = healthBoost;
        this.environmentResistance = environmentResistance;
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
     * @return True if crop is resistant to environmental event, otherwise false
     */
    public boolean hasEnvironmentalResistance() {
        return environmentResistance;
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
        //if (environmentResistance)
        //    ans += "Immune to environmental effects.\n  ";
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
     * @param amount
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
