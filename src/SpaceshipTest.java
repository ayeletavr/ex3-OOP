import org.junit.*;
import static org.junit.Assert.*;
import oop.ex3.spaceship.*;

public class SpaceshipTest {
    private static Spaceship spaceship1;
    private static int[] crew1IDs = new int[] {111,222,333};
    private static int otherID = 444;
    private static Spaceship spaceship2;
    private static int[] crew2IDs = new int[] {555,666};
    private static Spaceship spaceship3;

    @BeforeClass
    public static void createTestObject() {
        spaceship1 = new Spaceship("Apollo", crew1IDs, 3);
        spaceship2 = new Spaceship("small ship", crew2IDs, 0);
        int createLockerWrongID = spaceship1.createLocker(444, 4);
        int createLockerWrongCapacity = spaceship1.createLocker(111,-1);
        int createLockerWithNoRoom = spaceship2.createLocker(555,1);
        int createLockerSuccessfully = spaceship1.createLocker(111,2);
        int[] crew1expected = new int[] {111,222,333};
    }
    @Test
    public void testGetLongTermStorage() {
        spaceship1.longTermStorage.addItem(new Item("rock", 3), 50);
        LongTermStorage lst1 = spaceship1.getLongTermStorage();
        assertTrue("TestGetLongTermStorage failed", lst1.getAvailableCapacity() == (1000-150));
    }

    int createLockerWrongID = spaceship1.createLocker(444, 4);
    int createLockerWrongCapacity = spaceship1.createLocker(111,-1);
    int createLockerWithNoRoom = spaceship2.createLocker(555,1);
    int createLockerSuccessfully = spaceship1.createLocker(111,2);
    int[] crew1expected = new int[] {111,222,333};

    @Test (timeout = 60)
    public void testCreateLocker() {
        assertEquals("TestCreateLocker with ID that is not a crew member failed.",
                -1, createLockerWrongID);
        assertEquals("TestCreateLocker with negative capacity failed.",
                -2, createLockerWrongCapacity);
        assertEquals("TestCreateLocker with no room for locker failed.",
                -3, createLockerWithNoRoom);
        assertEquals("TestCreateLocker failed.", 0, createLockerSuccessfully);
    }
    @Test (timeout = 60)
    public void testGetCrewIDs() {
        assertEquals("TestGetCrewIDs failed.", crew1expected, spaceship1.getCrewIDs());
    }
    @After
    public void testGetLockers(){
        assertTrue("TestCreateLocker failed", (spaceship1.getLockers().length) == 3);
    }

}
