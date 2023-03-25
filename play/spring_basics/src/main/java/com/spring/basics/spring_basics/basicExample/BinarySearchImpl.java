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
    //************** */
    // The method carrying this annotation will run just before the spring destroys the bean of the 'BinarySearchImpl' component.
    @PreDestroy
    public void preDestroy() {
        logger.info("preDestroy is called !");
    }
}