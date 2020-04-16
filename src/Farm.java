public class Farm {
    private String farmName;
    //If we add farmer attributes later we should change this to a 'Farmer' class
    private String farmerName;
    private FarmType farmType;

    public Farm(String farmName, String farmerName, FarmType farmType){
        this.farmName = farmName;
        this.farmerName = farmerName;
        this.farmType = farmType;
    }

    public static FarmType[] farmTypes(){
        return new FarmType[]{
                new FarmType("Farm type 1"),
                new FarmType("Farm type 2"),
                new FarmType("Farm type 3"),
                new FarmType("Farm type 4")
        };
    }
}
