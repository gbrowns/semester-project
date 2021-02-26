import junit.framework.*;

public class UnitTestSuite {
    public static void main(String[] args){

        TestSuite suite = new TestSuite(AppTest.class);
        TestResult result = new TestResult();

        suite.run(result);
        System.out.printf("Number of Testcases = %s ", result.runCount());
    }
}
