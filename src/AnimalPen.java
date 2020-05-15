
public class AnimalPen {

	private Animal holdingAnimal;    
	private int capacity;      
	private int baseCapacity = 100;
	
	
	
	public AnimalPen(Animal newholdingAnimal) {
		this.holdingAnimal = newholdingAnimal;
		this.capacity = (int)Math.round(baseCapacity * Farm.getFarmType().getAnimalPenSizeMultiplier());
	}
	
	public String toString() {
		return holdingAnimal.getName() + " (" +
			   holdingAnimal.getCurrentCount() + ")  " +
			   holdingAnimal.getHappiness() + "/10  " +
			   holdingAnimal.getHealthiness() + "/10  $" +
			   holdingAnimal.getdailyIncome();
	}
	
	public Animal getAnimal() {
		return holdingAnimal;
    }
	
	public double getCapacity() {
		return capacity;
	}
	
	public void animalMultiplication(double i) {
		this.holdingAnimal.setAnimals((int)Math.round(this.holdingAnimal.getCurrentCount()*i)); 
		if(this.holdingAnimal.getCurrentCount() > this.capacity) {
			this.holdingAnimal.setAnimals(this.capacity);
		}
	}
	
	
}