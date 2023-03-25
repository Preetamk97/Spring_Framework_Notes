import org.junit.Test;

public class ArraySumTest {
    
    @Test   // Annotation denoting that this is a test method.  // Imports 'org.junit.Test'
    public void test () {
        
        // Creating an object of ArraySum class 
        ArraySum arraySum = new ArraySum();

        // running the sumOfArray() method - Passing an array {1,2,3}
        int result = arraySum.sumOfArray(new int[] {1,2,3});

        // Checking if the result is equal to 6
        System.out.println(result);

    }
}
