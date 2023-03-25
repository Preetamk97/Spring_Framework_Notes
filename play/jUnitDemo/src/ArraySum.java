public class ArraySum {

    public int sumOfArray (int[] arr) {
        int sum = 0;
        for(int i: arr){
            sum=sum+i;
        }
        return sum;
    }

    // To check whether our sumOfArray Method is working fine or not, we will use the JUnit Framework.
}
