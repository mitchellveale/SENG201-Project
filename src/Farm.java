import java.util.ArrayList;

public class Farm {
	
    String farmName;
    String farmerName;
    int farmType;
    ArrayList cropList = new ArrayList();
    ArrayList animalList = new ArrayList();
    double bank = 0;
    

  
    public Farm(String farmName, String farmerName, int farmType){
        this.farmName = farmName;
        this.farmerName = farmerName;
        this.farmType = farmType;
        if (farmType == 3) {
        	bank += 1000;
        }
    }
    
    public double getBank() {
    	return bank;
    }
    
    



}
