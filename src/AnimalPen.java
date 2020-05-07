
public class AnimalPen {

	Animal holdingAnimal;    // Eg cow, chicken, pig
	double capacity;      
	int baseCapacity = 100;
	
	
	
	public AnimalPen(Animal newholdingAnimal) {
		this.holdingAnimal = newholdingAnimal;
		this.capacity = baseCapacity * Farm.getFarmType().getAnimalPenSizeMultiplier();
	}
	
	public String toString() {
		return holdingAnimal.name + " " +
			   holdingAnimal.CurrentCount + " " +
			   holdingAnimal.happiness + " " +
			   holdingAnimal.healthiness + " " +
			   holdingAnimal.dailyIncome;
	}
	
	public Animal getAnimal() {
		return holdingAnimal;
    }
	
	public double getCapacity() {
		return capacity;
	}
	
	
}