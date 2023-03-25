# Autowiring Using @Primary

```java
package com.spring.basics.spring_basics;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Creating the BinarySearchImpl class Bean.
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.QuickSortAlgorithm@3574e198
		System.out.println(result);
		// 3
	}
}

//***********************************************

package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm;  // Dependency Interface

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);
        // com.spring.basics.spring_basics.QuickSortAlgorithm@3574e198

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//*************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 

//*************************************************

package com.spring.basics.spring_basics;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//*************************************************

package com.spring.basics.spring_basics;

@Component
@Primary  // Making QuickSortAlgorithm - the Primary component among the 2 available components.
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```


# Autowiring by Name:

```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Creating the BinarySearchImpl class Bean.
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@578524c3
		System.out.println(result);
		// 3
	}
}

//************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

    // Autowiring by Name
    @Autowired
    SortAlgorithm bubbleSortAlgorithm; 
    // Specifying the 'BinarySearchImpl' class to use the 'BubbleSortAlgorithm' component as the 'SortAlgorithm' dependency's implementation - directly by name.

    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = bubbleSortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(bubbleSortAlgorithm);
        // com.spring.basics.spring_basics.BubbleSortAlgorithm@578524c3

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//**************************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
}

//**************************************************************

package com.spring.basics.spring_basics;

import org.springframework.stereotype.Component;

// In this example we are using @Component annotation for both the 'QuickSortAlgorithm' & 'BubbleSortAlgorithm' classes.
@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//*****************************************************************

package com.spring.basics.spring_basics;

import org.springframework.stereotype.Component;

// In this example we are using @Component annotation for both the 'QuickSortAlgorithm' & 'BubbleSortAlgorithm' classes.
@Component
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```

# Autowiring by Name Vs Autowiring by @Primary - Which takes more Preference?

### Note :  Autowiring by @Primary takes more preference over Autowiring by Name. See the illustration below.

```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Creating the BinarySearchImpl class Bean.
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.QuickSortAlgorithm@b5cc23a
		// Autowiring by @Primary takes more preference over Autowiring by Name.
		
		System.out.println(result);
		// 3
	}
}

//*******************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

    // Autowiring by Name  // Autowiring by @Primary takes more preference over Autowiring by Name.
    @Autowired
    SortAlgorithm bubbleSortAlgorithm; 
    
    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {
        
        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = bubbleSortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(bubbleSortAlgorithm);
        // com.spring.basics.spring_basics.QuickSortAlgorithm@b5cc23a
        // Autowiring by @Primary takes more preference over Autowiring by Name.

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//************************************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
}

//*************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.stereotype.Component;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//***************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary   // Autowiring by @Primary takes more preference over Autowiring by Name
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```