Great Developers undrestand how to use **Spring** without **SpringBoot**.

In order to use **Pure and Basic Spring** in our Spring Application, we need to make a few changes in the following files only : **pom.xml** & **SpringbootApplication** files.

# Default pom.xml & SpringBootApplication Codes:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.spring.basics</groupId>
	<artifactId>spring_basics</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring_basics</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>19</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- Adding CDI Depedency  -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

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

		System.out.println(binarySearchImpl1);

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);

		System.out.println(result);
	}
}
```

# Modified pom.xml and SpringBootApplication Code:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.spring.basics</groupId>
	<artifactId>spring_basics</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring_basics</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>19</java.version>
	</properties>
	<dependencies>
		<!-- For SpringBoot Application -->
		<!-- <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency> -->

		<!-- For Spring Application -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
		</dependency>

		<!-- For Spring Application -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
		</dependency>

		<!-- For Using Logger in Spring Application -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
		</dependency>

		<!-- For Using Logger in Spring Application -->
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
		</dependency>

		<!-- No Changes -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- Adding CDI Depedency  -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

```java
package com.spring.basics.spring_basics.basicExample;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @SpringBootApplication
@Configuration   // annotation used in place of '@SpringBootApplication' - for using 'pure Spring'.
@ComponentScan  // annotation used for scanning the required classes/components while using 'pure Spring'. This will scan through the entire package 'com.spring.basics.spring_basics.basicExample'.
public class SpringBasicApplication {

	public static void main(String[] args) {

		// ApplicationContext applicationContext = SpringApplication.run(SpringBasicApplication.class, args);   
		// Used in SpringBoot Application

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBasicApplication.class);
		// Used for 'Pure Spring' Application

		BinarySearchImpl binarySearchImpl1 = applicationContext.getBean(BinarySearchImpl.class);

		System.out.println(binarySearchImpl1);

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);

		System.out.println(result);

		applicationContext.close();

	}
}
```