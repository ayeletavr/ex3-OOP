import org.junit.*;
import static org.junit.Assert.*;
import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class LongTermTest extends LongTermStorage {
    private static LongTermStorage testLTS;
    private static LongTermStorage emptyLTS;
    private static int ltsCapacity = 1000;
    private static LongTermStorage testResetLTS;
    private static Item baseballBat_i;
    private static Item helmet1_i;
    private static Item helmet2_i;
    private static Item helmet3_i;
    private static Item sporesEngine_i;
    private static Item football_i;

    @BeforeClass
    public static void createTestObject(){
        testLTS = new LongTermStorage();
        emptyLTS = new LongTermStorage();
        testResetLTS = new LongTermStorage();
        baseballBat_i = ItemFactory.createSingleItem("baseball bat");
        helmet1_i = ItemFactory.createSingleItem("helmet, size 1");
        helmet2_i = ItemFactory.createSingleItem("helmet, size 2");
        helmet3_i = ItemFactory.createSingleItem("helmet, size 3");
        sporesEngine_i = ItemFactory.createSingleItem("spores engine");
        football_i = ItemFactory.createSingleItem("football");
    }
    @Before
    public void testAddItem() {
        int testAddSuccessfully = testLTS.addItem(helmet1_i,200);
        assertEquals("TestAddItemToLtsSuccessfully failed", 0, testAddSuccessfully);
        int testFailAdding = testLTS.addItem(helmet1_i,2000);
        assertEquals("TestAddItemToLstUnSuccessfully failed", -1, testFailAdding);
    }
    @After
    public void testResetInventory() {
        testResetLTS.addItem(helmet1_i,2);
        testResetLTS.resetInventory();
        assertEquals("TestResetInventory failed.", emptyLTS, testResetLTS);
    }
    @After
    public void testGetItemCount() {
        int testLTSCount = testLTS.getItemCount("helmet, size 1");
        assertEquals("TestGetItemCount1 failed.", 200, testLTSCount);
        int testEmptyLTSCount = emptyLTS.getItemCount("helmet, size 1");
        assertEquals("TestGetItemCount2 failed.", 0, testEmptyLTSCount);
    }
    @After
    public void testGetInventory() {
        Map testLTSInventory = testLTS.getInventory();
        Map<String, Integer> assertInventory = new HashMap<String, Integer>();
        assertInventory.put("helmet, size 1", 200);
        assertEquals("TestGetInventory1 failed.", assertInventory, testLTSInventory); // assertSame?
        Map testEmptyLTSInventory = emptyLTS.getInventory();
        Map<String, Integer> assertEmptyInventory = new HashMap<String, Integer>();
        assertEquals("TestGetInventory1 for an empty lts failed.", assertEmptyInventory, testEmptyLTSInventory); // assertSame?
    }
    @Test (timeout = 60)
    public void testGetCapacity() {
        assertEquals("TestGetCapacity failed", ltsCapacity, testLTS.getCapacity());
    }
    @After
    public void testGetAvailableCapacity() {
        assertEquals("TestGetAvailableCapacity failed", ltsCapacity-200, testLTS.getAvailableCapacity());
    }
}
