
public class AnimalPen {

	Animal holdingAnimal;    // Eg cow, chicken, pig
	int capacity = 100;      // default of 100
	
	
	public AnimalPen(Animal newholdingAnimal) {
		this.holdingAnimal = newholdingAnimal;
	}
	
	public Animal getAnimal() {
		return holdingAnimal;
    }
	
	public int getCapacity() {
		return capacity;
	}
	
	public void upgradePen(int percentageIncrease) {
		capacity = capacity * percentageIncrease;
	}
	
}
