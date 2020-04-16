public class Farm {
    /**
     * the name of the farm
     */
    protected String farmName;
    /**
     * the name of the farmer
     */
    //If we add farmer attributes later we should change this to a 'Farmer' class
    protected String farmerName;
    /**
     * the type of farm
     */
    protected FarmType farmType;

    /**
     * Instantiates the Farm class
     * @param farmName the name of the farm
     * @param farmerName the name of the farmer
     * @param farmType  the farm type.
     *                 An array of possible farm types can be obtained with {@link #farmTypes()}
     */
    public Farm(String farmName, String farmerName, FarmType farmType){
        this.farmName = farmName;
        this.farmerName = farmerName;
        this.farmType = farmType;
    }

    /**
     * Used to declare all farm types
     * @return An array of all farm types
     */
    public static FarmType[] farmTypes(){
        return new FarmType[]{
                new FarmType("Farm type 1"),
                new FarmType("Farm type 2"),
                new FarmType("Farm type 3"),
                new FarmType("Farm type 4")
        };
    }
}
