public class Game extends Farm {
    /**
     * the length of the game in days
     */
    private int gameLength;

    /**
     * Instatiates {@link Game}
     * @param gameLength the length of the game in days
     * @param farmName the name of the farm
     * @param farmerName the name of the farmer
     * @param farmType  the farm type.
     *                  An array of possible farm types can be obtained with {@link #farmTypes()}
     */
    public Game(int gameLength, String farmName, String farmerName, FarmType farmType) {
        super(farmName, farmerName, farmType);
        this.gameLength = gameLength;
    }

}
