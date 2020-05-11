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

    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBaseYield() {
        return baseYield;
    }

    public int getBaseGrowTime() {
        return baseGrowTime;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    public boolean hasEnvironmentalResistance() {
        return environmentResistance;
    }

    public boolean doesFertilize() {
        return fertilizes;
    }

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

    public String description(){
        return "Grows in " + baseGrowTime + " day(s)\n    Sells for $" + sellPrice * baseYield;
    }

    public void buy(){
        Farm.money -= buyPrice;
        seedAmount++;
    }

    public int getSeedAmount() {
        return seedAmount;
    }

    public void buy(int amount){
        if (amount > 0) {
            Farm.money -= buyPrice * amount;
            seedAmount += amount;
        }
    }

    public void plant(){
        seedAmount--;
    }
}
