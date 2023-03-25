```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {
		
		BinarySearchImpl binarySearchImpl = new BinarySearchImpl();
		int result = binarySearchImpl.binarySearch(new int[] {12, 4, 3}, 3);
		System.out.println(result);

		SpringApplication.run(SpringBasicsApplication.class, args);
	}

}
```

```java
package com.spring.basics.spring_basics;

public class BinarySearchImpl {
    // Typically, Binary Search Algorithm takes those arrays as input - that are
    // already sorted ahead of time.
    // But in this example, we assume that the Binary Search is also responsible for
    // sorting the array.

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic : BubbleSortAlgorithm
        BubbleSortAlgorithm bubbleSort = new BubbleSortAlgorithm();
        int[] sortedNumbers = bubbleSort.sort(numbers);

        // Now, if we want to change the Sorting Algorithm that is being used in the binarySearch() logic (to say QuickSortAlgorithm), in that case, we would need to change the code of the above two statements. This is an example of Tight Coupling.
        // The binarySearch() logic is 'Tighly Coupled' with the 'BubbleSortAlgorithm'.

        // Searching the Array : Implementing Search Logic
        
        // Return the Result
        return 3;
    }
}
```

```java
package com.spring.basics.spring_basics;

public class BubbleSortAlgorithm {
    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
```

```java
package com.spring.basics.spring_basics;

public class QuickSortAlgorithm {
    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```