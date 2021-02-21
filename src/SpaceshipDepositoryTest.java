import org.junit.*;
import static org.junit.Assert.*;
import oop.ex3.spaceship.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LockerTest.class,
        LongTermTest.class,
        SpaceshipTest.class
})

public class SpaceshipDepositoryTest {
}
