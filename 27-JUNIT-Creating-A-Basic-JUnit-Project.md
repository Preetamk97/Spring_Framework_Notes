- Create a simple **Java Project** (***with No Build Tools***) named **jUnitDemo**.

- Inside the **src** folder of the project, create a class named **ArraySum.java** and add the following code to it.

    ```java
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
    ```
- Create a new folder named **test** beside the **src** folder. Inside this folder, we will create all our JUnit Test Files. It is always a good coding practise to keep the test files in a separate folder (**test**) outside the **src** folder - since we do not want to push these test files to the production along with the **src** source code files.

    ![](imgfiles\chap27\2023-03-16-16-42-14.png)

- Before proceeding further, if you are using **VS Code IDE**, particularly for this ***Java Project - with No Build Tools***, you need to add the **test** folder to the **ClassPath** of the project - otherwise this folder will not be detected as a part of the Java Project.

    To do that, follow the following steps:

    ![](imgfiles\chap27\2023-03-16-17-14-00.png) <br>

    ![](imgfiles\chap27\2023-03-16-17-15-29.png)

    ![](imgfiles\chap27\2023-03-16-17-17-28.png)

    ![](imgfiles\chap27\2023-03-16-17-18-32.png)

- Next, create a class ArraySumTest.java inside test folder and add the following code to it.

    ```java
    import static org.junit.Assert.fail;

    import org.junit.Test;

    public class ArraySumTest {
        
        @Test   // Annotation denoting that this is a test method.  // Imports 'org.junit.Test'
        public void test () {
            fail("Not yet implemented");
        }
    }
    ```

- Now to run the test class, in the left-side vertical bar of VS Code window, look for the **Testing** tab -> Select **Enable Testing** option -> Select **JUnit**.

![](2023-03-16-17-30-44.png)

- Select **Run Test**.

    ![](imgfiles\chap27\2023-03-16-17-33-51.png)

- You will see something like this (below): That is because of this line of code - `fail("Not yet implemented");`.

    ![](imgfiles\chap27\2023-03-16-17-37-16.png)

- If you remove this line of code (`fail("Not yet implemented");`) from **ArraySumTest.java** and rerun the test, you will see all the test results are positive (see below) : That is because in **JUnit** Framework - ***Absence of failure is Success***.

    ![](imgfiles\chap27\2023-03-16-17-40-35.png)

- Now lets us add the code for testing the **ArraySum.sumOfArray()** method inside the **test()** method of **ArraySumTest** class.

    Replace the **test()** method of **ArraySumTest** class with the below code.

    ```java

    ```