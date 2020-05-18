public enum Item {
    FERTILIZER ("Fertilizer", "Increases yield of next planted crop by 50%. Use on empty crop field", 50),
    BREEDING_COMPOUND ("Breeding Compound", "Increases amount of an animals the next day by 50%", 150),
    ANIMAL_TREATS ("Animal Treats", "Improves all animal happiness and healthiness by 3", 100),
    GROWTH_COMPOUND ("Growth Compound", "Increases a crop's growing speed by 100%", 200),
    HAY ("Hay", "Increases all animal healthiness by 3", 100),
    LOTTO_TICKET ("Lotto Ticket", "Wanna try your luck?", 500);
    private final String name;
    private final String description;
    private final int price;
    private int amount;


    /**
     * Initializes an Item
     * @param name
     * @param description
     * @param price
     */
    Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        amount = 0;
    }

    /**
     * 
     * @return Items name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return Items description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return Items price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 
     * @return Amount of the Item
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Increments the Items amount, deducts price from Farms money
     */
    public void buy(){
        amount++;
        Farm.money -= price;
    }

    /**
     * Increments the Items amount by an amount, deducts total cost from Farms money
     * @param amount
     */
    public void buy(int amount){
        if (amount > 0) {
            Farm.money -= price * amount;
            this.amount += amount;
        }
    }

    /**
     * decrements the amount of the Item
     */
    public void use(){
        amount--;
    }
}
