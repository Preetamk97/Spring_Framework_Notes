```java
package com.spring.basics.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicsApplication {

	// What are the beans? --- Add @Component Annotation to the classes
	// BinarySearchImpl, BubbleSortAlgorithm since we need to
	// create and use beans of this classes inside our main
	// 'SpringBasicsApplication' class.

	// What are the dependencies of the beans? -- There is only 1 dependency
	// 'SortAlgorithm sortAlgorithm;' inside the 'BinarySearchImpl' class. So add a
	// '@Autowired' Annotation above 'SortAlgorithm sortAlgorithm;' in
	// 'BinarySearchImpl' class.

	// Where to search for the beans? => No need to do this as our components are in
	// the same package as the our main 'SpringBasicsApplication'.

	public static void main(String[] args) {

		// BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new
		// BubbleSortAlgorithm());

		// The task done by the above line of code i.e:
		// 1. Creating the 'BinarySearchImpl binarySearchImpl' bean.
		// 2. Wiring the 'new BubbleSortAlgorithm()' bean with the created bean
		// Is managed by the Spring Framework.

		// Creating an ApplicationContext object.
		// ApplicationContext = Manages all the Spring Beans.
		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsApplication.class, args);

		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);
		// Creating a bean of 'BinarySearchImpl' component using
		// applicationContext.getBean() method and assigning it to the 'BinarySearchImpl
		// binarySearchImpl' variable.

		// Error Message Alert
		// ******************** */

		// Note: When you make a new component for using in this SpringBootApplication,
		// you may get an error message that - no qualifying bean of type
		// 'com.spring.basics.spring_basics.binarysearchimpl' available.
		// This means the Spring application has not recognized the 'BinarySearchImpl'
		// class as one of its components.
		// This is a common VS Code problem. In that case, just close the VS Code and
		// open the project once again.
		// On reopening the project, the SpringBootApplication should be able to detect
		// the newly created components easily.

		// What is happening in the Background.
		// *************************************** */

		// Now, while creating the the bean of 'BinarySearchImpl' component, spring
		// needs to pass a 'SortAlgorithm sortAlgorithm' object/bean and pass it as a parameter to
		// its constructor.
		// Now, 'SortAlgorithm' interface is already autowired to the 'BinarySearchImpl'
		// component as its dependency - so there is no confusion in that.
		// But, 'SortAlgorithm' is actually only an interface, without any definition of
		// the sort() method inside it. So it's object cannot be used for any purpose by
		// 'BinarySearchImpl' bean directly.
		// Instead, Spring needs to find a class/component that is implementng the
		// 'SortAlgorithm' interface and also has a definition of the sort() method
		// inside it.
		// Currently, Spring has only one more class/component that is directly
		// implementing the 'SortAlgorithm' interface i.e the 'BubbleSortAlgorithm'
		// component.
		// So, Spring uses the bean of this 'BubbleSortAlgorithm' component in place of
		// 'SortAlgorithm sortAlgorithm' bean inside the 'BinarySearchImpl' component
		// constructor.

		// Hence the newly created 'BinarySearchImpl binarySearchImpl' bean uses the
		// 'new BubbleSortAlgorithm()' bean in its constructor - instead of using the
		// 'new SortAlgorithm' bean directly.

		// If you observe carefully, we have not even used the @Component annotation in
		// the SortAlgorithm interface.

		int result = binarySearchImpl.binarySearch(new int[] { 12, 4, 3 }, 3);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@7df587ef
		System.out.println(result);
		// 3
	}
}
```

```java
package com.spring.basics.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  
public class BinarySearchImpl {
    // @Component Annotation tells the Spring Framework that it needs to create a bean of this class/component and manage this class/component.

    // Typically, Binary Search Algorithm takes those arrays as input - that are
    // already sorted ahead of time.
    // But in this example, we assume that the Binary Search is also responsible for
    // sorting the array.

    @Autowired  
    SortAlgorithm sortAlgorithm; 
    // @Autowired Annotation tells Spring Framework that this object 'SortAlgorithm sortAlgorithm' is a dependency of the 'BinarySearchImpl' class/component.

    BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // Sorting the array : Implementing Sort Logic
        int[] sortedNumbers1 = sortAlgorithm.sort(numbers); // Using whatever sort algorithm we have.
        System.out.println(sortAlgorithm);
		// com.spring.basics.spring_basics.BubbleSortAlgorithm@7df587ef

        // Searching the Array : Implementing Search Logic

        // Return the Result
        return 3;
    }
}
```

```java
package com.spring.basics.spring_basics;

// Since 'SortAlgorithm' is just an interface, we are not going to create any beans for it - and therefore, we do not annotate it as a @Component.

public interface SortAlgorithm {
    public int[] sort(int[] numbers);
} 
```

```java
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
```