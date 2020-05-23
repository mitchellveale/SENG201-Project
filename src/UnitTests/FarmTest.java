package UnitTests;

import SENGProject.Farm.Farm;
import SENGProject.Farm.FarmType;
import SENGProject.Farm.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarmTest {
    @BeforeEach
    void init(){
        Farm.createFarm(5, "Bob's Farm", "Bob", FarmType.EXPANSIVE_LAND);
    }

    @Test
    void nextDay() {
        Farm.nextDay();
        assertEquals(2, Farm.getCurrentDay());
        assertTrue(Farm.getFarmCondition() < 1);
    }

    @Test
    void useLottoTicket() {
        Item.LOTTO_TICKET.buy();
        int money = Farm.getMoney();
        Farm.useLottoTicket();
        assertTrue(Farm.getMoney() > money);
    }
}