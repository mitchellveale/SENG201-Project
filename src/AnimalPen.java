
public class AnimalPen {

	Animal holdingAnimal;    // Eg cow, chicken, pig
	int capacity;      
	int baseCapacity = 100;
	
	
	
	public AnimalPen(Animal newholdingAnimal) {
		this.holdingAnimal = newholdingAnimal;
		this.capacity = (int)Math.round(baseCapacity * Farm.getFarmType().getAnimalPenSizeMultiplier());
	}
	
	public String toString() {
		return holdingAnimal.name + " (" +
			   holdingAnimal.CurrentCount + ")  " +
			   holdingAnimal.happiness + "/10  " +
			   holdingAnimal.healthiness + "/10  $" +
			   holdingAnimal.getdailyIncome();
	}
	
	public Animal getAnimal() {
		return holdingAnimal;
    }
	
	public double getCapacity() {
		return capacity;
	}
	
	public void animalMultiplication(double i) {
		this.holdingAnimal.CurrentCount = (int)Math.round(this.holdingAnimal.CurrentCount*i);
		if(this.holdingAnimal.CurrentCount > this.capacity) {
			this.holdingAnimal.CurrentCount = this.capacity;
		}
	}
	
	
}