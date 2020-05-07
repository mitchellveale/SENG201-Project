public enum Item {
    FERTILIZER ("Fertilizer", "Increases yield of next planted crop by 50%. Use on empty crop field", 50),
    BREEDING_COMPOUND ("Breeding Compound", "Increases amount of an animals gained when pressing next day by 50%", 150),
    ANIMAL_TREATS ("Animal Treats", "Improves all animal happiness by 30", 100),
    GROWTH_COMPOUND ("Growth Compound", "Increases a crop's growing speed by 100%", 200),
    HAY ("Hay", "Increases all animal healthiness by 30", 100),
    LOTTO_TICKET ("Lotto Ticket", "Wanna try your luck?", 500);
    protected final String name;
    protected final String description;
    protected final int price;
    protected int amount;


    Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        amount = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void buy(){
        amount++;
        Farm.money -= price;
    }

    public void buy(int amount){
        if (amount > 0) {
            Farm.money -= price * amount;
            this.amount += amount;
        }
    }

    public void use(){
        amount--;
    }
}