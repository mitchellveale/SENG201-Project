public class Game extends Farm {
    private int gameLength;

    public Game(int gameLength, String farmName, String farmerName, FarmType farmType) {
        super(farmName, farmerName, farmType);
        this.gameLength = gameLength;
    }
}
