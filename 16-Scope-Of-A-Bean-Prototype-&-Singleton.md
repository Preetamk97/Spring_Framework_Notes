![](imgfiles\chap16\2023-03-10-14-13-37.png)

# Demostration of Singleton Scope of a Bean

- ### In Spring Framework, the default scope of a Bean is `Singleton` - which means, within the SpringBoot Application, we can get **only one** bean/object per `ApplicationContext` **for every class/component**. 
- ### If we try to create a second bean (from the same `applicationContext` - for the same class/component) we are not going to get a new bean - instead we are going to get an **exact copy** of the previously created bean - with the same ***Bean Hashcode***.
- ### Also,  within the SpringBoot Application, if we have 5 `applicationContext` objects, we can create 5 different beans for every class/component.

- The idea is demostrated in the code below:

```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

        // Single ApplicationContext object
		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Demonstration of Singleton scope of a Bean.
		// Creating 2 Beans of the 'BinarySearchImpl' class. 
		// In Spring Framework, the default scope of any Bean is - "Singleton".

        // Bean 1
		BinarySearchImpl binarySearchImpl1 = applicationContext.getBean(BinarySearchImpl.class);  // Bean 1
		System.out.println(binarySearchImpl1);
		// com.spring.basics.spring_basics.BinarySearchImpl@4cc547a

        // Creating a second bean from the same 'applicationContext' - of the same class - using same ApplicationContext object.
		BinarySearchImpl binarySearchImpl2 = applicationContext.getBean(BinarySearchImpl.class);  // Bean 2
		System.out.println(binarySearchImpl2);
		// com.spring.basics.spring_basics.BinarySearchImpl@4cc547a
		// Copy of the previously created Bean 1.
		// Same HashCode as previously created Bean.

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

		System.out.println(result);
		// 3
	}
}

//***********************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  // We can also explicitly mention the scope of a Bean as Singleton - However this is not required as - all Spring Beans are Singleton Scope by default.
public class BinarySearchImpl {

    // Autowiring using @Qualifier
    @Autowired
    @Qualifier("bubble")
    SortAlgorithm sortAlgorithm; 
    
    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {
        
        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using BubbleSortAlgorithm class implementaion
        System.out.println(sortAlgorithm);
        // com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//**********************************************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
}

//**********************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
```

# Demostration of Prototype Scope of a Bean

```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		// Demonstration of Prototype scope of a Bean.
		// Creating 2 Beans of the 'BinarySearchImpl' class. 

		BinarySearchImpl binarySearchImpl1 = applicationContext.getBean(BinarySearchImpl.class);  // Bean 1
		System.out.println(binarySearchImpl1);
		// com.spring.basics.spring_basics.BinarySearchImpl@3574e198

		BinarySearchImpl binarySearchImpl2 = applicationContext.getBean(BinarySearchImpl.class);  // Bean 2
		System.out.println(binarySearchImpl2);
		// com.spring.basics.spring_basics.BinarySearchImpl@6db66836
		// Different Bean 2 than previously created Bean 1.
		// Different HashCode.

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

		System.out.println(result);
		// 3
	}
}

//********************************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// Making the scope of 'BinarySearchImpl' Beans - 'Prototype'.
// @Scope("prototype")  // Not a good coding practise.
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  // Good Coding Practise
public class BinarySearchImpl {

    // Autowiring using @Qualifier
    @Autowired
    @Qualifier("bubble")
    SortAlgorithm sortAlgorithm; 
    
    // No Constructor - No Setter
    
    public int binarySearch(int[] numbers, int numberToSearchFor) {
        
        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using BubbleSortAlgorithm class implementaion
        System.out.println(sortAlgorithm);
        // com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}

//**************************************************************************************

package com.spring.basics.spring_basics;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
}

//**************************************************************************************

package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
```