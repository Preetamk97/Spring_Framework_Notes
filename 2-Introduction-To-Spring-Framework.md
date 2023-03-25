# What is Spring ?

- **Spring** is a popular open source **Java Application Development Framework** - created by ***Rod Johnson*** - that supports developing all types of Java applications such as enterprise applications, web applications, cloud based applications, and many more - mainly known for its **Dependency Injection** feature. Spring framework helps in developing a loosely coupled application that is simple, easily testable, reusable, and maintainable.

However, configuring a Spring application is a tedious job. Even though it provides flexibility in bean configuration with multiple ways such as XML, annotation, and Java-based configurations, there is no escape from the configuration. Configuring the Spring features may need more time for developers and may distract the developers from solving business problems.

Thanks to Spring Team for releasing Spring Boot, one of the topmost innovations in all the existing Spring Framework. Spring Boot provides a new prototype for developing Spring applications with nominal effort. Using Spring Boot, you will be able to develop Spring applications with extra agility and capacity to focus on solving business needs with nominal (or possibly no) configuring.

In this course, you will learn the core concepts of Spring Framework using Spring Boot that makes it easy to create a production-grade, stand-alone application that can just run.

# Dependencies At High Level

![](imgfiles\chap2\2023-03-02-17-31-48.png)

- A typical Java application will have three layers in its architecture: web, business and data.
- Web Layer depends on Business Layer. The business layer is a dependency for the web layer.
- Business layer depends on Data Layer. The data layer is a dependency for the business layer.

# Dependencies At Class Level

- Take a look at the below block of code:

    ```java
    class ServiceClass {
        SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();
    }
    ```

    Here, the class `ServiceClass` uses an object of another class `SortAlgorithm` as its member/attribute which is getting instatiated with the constructor of yet another class `BubbleSortAlgorithm` that implements the class `SortAlgorithm`. So, we can say that the classes `SortAlgorithm` and `BubbleSortAlgorithm` are **dependencies** of the `ServiceClass` class.

- In the Spring Framework, a dependency on the class level refers to the relationship between two classes where one class (dependent class) requires another class (dependency) in order to function properly.


# The Need For Spring Framework

- Consider the below code again:

    ```java
    class ServiceClass {
            SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();
    }

    class BubbleSortAlgorithm implements SortAlgorithm{}

    class QuickSortAlgorithm implements SortAlgorithm{}
    ```
    Here, both the classes **BubbleSortAlgorithm** &  **QuickSortAlgorithm** are implementing the **SortAlgorithm** interface. And inside the **ServiceClass** class we are instantiating a **SortAlgorithm** class object using the **BubbleSortAlgorithm** class constructor.

    But what if, later on in the `ServiceClass` code, we decide that we need an object of **SortAlgorithm** class that is instantiated using **QuickSortAlgorithm()** class constructor instead of the **BubbleSortAlgorithm()** class constructor.

    Normally, that would mean that we have edit the line of code `SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();`  into `SortAlgorithm sortAlgorithm = new QuickSortAlgorithm();`.

    This kind of situation where a **dependent class** (*ServiceClass*) is **directly instantiating** a specific **dependency class** (*BubbleSortAlgorithm*) inside it -- is called **Tight Coupling**.

    In a **tightly coupled system**, changes to one component may require changes to other interdependent components as well, which can lead to a ripple effect of changes throughout the system.

    **Tight coupling** of components is not considered a good coding practise. Our goal should be always to design **Loosely coupled** systems/codes. 

- Below is the **loosely coupled** variation of the above code:

    ```java
    class ServiceClass {
            // Removing the instantiation
            SortAlgorithm sortAlgorithm; //= new BubbleSortAlgorithm();

            ServiceClass(SortAlgorithm sortAlgorithm) {
                this.sortAlgorithm = sortAlgorithm;
            }
    }

    class BubbleSortAlgorithm implements SortAlgorithm{}

    class QuickSortAlgorithm implements SortAlgorithm{}

    class Main {
        // Instantiating Dependency Instance - as per our requirement.
        SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm(); 

        // Populating the Dependent Class with the Dependency Instance
        ServiceClass serviceClass = new ServiceClass(sortAlgorithm);
        // Here we can provide any type sortAlgorithm object we want - either BubbleSortAlgorithm or QuickSortAlgorithm.
    }
    ```

- **Spring Framework** helps us in building **loosely coupled** Java Enterprise Applications **efficiently** - by managing the tasks of ***Instantiating Dependencies*** and ***Populating the Dependency Instances*** - for us - as per our need.

    In other words, **Spring Framework** takes care of the below logic - for us:

    ```java
    // Instantiating Dependency 
    SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm(); 

    // Populating the Dependency Instance
    ServiceClass serviceClass = new ServiceClass(sortAlgorithm);
    ```


# @Component and @Autowired Annotations

```java
@Component
class ServiceClass {

    @Autowired
    SortAlgorithm sortAlgorithm;

    ServiceClass(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
}

@Component
class BubbleSortAlgorithm implements SortAlgorithm {
}
```

- **@Component** annotation tells the **Spring Framework** that it needs to create and manage the instances of these classes (***ServiceClass*** & ***BubbleSortAlgorithm***) - for us. 
- **@Autowired** annotation tells the **Spring Framework** that the `SortAlgorithm` class is infact a **dependency** of the `ServiceClass` class.

# Some Terminologies

- **Spring Beans** - The **instances/objects** of the **classes/components** that are managed by the **Spring Framework**.

- **Autowiring** - The process where **Spring** identifies the dependencies and populates them as per the need.
 
- **Dependency Injection** - It is a technique in which the responsibility of creating and wiring the dependencies of a dependent class is externalized to the external framework or library called dependency injection (DI) frameworks. So now the control over the construction and wiring of an object no longer resides with the dependent classes themselves. This reversal of responsibilities is known as Inversion of Control(IoC). Dependency injection framework also called IoC container.  

- **Inversion Of Control** - Transfer of control over **creating and populating dependencies** from the **Dependent Class** to the **Spring Framework**.

- **IOC Container** - The IoC container is a core part of the Spring Framework that manages the creation and configuration of objects/beans in an application. It reads configuration metadata and wires the necessary objects together. This helps to create loosely coupled systems. There are two types of IoC containers in Spring: **BeanFactory** and **ApplicationContext**. 