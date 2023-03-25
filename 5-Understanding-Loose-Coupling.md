```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {
		
		BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new BubbleSortAlgorithm());
		// If we want to use QuickSortAlgorithm instead, we just need to update the above line accordingly.
		// This is an example of 'Loose Coupling'.
		// So, the 'BinarySearchImpl' class is loosely coupled to both BubbleSortAlgorithm and QuickSortAlgorithm.

        // On thing to note here is that, for this SpringBoot Application, the object 'BinarySearchImpl binarySearchImpl' is a Bean. And classes 'BubbleSortAlgorithm' & 'QuickSortAlgorithm' are the 'Dependencies' for this bean. 
        // 'new BubbleSortAlgorithm()' is a bean of 'BubbleSortAlgorithm' class/component.
        // Similarly, 'new QuickSortAlgorithm()' is a bean of 'QuickSortAlgorithm' class/component.

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

    SortAlgorithm sortAlgorithm; // Interface 'SortAlgorithm' is a dependency of the 'BinarySearchImpl' class.

    BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers1 = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}
```

```java
package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 
```

```java
package com.spring.basics.spring_basics;

public class BubbleSortAlgorithm implements SortAlgorithm {
    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
```

```java
package com.spring.basics.spring_basics;

public class QuickSortAlgorithm implements SortAlgorithm{
    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```