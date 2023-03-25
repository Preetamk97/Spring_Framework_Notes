Take a look at the code of the previous lesson:

```java
package com.spring.basics.spring_basics;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Creating the BinarySearchImpl class Bean.
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// 
		System.out.println(result);
		// 3
	}
}

//************************************************

package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm;  // Dependency

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

//************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 

//************************************************

package com.spring.basics.spring_basics;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
```

1. Till now, we were using only **one single** implementation of the **SortAlgorithm** interface (i.e **BubbleSortAlgorithm** class) as a **Spring Component** (***@Component***) - to be used in creating the bean of **BinarySearchImpl** class/component. So there was no ambiguity/confusion for the **Spring Framework** about which which implementation of **SortAlgorithm** interface should be used as a **component** for creating the **bean** and using it as a dependency inside the **BinarySearchImpl** class/component.

1. But what if our application has more than on implementations of the **SortAlgorithm** interface available (i.e. we use ***@Component*** annotation for both the **BubbleSortAlgorithm** & **QuickSortAlgorithm** classes). This will arise an ***ambiguity*** for the **Spring** framework as to which of the two available components (both are implementaions of the **SortAlgorithm** interface) should be used as a **dependency** while creating the  **BinarySearchImpl** bean.

### Code Illustration of the Problem: 

```java
package com.spring.basics.spring_basics;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Creating the BinarySearchImpl class Bean.
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// 
		System.out.println(result);
		// 3
	}
}

//************************************************

package com.spring.basics.spring_basics;

@Component
public class BinarySearchImpl {

    @Autowired
    SortAlgorithm sortAlgorithm;  // Dependency

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

//************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 

//************************************************

package com.spring.basics.spring_basics;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//************************************************

package com.spring.basics.spring_basics;

@Component
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}

// Here, there are 2 implementations of the SortAlgorithm interface - BubbleSortAlgorithm & QuickSortAlgorithm - both can be used as a @Component/dependency/bean in creation of the 'BinarySearchImpl' bean.
```

### Error Message in Terminal:

![](imgfiles\chap7\2023-03-08-13-17-59.png)


3. In that case, to resolve the **ambiguity**, we use the ***@Primary*** annotation on the class (among the  available components - **BubbleSortAlgorithm** & **QuickSortAlgorithm**) which we want to be used as a **dependency** in place of **SortAlgorithm** interface while creating the **BinarySearchImpl** bean.

# Code Illustration : 

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

    BinarySearchImpl(SortAlgorithm sortAlgorithm) { // SortAlgorithm sortAlgorithm = new QuickSortAlgorithm();
        this.sortAlgorithm = sortAlgorithm;
    }

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