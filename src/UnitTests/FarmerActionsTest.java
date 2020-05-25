package UnitTests;

import SENGProject.Farm.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarmerActionsTest {

    @BeforeEach
    void setUp() {
        Farm.createFarm(5, "Bob's Farm", "Bob", FarmType.EXPANSIVE_LAND);

    }

    @Test
    void resetActions() {
        FarmerActions.tendToFarm();
        FarmerActions.resetActions();
        assertEquals(FarmerActions.getRemainingActions(), 2);
    }

    @Test
    void useBreedingCompound() {
        Item.BREEDING_COMPOUND.buy();
        FarmerActions.useBreedingCompound();
        assertEquals(Item.BREEDING_COMPOUND.getAmount(), 0);
        assertEquals(Farm.getAnimalPens()[0].getAnimal().getCurrentCount(), 15);
    }

    @Test
    void harvestCrops() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        FarmerActions.harvestCrops();
        assertNull(Farm.getCropFields()[0].getPlantedCrop());
    }

    @Test
    void tendToCrop() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        FarmerActions.tendToCrop(Farm.getCropFields()[0], false);
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 2);
        Farm.getCropFields()[1].PlantCrop(Crop.CORN);
        Item.GROWTH_COMPOUND.buy();
        FarmerActions.tendToCrop(Farm.getCropFields()[1], true);
        assertEquals(Farm.getCropFields()[1].getRemainingGrowTime(), 1);
    }

    @Test
    void feedAnimalsHay() {
        Item.HAY.buy();
        //This ensures that healthiness is 0
        Farm.getAnimalPens()[0].getAnimal().increaseHealthiness(-11);
        FarmerActions.feedAnimalsHay();
        assertEquals(Farm.getAnimalPens()[0].getAnimal().getHealthiness(), 3);
    }

    @Test
    void feedAnimalsTreats() {
        Item.ANIMAL_TREATS.buy();
        Farm.getAnimalPens()[0].getAnimal().increaseHealthiness(-11);
        Farm.getAnimalPens()[0].getAnimal().increaseHappiness(-11);
        FarmerActions.feedAnimalsTreats();
        assertEquals(Farm.getAnimalPens()[0].getAnimal().getHealthiness(), 3);
        assertEquals(Farm.getAnimalPens()[0].getAnimal().getHappiness(), 3);
    }

    @Test
    void playWithAnimals() {
        Farm.getAnimalPens()[0].getAnimal().increaseHappiness(-11);
        FarmerActions.playWithAnimals();
        assertEquals(Farm.getAnimalPens()[0].getAnimal().getHappiness(), 2);
    }

    @Test
    void tendToFarm() {
        Farm.nextDay();
        assertTrue(Farm.getFarmCondition() < 1);
        FarmerActions.tendToFarm();
        assertEquals(1, Farm.getFarmCondition());
    }
}