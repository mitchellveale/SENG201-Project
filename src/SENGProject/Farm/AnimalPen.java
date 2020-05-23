package SENGProject.Farm;

/**
 * A class which represents a Pen which holds Animals
 * @author Ryan Bellamy and Mitchell Veale
 *
 */
public class AnimalPen {

	private Animal holdingAnimal;    
	private int capacity;      
	private int baseCapacity = 100;
	
	/**
	 * Constructs AnimalPen
	 * @param holdingAnimal An object of type Animal
	 */
	public AnimalPen(Animal holdingAnimal) {
		this.holdingAnimal = holdingAnimal;
		this.capacity = (int)Math.round(baseCapacity * Farm.getFarmType().getAnimalPenSizeMultiplier());
	}
	
	public String toString() {
		return holdingAnimal.getName() + " (" +
			   holdingAnimal.getCurrentCount() + ")  " +
			   holdingAnimal.getHappiness() + "/10  " +
			   holdingAnimal.getHealthiness() + "/10  $" +
			   holdingAnimal.getdailyIncome();
	}
	/**
	 * 
	 * @return Animal within AnimalPen
	 */
	public Animal getAnimal() {
		return holdingAnimal;
    }
	/**
	 * 
	 * @return Capacity of AnimalPen
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Multiplies currentCount of the holdingAnimal by i.
	 * If currentCount > capacity, currentCount becomes the capacity.
	 * @param i A double which multiplies the count of the Animal
	 */
	public void animalMultiplication(double i) {
		this.holdingAnimal.setAnimals((int)Math.round(this.holdingAnimal.getCurrentCount()*i)); 
		if(this.holdingAnimal.getCurrentCount() > this.capacity) {
			this.holdingAnimal.setAnimals(this.capacity);
		}
	}
	
	
}