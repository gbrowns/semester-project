import com.boomer.Student;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest {

    Student s;

    @Before
    public void setup(){
        this.s = new Student("John",1234);
    }

    @After
    public void close(){

    }

    @Test
    public void testGetName(){

        assertEquals("John",s.getName());
        System.out.println(s.getName());

    }

    @Test
    public void testGetRegNumber(){
        assertEquals(1234,s.getRegNo());
        System.out.println(s.getRegNo());
    }
}
