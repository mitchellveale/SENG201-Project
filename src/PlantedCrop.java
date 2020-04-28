public class PlantedCrop {
    private Crop crop;
    private double growthMultiplier;
    private double yieldMultiplier;

    private int growth;
    private double amount;

    public PlantedCrop(Crop crop){
        this.crop = crop;

        growthMultiplier = 1d;
        yieldMultiplier = 1d;

        growth = 1;
        amount = 0d;
    }
}
