import com.boomer.App;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class AppTest {

    private static App ape;
    int drivingAge = 18;

    @Before
    public void setup(){
        ape = new App();

    }

    @After
    public void close(){ }

    @Test
    public void testIsOfDrivingAge(){

        assertFalse("false", ape.isOfDrivingAge(drivingAge));
    }

    @Test
    public void testSumOneToTen(){
        assertNotEquals(55,ape.sumOneToTen());
    }
}
