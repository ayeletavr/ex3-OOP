import org.junit.*;
import static org.junit.Assert.*;
import oop.ex3.spaceship.*;
import oop.ex3.spaceship.ItemFactory;

import java.util.HashMap;
import java.util.Map;

public class LockerTest {
    private static Locker testLockerLowCap;
    private static Locker testLockerHighCap;
    private static Locker testLockerAverageCap;
    private static Locker testLockerConstraints;
    private static int lowCap;
    private static int highCap;
    private static int averageCap;
    private static LongTermStorage lts;
    private static Item[][] constrainTST;
    private static Item baseballBat_i;
    private static Item helmet1_i;
    private static Item helmet2_i;
    private static Item helmet3_i;
    private static Item sporesEngine_i;
    private static Item football_i;

    @BeforeClass
    public static void createTestObject() {
        lowCap = 1;
        highCap = 100;
        averageCap = 10;
        testLockerLowCap = new Locker(lts, lowCap, constrainTST);
        testLockerHighCap = new Locker(lts, highCap, constrainTST);
        testLockerAverageCap =new Locker(lts, averageCap, constrainTST);
        testLockerConstraints = new Locker(lts,highCap,constrainTST);
        baseballBat_i = ItemFactory.createSingleItem("baseball bat");
        helmet1_i = ItemFactory.createSingleItem("helmet, size 1");
        helmet2_i = ItemFactory.createSingleItem("helmet, size 2");
        helmet3_i = ItemFactory.createSingleItem("helmet, size 3");
        sporesEngine_i = ItemFactory.createSingleItem("spores engine");
        football_i = ItemFactory.createSingleItem("football");

    }
    int testAddItemToLocker = testLockerHighCap.addItem(helmet1_i, 3);
    int testAddItemToLts = testLockerLowCap.addItem(helmet1_i,2);
    int testAddTooManyItems = testLockerAverageCap.addItem(helmet2_i, 1000);

    @Test (timeout = 60)
    public void testAddItem() {
        assertEquals("TestAddItemToLocker failed.", 0, testAddItemToLocker);
        assertEquals("TestAddItemToLts failed.", 1, testAddItemToLts);
        assertEquals("TestAddTooManyItems failed.", -1, testAddTooManyItems);
    }

    @Test
    public void testAddItemWithConstraints() {
        testLockerConstraints.addItem(football_i,1);
        int valReturned = testLockerConstraints.addItem(baseballBat_i,1);
        assertEquals("Test testAddItemWithConstraints failed", -2, valReturned);
    }

    @After
    public void testRemoveItem() {
        int testRemoveItemSuccessfully = testLockerHighCap.removeItem(new Item("plate", 2),2);
        assertEquals("TestRemoveItemSuccessfully failed.", 0, testRemoveItemSuccessfully);
        int testRemoveTooManyItems = testLockerHighCap.removeItem(new Item("plate", 2),2);
        assertEquals("TestRemoveTooManyItems failed.", -1, testRemoveTooManyItems);
        int testRemoveNegativeItems = testLockerHighCap.removeItem(new Item("plate", 2),-1);
        assertEquals("TestRemoveNegativeItems failed.", -1, testRemoveNegativeItems);
    }
    @After
    public void testGetItemCount() {
        int highCapLockerCount = testLockerHighCap.getItemCount("plate");
        assertEquals("TestGetItemCount for highCapLocker failed.", 1, highCapLockerCount);
        int lowCapLockerCount = testLockerLowCap.getItemCount("fork");
        assertEquals("TestGetItemCount for lowCapLocker failed.", 0, lowCapLockerCount); // all forks are supposed to be in the lts
        int averageCapLockerCount = testLockerAverageCap.getItemCount("car");
        assertEquals("TestGetItemCount for averageCapLocker failed.", 0, averageCapLockerCount);
    }
    @After
    public void testGetInventory() {
        Map highCapLockerInventory = testLockerHighCap.getInventory();
        Map<String, Integer> assertHighCap = new HashMap<String, Integer>();
        assertHighCap.put("plate", 1);
        assertEquals("TestGetInventory for highCapLocker failed.", assertHighCap, highCapLockerInventory); // assertSame?
        Map lowCapLockerInventory = testLockerLowCap.getInventory();
        Map<String, Integer> assertLowCap = new HashMap<String, Integer>();
        assertEquals("TestGetInventory for lowCapLocker failed.", assertLowCap, lowCapLockerInventory); // assertSame?
    }
    @Test (timeout = 60)
    public void testGetCapacity(){
        assertEquals("TestGetCapacity failed", averageCap, testLockerAverageCap.getCapacity());
    }
    @After
    public void testGetAvailableCapacity(){
        assertEquals("TestGetAvailableCapacity failed", highCap-1, testLockerHighCap.getAvailableCapacity());
    }
}
