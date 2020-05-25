package UnitTests;

import SENGProject.Farm.Crop;
import SENGProject.Farm.Farm;
import SENGProject.Farm.FarmType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CropFieldTest {
    @BeforeEach
    void init(){
        Farm.createFarm(5, "Bob's Farm", "Bob", FarmType.EXPANSIVE_LAND);
    }

    @Test
    void plantCrop() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        assertSame(Farm.getCropFields()[0].getPlantedCrop(), Crop.POTATO);
    }

    @Test
    void addGrowthMultiplier() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        Farm.getCropFields()[0].addGrowthMultiplier(2);
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 2);
    }

    @Test
    void grow() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        Farm.getCropFields()[0].grow();
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 3);
    }

    @Test
    void harvest() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].harvest();
        assertNull(Farm.getCropFields()[0].getPlantedCrop());
    }

    @Test
    void harvestValue() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        assertEquals(Farm.getCropFields()[0].harvestValue(), 0);
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        assertTrue(Farm.getCropFields()[0].harvestValue() > 0);
    }

    @Test
    void isMature() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        assertFalse(Farm.getCropFields()[0].isMature());
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        assertTrue(Farm.getCropFields()[0].isMature());
    }

    @Test
    void getGrowthPercent() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        assertEquals(Farm.getCropFields()[0].getGrowthPercent(), 0);
        Farm.getCropFields()[0].grow();
        assertEquals(Farm.getCropFields()[0].getGrowthPercent(), 0.25);
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        Farm.getCropFields()[0].grow();
        assertEquals(Farm.getCropFields()[0].getGrowthPercent(), 1);
    }

    @Test
    void getRemainingGrowTime() {
        Farm.getCropFields()[0].PlantCrop(Crop.POTATO);
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 4);
        Farm.getCropFields()[0].addGrowthMultiplier(2);
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 2);
        Farm.getCropFields()[0].grow();
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 1);
        Farm.getCropFields()[0].grow();
        assertEquals(Farm.getCropFields()[0].getRemainingGrowTime(), 0);
    }
}