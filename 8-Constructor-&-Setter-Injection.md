# Constructor Injection : Used in Earlier Examples

```java
package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm; // Dependency Interface

    // Constructor Injection
    BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}
```

# Setter Injection:

```java
package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm; // Dependency Interface

    // Setter Injection
    public void setSortAlgorithm (SortAlgorithm sortAlgorithm){
        this.sortAlgorithm = sortAlgorithm;
    }

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}
```

# No Constructor - No Setter

```java
package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm; // Dependency Interface

    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}
```

## Output in all the 3 cases is same:

![](imgfiles\chap8\2023-03-08-13-58-48.png)