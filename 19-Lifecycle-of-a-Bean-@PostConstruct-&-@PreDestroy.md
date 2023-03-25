# Use of @PostConstruct and @PreDestroy Annotation:

```java
package com.spring.basics.spring_basics.basicExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicApplication.class, args);

		BinarySearchImpl binarySearchImpl1 = applicationContext.getBean(BinarySearchImpl.class);
		//postConstruct method is called.

		System.out.println(binarySearchImpl1);
		// com.spring.basics.spring_basics.BinarySearchImpl@3574e198

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

		System.out.println(result);
		// 3
		
		// preDestroy is called !
	}
}

//**************************************************************************

package com.spring.basics.spring_basics.basicExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component  // The beans of this component will be managed by Spring IOC (Inversion Of Control) Contatiner.
public class BinarySearchImpl {

    // Creating a logger object
    // Import org.slf4j.Logger
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // Autowiring the dependencies
    @Autowired
    SortAlgorithm sortAlgorithm; 
        
    public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public int binarySearch(int[] numbers, int numberToSearchFor) {
        
        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers = sortAlgorithm.sort(numbers); // Using BubbleSortAlgorithm class implementaion
        System.out.println(sortAlgorithm);
        // com.spring.basics.spring_basics.BubbleSortAlgorithm@182f1e9a

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }

    // @PostConstruct
    //****************** */
    // Let us say we want to execute a function - only when all the dependencies of the component 'BinarySearchImpl' have been created and populated successfully (currently we have only one dependency for this copmponent i.e SortAlgorithm class) - or in other words as soon as the bean of 'BinarySearchImpl' component is created - or as soon as the execution of the 'BinarySearchImpl' constructor is completed.
    // In this case we are gonna use the @PostConstruct Annotation.
    // The method in which we are using @PostConstruct annotation should be a 'void' method.
    @PostConstruct
    public void postConstruct() {   // This method will run as soon as all the 'BinarySearchImpl' dependencies have been created successfully and the execution of the 'BinarySearchImpl' constructor is completed - i.e the 'BinarySearchImpl' bean creation has beeen completed.
        //Simple logging output
        logger.info("postConstruct method is called.");
    }

    // @PreDestroy
    // The method carrying this annotation will run just before the spring destroys the bean of the 'BinarySearchImpl' component.
    @PreDestroy
    public void preDestroy() {
        logger.info("preDestroy is called !");
    }
}

//************************************************************************************************

package com.spring.basics.spring_basics.basicExample;

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 

//**************************************************************************************************

package com.spring.basics.spring_basics.basicExample;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}

//************************************************************************************************

package com.spring.basics.spring_basics.basicExample;

import org.springframework.stereotype.Component;

@Component
public class QuickSortAlgorithm implements SortAlgorithm{

    // There are no dependencies inside QuickSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Quick Sort Logic
        return numbers;
    }
}
```

## Output:

### ***After adding @PostConstruct code***: <br>

![](imgfiles\chap19\2023-03-14-14-01-12.png)

### ***After adding @PreDestroy code***: <br>

![](imgfiles\chap19\2023-03-14-14-14-57.png)