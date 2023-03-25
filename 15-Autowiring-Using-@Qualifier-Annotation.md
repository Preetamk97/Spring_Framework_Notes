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
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

		System.out.println(result);
		// 3
	}
}

//*******************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

    // Autowiring using @Qualifier
    @Autowired
    @Qualifier("bubble")   // Using BubbleSortAlgorithm class implementaion
    SortAlgorithm sortAlgorithm; 
    
    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {
        
        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); 
        System.out.println(sortAlgorithm);
        // com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//***************************************************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
}

//***************************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")   // Using @Qualifier annotation
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//***************************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("quick")  // Using @Qualifier annotation
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```